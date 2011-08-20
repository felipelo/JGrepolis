package com.mycompany.jgrepolis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

public class RetrieveResources {
    
    public void retrieveResources( String html ) throws Exception {
        InputStream _is = new ByteArrayInputStream( html.getBytes("UTF-8") );
        OutputStream _os = new ByteArrayOutputStream( (int)(html.length() * 0.5) );
        
        Tidy _tidy = new Tidy();
        
        Document _doc = _tidy.parseDOM( _is, _os );
        
        _is.close();
        _os.close();
        
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = null;
        Node result = null;
        
        expr = xpath.compile("//span[@id='wood_count']/text()");
        result = (Node) expr.evaluate(_doc, XPathConstants.NODE);
        System.out.println("Wood: " + result.getNodeValue());
        
        expr = xpath.compile("//span[@id='stone_count']/text()");
        result = (Node) expr.evaluate( _doc, XPathConstants.NODE );
        System.out.println("Stone: " + result.getNodeValue() );
        
        expr = xpath.compile("//span[@id='iron_count']/text()");
        result = (Node) expr.evaluate( _doc, XPathConstants.NODE );
        System.out.println("iron: " + result.getNodeValue() );
    }
    
    public static void main(String[] args) throws Exception {
        String _str = FileUtils.readFileToString(new File("C:\\Users\\Felipe\\Documents\\NetBeansProjects\\JGrepolis\\src\\test\\java\\underAtack.xml") );
        
        new RetrieveResources().retrieveResources(_str);
    }
    
}
