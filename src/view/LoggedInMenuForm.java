/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Filip
 */
public class LoggedInMenuForm extends javax.swing.JFrame {

    /**
     * Creates new form MenuForm
     */
    public LoggedInMenuForm() {
        initComponents();
    }

    public void setLb_username_display(String text) {
        this.lb_username_display.setText(text);
    }
    
    
    public void addListener(ActionListener e1, ActionListener e2, ActionListener e3, ActionListener e4, ActionListener e5){
        btn_new_game.addActionListener(e1);
        btn_highscore.addActionListener(e2);
        btn_exit.addActionListener(e3);
        btn_log_out.addActionListener(e4);
        btn_online_game.addActionListener(e5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_log_out = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lb_username_display = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_new_game = new javax.swing.JButton();
        btn_online_game = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn_highscore = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btn_exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));

        pnl_main.setLayout(new java.awt.GridLayout(4, 1, 1, 1));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout(4, 0));
        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        btn_log_out.setText("LOG OUT");
        jPanel1.add(btn_log_out, java.awt.BorderLayout.LINE_END);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Username: ");
        jPanel1.add(jLabel1, java.awt.BorderLayout.LINE_START);

        lb_username_display.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_username_display.setForeground(new java.awt.Color(204, 0, 0));
        jPanel1.add(lb_username_display, java.awt.BorderLayout.CENTER);

        pnl_main.add(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        btn_new_game.setText("NEW GAME");
        btn_new_game.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_new_gameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_new_gameMouseExited(evt);
            }
        });
        jPanel2.add(btn_new_game);

        btn_online_game.setText("ONLINE GAME");
        btn_online_game.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_online_gameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_online_gameMouseExited(evt);
            }
        });
        jPanel2.add(btn_online_game);

        pnl_main.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        btn_highscore.setText("HIGHSCORE");
        jPanel3.add(btn_highscore, java.awt.BorderLayout.CENTER);

        pnl_main.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2, 1, 1));

        jPanel6.setLayout(new java.awt.BorderLayout());

        btn_exit.setText("EXIT");
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_exitMouseExited(evt);
            }
        });
        jPanel6.add(btn_exit, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel6);

        pnl_main.add(jPanel4);

        getContentPane().add(pnl_main, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_new_gameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_new_gameMouseEntered
        btn_new_game.setBackground(Color.green);
    }//GEN-LAST:event_btn_new_gameMouseEntered

    private void btn_new_gameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_new_gameMouseExited
        btn_new_game.setBackground(new JButton().getBackground());
    }//GEN-LAST:event_btn_new_gameMouseExited

    private void btn_exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseEntered
        btn_exit.setBackground(Color.red);
    }//GEN-LAST:event_btn_exitMouseEntered

    private void btn_exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseExited
        btn_exit.setBackground(new JButton().getBackground());
    }//GEN-LAST:event_btn_exitMouseExited

    private void btn_online_gameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_online_gameMouseEntered
        btn_online_game.setBackground(Color.green);
    }//GEN-LAST:event_btn_online_gameMouseEntered

    private void btn_online_gameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_online_gameMouseExited
        btn_online_game.setBackground(new JButton().getBackground());
    }//GEN-LAST:event_btn_online_gameMouseExited

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_highscore;
    private javax.swing.JButton btn_log_out;
    private javax.swing.JButton btn_new_game;
    private javax.swing.JButton btn_online_game;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lb_username_display;
    private javax.swing.JPanel pnl_main;
    // End of variables declaration//GEN-END:variables
}
