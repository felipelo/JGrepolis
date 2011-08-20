package com.mycompany.jgrepolis;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class App {

    public static void main(String[] args) throws Exception {
        HttpClientResource _resource = HttpClientResource.getInstance();
        _resource.login();
        
        UnderAttackRun _underAttack = new UnderAttackRun();
        
        Thread _thUnderAttack = new Thread( _underAttack );
        _thUnderAttack.start();
        
//        /*
//         * busca rank
//         */
//        for( int i = 0; i < 10; i++ ) {
//            HttpGet rankPost = new HttpGet("http://pt2.grepolis.com/game/ranking?action=sea_player&sea_id=55&offset="+(i*12));
//            rankPost.addHeader("Accept", "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
//            rankPost.addHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
//            rankPost.addHeader("Accept-Encoding", "gzip, deflate");
//            rankPost.addHeader("Accept-Encoding", "gzip,deflate,sdch");
//            rankPost.addHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
//            rankPost.addHeader("Connection", "keep-alive");
//            rankPost.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
//
//            XMLXPath.main( HttpClientResource.getInstance().executeAsString(rankPost) );
//        }

        _thUnderAttack.join();
        
        HttpClientResource.getInstance().closeClient();
    }

    private void first() throws Exception {
        DefaultHttpClient cliente = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet("http://pt.grepolis.com");

        HttpResponse response = cliente.execute(httpGet);
        HttpEntity entity = response.getEntity();

        System.out.println("Login form get: " + response.getStatusLine());

        System.out.println(EntityUtils.toString(entity));
    }

    static class GzipDecompressingEntity extends HttpEntityWrapper {

        public GzipDecompressingEntity(final HttpEntity entity) {
            super(entity);
        }

        @Override
        public InputStream getContent()
                throws IOException, IllegalStateException {

            // the wrapped entity's getContent() decides about repeatability
            InputStream wrappedin = wrappedEntity.getContent();

            return new GZIPInputStream(wrappedin);
        }

        @Override
        public long getContentLength() {
            // length of ungzipped content is not known
            return -1;
        }
    }
}
