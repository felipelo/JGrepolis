package com.mycompany.jgrepolis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

public class UnderAttack {    
    
    public boolean isUnderAtack( String html ) throws Exception {
        InputStream _is = new ByteArrayInputStream( html.getBytes("UTF-8") );
        OutputStream _os = new ByteArrayOutputStream( (int)(html.length() * 0.5) );
        
        Tidy _tidy = new Tidy();
        
        Document _doc = _tidy.parseDOM( _is, _os );
        
        _is.close();
        _os.close();
        
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xpath.compile("//span[@id='game_incoming']");
        NodeList result = (NodeList) expr.evaluate(_doc, XPathConstants.NODESET);
        
        return (result.getLength() > 0);
    }
    
    public String isCityUnderAttack( String html, String cityName ) throws Exception {
        InputStream _is = new ByteArrayInputStream( html.getBytes() );
        OutputStream _os = new ByteArrayOutputStream( (int)(html.length() * 0.5) );
        
        Tidy _tidy = new Tidy();
        
        Document _doc = _tidy.parseDOM( _is, _os );
        
        _is.close();
        _os.close();
        
        XPath _xpath = XPathFactory.newInstance().newXPath();
        
        XPathExpression _expAttack = _xpath.compile( "//ul[@id='unit_movements']/li[@class='attack incoming']/div/span/text()" );
//        XPathExpression _expCityName = _xpath.compile( "//span[@id='town_name_span_text']/a/text()" );
        
        NodeList _resAttack = (NodeList) _expAttack.evaluate( _doc, XPathConstants.NODESET );
//        NodeList _resCityName = (NodeList) _expCityName.evaluate( _doc, XPathConstants.NODESET );
        
//        String _cityName = _resCityName.item( 0 ).getNodeValue();
         
        StringBuilder _sb = new StringBuilder( cityName.length() + ( _resAttack.getLength() * 8) );
        _sb.append( cityName.concat("\n") );
        int _x = 0;
        for( _x = 0; _x < _resAttack.getLength()-1; _x++ ) {
            _sb.append( _resAttack.item(_x).getNodeValue().concat("\n") );
        }
        if( _resAttack.getLength() > 0 ) {
            System.out.println("======================================");
            System.out.println(html);
            System.out.println("======================================");
            _sb.append( _resAttack.item(_x).getNodeValue().concat("\n") );
            //hora, minuto, segundo
            String _time[] = _resAttack.item(_x).getNodeValue().split(":");
            
            long _milSec = Integer.parseInt( _time[0] ) * 60;                   //minutos
            _milSec = (_milSec*60) + (Integer.parseInt( _time[1] ) * 60);       //segundo
            _milSec = (_milSec*1000) + (Integer.parseInt( _time[2] ) * 1000);   //mil

            System.out.println(_milSec);
        }
        
        return _sb.toString();
    }
    
}
