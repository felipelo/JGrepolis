package com.mycompany.jgrepolis;

public class RetrieveRankRun implements Runnable {

    private RetrieveRank retrieveRank;
    
    public RetrieveRankRun() {
        retrieveRank = new RetrieveRank();
    }
    
    @Override
    public void run() {
        while( true ) {
            try {
                retrieveRank.retrieveRank();
                
                Thread.sleep( 30 * 60 * 1000);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
