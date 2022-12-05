/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.node;
import model.server;
/**
 *
 * @author Admin
 */
public class FirstNode extends javax.swing.JFrame {

    private server sv;
    public FirstNode() {
        this.setTitle("FirstNode");
        initComponents();
        this.tfSendToCoordinator.setEditable(false);
        this.btnSendToCoordinator.setEnabled(false);
        this.tpChatbox.setContentType("text/html");
        this.tpChatbox.setDragEnabled(true);
        JFrame tmp = this;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                if (JOptionPane.showConfirmDialog(tmp, 
                    "Bạn chắc chắn muốn thoát?", "Thoát chương trình?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.out.println("thoat");
                    try {
                        //---co the ghi lai log tai day
                        sv.WriteLog("FirstNode.txt");
                    } catch (IOException ex) {
                        System.out.println(ex.getCause());
                    }
                    System.exit(0);
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfPort = new javax.swing.JTextField();
        lbPort = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        lbServerStatus = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpLog = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tpChatbox = new javax.swing.JTextPane();
        tfSendToCoordinator = new javax.swing.JTextField();
        btnSendToCoordinator = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tfPort.setEditable(false);
        tfPort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfPort.setText("3000");

        lbPort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbPort.setText("Port");

        btnConnect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConnect.setText("Open server");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        lbServerStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        tpLog.setEditable(false);
        tpLog.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(tpLog);

        tpChatbox.setEditable(false);
        tpChatbox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane3.setViewportView(tpChatbox);

        btnSendToCoordinator.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSendToCoordinator.setText("Send message");
        btnSendToCoordinator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendToCoordinatorActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HISTORY LOG");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 255, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CHAT BOX");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPort, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(tfSendToCoordinator, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendToCoordinator, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbServerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbServerStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbPort, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSendToCoordinator, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSendToCoordinator, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        if(this.tfPort.equals("")){
            JOptionPane.showMessageDialog(this, "please type number of server port", "warning", JOptionPane.WARNING_MESSAGE);
        }
        else{
            this.sv = new server(this,3000,0,this.tpLog,this.tpChatbox,this.tfSendToCoordinator,this.btnSendToCoordinator);
            this.sv.execute();
            this.lbServerStatus.setText("server is running on port "+tfPort.getText());
            this.btnConnect.setEnabled(false);
            node n = this.sv.getCoordinator();
            if(n == null){
                for(node item:sv.getListNode()){
                    
                }
                sv.bully(3);
            }
            else if(n.getId() == this.sv.getId()){
                System.out.println("day chinh la dieu phoi vien");
            }
            else{
                JOptionPane.showMessageDialog(this, "Coordinator có id: "+n.getId(), "Thông báo", JOptionPane.DEFAULT_OPTION);
                this.tfSendToCoordinator.setEditable(true);
                this.btnSendToCoordinator.setEnabled(true);
            }
        }
        
        
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnSendToCoordinatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendToCoordinatorActionPerformed
        //this.taClientMessage.add("");
        node n = this.sv.getCoordinator();
        if(this.tfSendToCoordinator.getText().equals("")){
            System.out.println("input is empty");
            return;
        }
        if(n == null){
            //sv.bully();
            sv.bully(3);
            System.out.println("Coordinator is null");
            return;
        }
        else{
            this.sv.checkCoordinator(this.tfSendToCoordinator.getText());
            //this.jScrollPane3.getVerticalScrollBar().setValue(Integer.MAX_VALUE);
        }
    }//GEN-LAST:event_btnSendToCoordinatorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FirstNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FirstNode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnSendToCoordinator;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbPort;
    private javax.swing.JLabel lbServerStatus;
    private javax.swing.JTextField tfPort;
    private javax.swing.JTextField tfSendToCoordinator;
    private javax.swing.JTextPane tpChatbox;
    private javax.swing.JTextPane tpLog;
    // End of variables declaration//GEN-END:variables
}
