/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.EquipmentTechnician;

import Model.Organization.Organization;
import Model.User.UserAccount;
import Model.Supplies.EquipmentsCatalog;
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 *
 * @author tiankaining
 */
public class EquipmentTechnicianWorkAreaPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public EquipmentTechnicianWorkAreaPanel(JPanel userProcessContainer, Organization organization, UserAccount userAccount) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;

        initComponents();
        initContentPanel();
    }

    private void initContentPanel() {
        contentPanel = new JPanel(new CardLayout());
        this.cardLayout = (CardLayout) contentPanel.getLayout();

        // Assuming organization has methods to get its directories/catalogs
        EquipmentsCatalog equipmentsCatalog = organization.getEquipmentCatalog(); // Placeholder

        // Add sub-panels to the contentPanel
        contentPanel.add("ReportIssue", new ReportIssue(userProcessContainer, organization, userAccount, equipmentsCatalog));
        contentPanel.add("ManageRepairStatus", new ManageRepairStatus(userProcessContainer, organization, userAccount, equipmentsCatalog));
        contentPanel.add("EquipmentStatusChart", new EquipmentStatusChart(userProcessContainer, organization, userAccount, equipmentsCatalog));
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
        btnRepairStatusUpdate = new javax.swing.JButton();
        btnEquipmentStatsChart = new javax.swing.JButton();
        btnReportIssue = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Equipment Technician WorkArea");

        btnRepairStatusUpdate.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnRepairStatusUpdate.setText("Repair Status Update");
        btnRepairStatusUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepairStatusUpdateActionPerformed(evt);
            }
        });

        btnEquipmentStatsChart.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnEquipmentStatsChart.setText("Equipment Stats Chart");
        btnEquipmentStatsChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEquipmentStatsChartActionPerformed(evt);
            }
        });

        btnReportIssue.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnReportIssue.setText("Report Issue");
        btnReportIssue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportIssueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(325, 325, 325))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnReportIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnRepairStatusUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEquipmentStatsChart, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addGap(222, 222, 222)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEquipmentStatsChart, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRepairStatusUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(345, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRepairStatusUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepairStatusUpdateActionPerformed
        // Navigate to ManageRepairStatus
        userProcessContainer.add("RepairStatusUpdate", contentPanel.getComponent(1));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnRepairStatusUpdateActionPerformed

    private void btnEquipmentStatsChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEquipmentStatsChartActionPerformed
        // Navigate to EquipmentStatusChart
        userProcessContainer.add("EquipmentStatsChart", contentPanel.getComponent(2));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnEquipmentStatsChartActionPerformed

    private void btnReportIssueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportIssueActionPerformed
        // Navigate to ReportIssue
        userProcessContainer.add("ReportIssue", contentPanel.getComponent(0));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnReportIssueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEquipmentStatsChart;
    private javax.swing.JButton btnRepairStatusUpdate;
    private javax.swing.JButton btnReportIssue;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
