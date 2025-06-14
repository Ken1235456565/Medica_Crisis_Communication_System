/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.DeliveryStaff;

import Model.Organization.Organization;
import Model.Organization.SupplyChainManagementUnit;
import Model.User.UserAccount;
import Model.Supplies.DeliveryCatalog;
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 *
 * @author tiankaining
 */
public class DeliveryStaffWorkAreaPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    SupplyChainManagementUnit supplyChainManagementUnit;

    private CardLayout cardLayout;
    private JPanel contentPanel;

public DeliveryStaffWorkAreaPanel(JPanel userProcessContainer, Organization organization, UserAccount userAccount) {
    this.userProcessContainer = userProcessContainer;
    this.organization = organization;
    this.userAccount = userAccount;

    this.supplyChainManagementUnit = (SupplyChainManagementUnit) organization;

    initComponents();
    initContentPanel();
}
    
    private void initContentPanel() {
        contentPanel = new JPanel(new CardLayout());
        this.cardLayout = (CardLayout) contentPanel.getLayout();

        // Assuming organization has methods to get its directories/catalogs
        DeliveryCatalog deliveryCatalog = supplyChainManagementUnit.getDeliveryCatalog(); // Placeholder

        // Add sub-panels to the contentPanel
        contentPanel.add("TaskInstructions", new TaskInstructions(userProcessContainer, organization, userAccount, deliveryCatalog));
        contentPanel.add("ManageDeliveryTasks", new ManageDeliveryTasks(userProcessContainer, organization, userAccount, deliveryCatalog));
         // UploadProof
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnTaskInstructions = new javax.swing.JButton();
        btnDeliverySummary = new javax.swing.JButton();
        btnManageTask = new javax.swing.JButton();
        btnUploadProof = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Delivery Staff WorkArea");

        btnTaskInstructions.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnTaskInstructions.setText("Task Instructions");
        btnTaskInstructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaskInstructionsActionPerformed(evt);
            }
        });

        btnDeliverySummary.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnDeliverySummary.setText("Delivery Summary");
        btnDeliverySummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeliverySummaryActionPerformed(evt);
            }
        });

        btnManageTask.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnManageTask.setText("Manage Delivery Task");
        btnManageTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageTaskActionPerformed(evt);
            }
        });

        btnUploadProof.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnUploadProof.setText("Upload Proof");
        btnUploadProof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadProofActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUploadProof, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDeliverySummary, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTaskInstructions, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(btnManageTask, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(jLabel1)))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel1)
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaskInstructions, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageTask, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeliverySummary, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUploadProof, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(233, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaskInstructionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaskInstructionsActionPerformed
        // Navigate to TaskInstructions
        userProcessContainer.add("TaskInstructions", contentPanel.getComponent(0));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnTaskInstructionsActionPerformed

    private void btnManageTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageTaskActionPerformed
        // Navigate to ManageDeliveryTasks
        userProcessContainer.add("ManageDeliveryTasks", contentPanel.getComponent(1));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnManageTaskActionPerformed

    private void btnUploadProofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadProofActionPerformed
        // Navigate to UploadProof (Note: This panel likely needs a selected delivery, which isn't handled here)
         userProcessContainer.add("UploadProof", new UploadProof(userProcessContainer, organization, userAccount, null));
         ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnUploadProofActionPerformed

    private void btnDeliverySummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeliverySummaryActionPerformed
        // caculation button:
    }//GEN-LAST:event_btnDeliverySummaryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeliverySummary;
    private javax.swing.JButton btnManageTask;
    private javax.swing.JButton btnTaskInstructions;
    private javax.swing.JButton btnUploadProof;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
