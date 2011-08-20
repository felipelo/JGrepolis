package com.mycompany.jgrepolis;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.http.client.methods.HttpGet;

public class UnderAttackRun implements Runnable {
    
    private UnderAttack underAttack;
    
    public UnderAttackRun() {
        underAttack = new UnderAttack();
    }
    
    public static void main(String[] args) throws Exception {
//        String _str = FileUtils.readFileToString(new File("C:\\Users\\Felipe\\Documents\\NetBeansProjects\\JGrepolis\\src\\test\\java\\underAtack.xml") );
//        
        UnderAttackRun u = new UnderAttackRun();
//        System.out.println( u.isCityUnderAttack(_str) );
        
//        Thread _thread = new Thread( u );
//        _thread.start();
//        _thread.join();
//        System.out.println("terminou!");
    }

    @Override
    public void run() {
        String cityCod[][] = {
            {"96044", "Born"},
            {"96630", "Cidade de Felipelo"}, //cidade felipelo
            {"5752", "Edirme"}, //edirme
            {"96245", "Last Born"}, //last born
            {"6621", "new3"}, //new3
            {"96379", "Poor City"}, //poor city
            {"6394", "Roma"} //roma            
        };
        
        try {
            HttpClientResource _httpClient = HttpClientResource.getInstance();
            
            while( true ) {

                HttpGet _checkAttach = new HttpGet( "http://pt2.grepolis.com/game/index?town_id=".concat(cityCod[0][0]) );
                _checkAttach.addHeader("Accept", "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
                _checkAttach.addHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
                _checkAttach.addHeader("Accept-Encoding", "gzip, deflate");
                _checkAttach.addHeader("Accept-Encoding", "gzip,deflate,sdch");
                _checkAttach.addHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
                _checkAttach.addHeader("Connection", "keep-alive");
                _checkAttach.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");

                String _result = _httpClient.executeAsString( _checkAttach );

                if( underAttack.isUnderAtack(_result) ) {
                    StringBuilder _sb = new StringBuilder();

                    _result = underAttack.isCityUnderAttack( _result, cityCod[0][1] );
                    _sb.append( _result.concat("\n\n") );

                    for( int x = 1; x < cityCod.length; x++ ) {
                        _checkAttach = new HttpGet( "http://pt2.grepolis.com/game/unit_overview?action=info&town_id=".concat(cityCod[x][0]) );
                        _checkAttach.addHeader("Accept", "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
                        _checkAttach.addHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
                        _checkAttach.addHeader("Accept-Encoding", "gzip,deflate,sdch");
                        _checkAttach.addHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
                        _checkAttach.addHeader("Connection", "keep-alive");
                        _checkAttach.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");

                        _result = _httpClient.executeAsString( _checkAttach );

                        _result = underAttack.isCityUnderAttack( _result, cityCod[x][1] );
                        _sb.append( _result.concat("\n") );
                    }

                    System.out.println( _sb );
                    
                    sendEmail( _sb );
                }
                System.out.println("Sleeping for 30 minutes!!!");
                Thread.sleep( 30 * 60 * 1000 );
                
            }
        } catch (Exception ex) {
            Logger.getLogger(UnderAttackRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendEmail(StringBuilder _sb) throws EmailException {
        Email _mail = new SimpleEmail();
        _mail.setHostName("smtp.gmail.com");
        _mail.setSmtpPort(587);
        _mail.setAuthenticator(new DefaultAuthenticator("user", "pass"));
        _mail.setTLS(true);
        _mail.setFrom("felipe.lorenz@gmail.com");
        _mail.addTo("felipe.lorenz@gmail.com");
        _mail.setSubject("Ataques!");
        _mail.setMsg( _sb.toString() );
        
        _mail.send();
    }
    
}
