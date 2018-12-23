/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Filip
 */
public class MenuForm extends javax.swing.JFrame {

    /**
     * Creates new form MenuForm
     */
    public MenuForm() {
        initComponents();
    }
    
    public void addListener(ActionListener e1, ActionListener e2, ActionListener e3, ActionListener e4, ActionListener e5){
        btn_new_game.addActionListener(e1);
        btn_highscore.addActionListener(e2);
        btn_exit.addActionListener(e3);
        btn_login.addActionListener(e4);
        btn_register.addActionListener(e5);
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
        btn_new_game = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btn_highscore = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn_exit = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_login = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btn_register = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));

        pnl_main.setLayout(new java.awt.GridLayout(4, 1, 1, 1));

        jPanel1.setLayout(new java.awt.BorderLayout());

        btn_new_game.setText("NEW GAME");
        btn_new_game.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_new_gameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_new_gameMouseExited(evt);
            }
        });
        jPanel1.add(btn_new_game, java.awt.BorderLayout.CENTER);

        pnl_main.add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        btn_highscore.setText("HIGHSCORE");
        jPanel2.add(btn_highscore, java.awt.BorderLayout.CENTER);

        pnl_main.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        btn_exit.setText("EXIT");
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_exitMouseExited(evt);
            }
        });
        jPanel3.add(btn_exit, java.awt.BorderLayout.CENTER);

        pnl_main.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2, 1, 1));

        jPanel5.setLayout(new java.awt.BorderLayout());

        btn_login.setText("LOGIN");
        jPanel5.add(btn_login, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        btn_register.setText("REGISTER");
        jPanel6.add(btn_register, java.awt.BorderLayout.CENTER);

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

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_highscore;
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_new_game;
    private javax.swing.JButton btn_register;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel pnl_main;
    // End of variables declaration//GEN-END:variables
}
