package com.mycompany.jgrepolis;

import com.mycompany.jgrepolis.model.Classificacao;
import com.mycompany.jgrepolis.model.ClassificacaoPK;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

public class RetrieveRank {

    private EntityManagerFactory emf;
    private EntityManager em;
    
    public RetrieveRank() {
        emf = Persistence.createEntityManagerFactory("JGrepolis");
        em = emf.createEntityManager();
    }
    
    public void retrieveRank() throws Exception {
        HttpClientResource _httpClient = HttpClientResource.getInstance();
        
        for( int i = 0; i < 10; i++ ) {
            HttpGet _rankPost = new HttpGet("http://pt2.grepolis.com/game/ranking?action=sea_player&sea_id=55&offset="+(i*12));
            _rankPost.addHeader("Accept", "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
            _rankPost.addHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
            _rankPost.addHeader("Accept-Encoding", "gzip, deflate");
            _rankPost.addHeader("Accept-Encoding", "gzip,deflate,sdch");
            _rankPost.addHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
            _rankPost.addHeader("Connection", "keep-alive");
            _rankPost.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");

            String _result = _httpClient.executeAsString( _rankPost );
            
            em.getTransaction().begin();
            saveRank( _result );
            em.getTransaction().commit();
        }
    }
    
    private void saveRank( String html ) throws Exception {
        InputStream _is = new ByteArrayInputStream( html.getBytes("UTF-8") );
        OutputStream _os = new ByteArrayOutputStream( (int)(html.length() * 0.5) );
        
        Tidy _tidy = new Tidy();
        
        Document doc = _tidy.parseDOM( _is, _os );
        
        _is.close();
        _os.close();
        
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xpath.compile("//div[@class='ranking_table_body']/table/tr/td");
        NodeList result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        
        Date date = new Date();
            
        for (int index = 0; index < 12; index++) {
            ClassificacaoPK clPK = new ClassificacaoPK();
            clPK.setDate(date);

            Classificacao cl = new Classificacao( clPK );
            for( int pos = 0; pos < 6; pos++ ) {
                Node aNode = result.item(pos+(index*6)).getFirstChild();
                if( aNode == null ) continue;

                switch(pos) {
                    case 0:
                        cl.setRank( Integer.parseInt(aNode.getNodeValue()) );
                        break;
                    case 1:
                        aNode = aNode.getFirstChild();
                        clPK.setPlayer( aNode.getNodeValue() );
                        break;
                    case 2:
                        cl.setPoints( Integer.parseInt(aNode.getNodeValue()) );
                        break;
                    case 3:
                        aNode = aNode.getFirstChild();
                        if( aNode == null || aNode.getNodeValue() == null )
                            cl.setAlly( "" );
                        else
                            cl.setAlly( aNode.getNodeValue() );
                        break;
                    case 4:
                        cl.setCities( Integer.parseInt(aNode.getNodeValue()) );
                        break;
                    case 5:
                        cl.setPointsAvg( Integer.parseInt(aNode.getNodeValue()) );
                        break;
                }
            }
            em.persist( cl );        
        }
    }    
}
