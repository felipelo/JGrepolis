package com.mycompany.jgrepolis.view;

import com.mycompany.jgrepolis.SystemResources;
import javax.swing.SwingUtilities;

public class Main extends javax.swing.JFrame {

    SystemResources sysResources;
    
    public Main() {        
        sysResources = SystemResources.getInstance();
        
        initComponents();
        
        if( sysResources.getUser() == null ) {
            userSigin.setContentPane( new UserSigIn() );
            userSigin.setSize( 200, 140 );
//            SwingUtilities.
            userSigin.setVisible(true);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userSigin = new javax.swing.JDialog();

        userSigin.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        userSigin.setTitle("Sig In");
        userSigin.setAlwaysOnTop(true);
        userSigin.setModal(true);

        javax.swing.GroupLayout userSiginLayout = new javax.swing.GroupLayout(userSigin.getContentPane());
        userSigin.getContentPane().setLayout(userSiginLayout);
        userSiginLayout.setHorizontalGroup(
            userSiginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        userSiginLayout.setVerticalGroup(
            userSiginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog userSigin;
    // End of variables declaration//GEN-END:variables
}
