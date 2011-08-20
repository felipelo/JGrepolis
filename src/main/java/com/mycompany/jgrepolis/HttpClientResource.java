package com.mycompany.jgrepolis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientResource {
    private static HttpClientResource instance;
    private DefaultHttpClient client;

    private HttpClientResource() {
        client = new DefaultHttpClient();
        client.addResponseInterceptor(new HttpResponseInterceptor() {

            @Override
            public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
                HttpEntity entity = response.getEntity();
                Header ceheader = entity.getContentEncoding();
                if (ceheader != null) {
                    HeaderElement[] codecs = ceheader.getElements();
                    for (int i = 0; i < codecs.length; i++) {
                        if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                            response.setEntity(
                                    new GzipDecompressingEntity(response.getEntity()));
                            return;
                        } else if (codecs[i].getName().equalsIgnoreCase("deflate")) {
                            response.setEntity(new DeflateDecompressingEntity(entity));
                        }
                    }
                }
            }
        });
    }

    public synchronized static HttpClientResource getInstance() {
        if (instance == null) {
            instance = new HttpClientResource();
        }
        return instance;
    }
    
    public synchronized HttpResponse executeAsResponse( HttpRequestBase request ) throws IOException {
        return client.execute( request );
    }
    
    public synchronized String executeAsString( HttpRequestBase request ) throws IOException {
        HttpResponse _response = client.execute( request );
        HttpEntity entity = _response.getEntity();
        return EntityUtils.toString(entity);
    }
    
    public synchronized void closeClient() {
        client.getConnectionManager().shutdown();
    }
    
    public void login() throws Exception {
        HttpPost httpPost = new HttpPost("http://pt.grepolis.com/start?action=login");
        httpPost.addHeader("Accept", "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
        httpPost.addHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
        httpPost.addHeader("Accept-Encoding", "gzip,deflate,sdch");
        httpPost.addHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
        httpPost.addHeader("Cache-Control", "max-age=0");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Host", "pt.grepolis.com");
        httpPost.addHeader("Origin", "http://pt.grepolis.com");
        httpPost.addHeader("Refer", "http://pt.grepolis.com/");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        
        List<NameValuePair> nvps = new ArrayList<NameValuePair>(3);
        nvps.add(new BasicNameValuePair("facebook_login", ""));
        nvps.add(new BasicNameValuePair("name", "name"));
        nvps.add(new BasicNameValuePair("password", "pass"));
        nvps.add(new BasicNameValuePair("portal_sid", ""));
        nvps.add(new BasicNameValuePair("world", "pt2"));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        
        HttpResponse _response = getInstance().executeAsResponse(httpPost);
        _response.getEntity().getContent().close();
        
        HttpGet httpPost_2 = new HttpGet( _response.getFirstHeader("Location").getValue() );
        httpPost_2.addHeader("Accept", "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
        httpPost_2.addHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
        httpPost_2.addHeader("Accept-Encoding", "gzip,deflate,sdch");
        httpPost_2.addHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
        httpPost_2.addHeader("Cache-Control", "max-age=0");
        httpPost_2.addHeader("Connection", "keep-alive");
        httpPost_2.addHeader("Host", "pt2.grepolis.com");
        httpPost_2.addHeader("Refer", "http://pt.grepolis.com/");
        httpPost_2.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        
        executeAsString(httpPost_2);
        
    }
}
