/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Filip
 */
public class PreviewForm extends javax.swing.JFrame {

    /**
     * Creates new form PreviewForm
     */
    public PreviewForm() {
        initComponents();
    }
    
    public void addListener(ActionListener e){
        
        btn_next.addActionListener(e);

    }

    public void setL1(String text) {
        this.l1.setText(text);
    }

    public String getLb_score() {
        return lb_score.toString();
    }

    public void setL2(String text) {
        this.l2.setText(text);
    }

    public void setL3(String text) {
        this.l3.setText(text);
    }

    public void setL4(String text) {
        this.l4.setText(text);
    }

    public void setL5(String text) {
        this.l5.setText(text);
    }

    public void setL6(String text) {
        this.l6.setText(text);
    }

    public void setL7(String text) {
        this.l7.setText(text);
    }

    public void setL8(String text) {
        this.l8.setText(text);
    }

    public void setL9(String text) {
        this.l9.setText(text);
    }

    public void setLb_score(String text) {
        this.lb_score.setText(text);
    }

    public void setBtn_next(String text) {
        this.btn_next.setText(text);
    }

    public String getBtn_next() {
        return btn_next.getText();
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_next = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        l1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        l2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        l3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        l4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        l5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        l6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        l7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        l8 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        l9 = new javax.swing.JLabel();
        lb_score = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_next.setText("NEXT");

        jPanel2.setLayout(new java.awt.GridLayout(3, 3, 1, 1));

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new java.awt.BorderLayout());

        l1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(l1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new java.awt.BorderLayout());

        l2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        l2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(l2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new java.awt.BorderLayout());

        l3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        l3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(l3, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new java.awt.BorderLayout());

        l4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        l4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(l4, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setLayout(new java.awt.BorderLayout());

        l5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        l5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(l5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.setLayout(new java.awt.BorderLayout());

        l6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        l6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel8.add(l6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8);

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setLayout(new java.awt.BorderLayout());

        l7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        l7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(l7, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel9);

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setLayout(new java.awt.BorderLayout());

        l8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        l8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel10.add(l8, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel10);

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setLayout(new java.awt.BorderLayout());

        l9.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        l9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(l9, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel11);

        lb_score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(lb_score, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_next, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lb_score, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_next;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
    private javax.swing.JLabel lb_score;
    // End of variables declaration//GEN-END:variables
}