package com.mycompany.jgrepolis;

import com.mycompany.jgrepolis.model.Classificacao;
import com.mycompany.jgrepolis.model.ClassificacaoPK;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

public class XMLXPath {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JGrepolis");

    public static void main(String html) {
        EntityManager em = emf.createEntityManager();
        
        InputStream _is = null;
        OutputStream _os = null;
        
        try {
            em.getTransaction().begin();

            _is = new ByteArrayInputStream( html.getBytes("UTF-8") );
            _os = new ByteArrayOutputStream( (int)(html.length() * 0.5) );

            Tidy t = new Tidy();

            Document doc = t.parseDOM( _is, _os );

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
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                if( _is != null ) _is.close();
                if( _os != null ) _os.close();
            } catch (IOException ex) {}
            
            em.getTransaction().commit();
            em.close();
        }
    }
}
