/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.DonationCoordinator;

import Model.Organization.Organization;
import Model.User.UserAccount;
import Model.Supplies.DonationCatalog;
import Model.EcoSystem; // Needed for BrowsePublicData
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 *
 * @author tiankaining
 */
public class DonationCoordinatorWorkAreaPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private EcoSystem system;

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public DonationCoordinatorWorkAreaPanel(JPanel userProcessContainer, Organization organization, UserAccount userAccount) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.system = EcoSystem.getInstance(); // Assuming EcoSystem is a singleton

        initComponents();
        initContentPanel();
    }

    private void initContentPanel() {
        contentPanel = new JPanel(new CardLayout());
        this.cardLayout = (CardLayout) contentPanel.getLayout();

        // Assuming organization has a DonationCatalog
        DonationCatalog donationCatalog = organization.getDonationCatalog(); // Placeholder

        // Add sub-panels to the contentPanel
        contentPanel.add("SubmitDonation", new SubmitDonation(userProcessContainer, organization, userAccount, donationCatalog));
        contentPanel.add("ViewDonationHistory", new ViewDonationHistory(userProcessContainer, organization, userAccount, donationCatalog));
        contentPanel.add("ManageDonations", new ManageDonations(userProcessContainer, organization, userAccount, donationCatalog));
        contentPanel.add("BrowsePublicData", new BrowsePublicData(userProcessContainer, organization, userAccount, system));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDonationStatsChart = new javax.swing.JButton();
        btnBrowsePublicData = new javax.swing.JButton();
        btnManageDonations = new javax.swing.JButton();
        btnSubmitDonations = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnViewDonationHistory = new javax.swing.JButton();

        btnDonationStatsChart.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnDonationStatsChart.setText("Donation Stats Chart");
        btnDonationStatsChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonationStatsChartActionPerformed(evt);
            }
        });

        btnBrowsePublicData.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnBrowsePublicData.setText("Browse Public Data");
        btnBrowsePublicData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowsePublicDataActionPerformed(evt);
            }
        });

        btnManageDonations.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnManageDonations.setText("Manage Donations");
        btnManageDonations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageDonationsActionPerformed(evt);
            }
        });

        btnSubmitDonations.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnSubmitDonations.setText("Submit Donations");
        btnSubmitDonations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitDonationsActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Donation Coordinator WorkArea");

        btnViewDonationHistory.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnViewDonationHistory.setText("View Donation History");
        btnViewDonationHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDonationHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnSubmitDonations, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnViewDonationHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBrowsePublicData, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(btnManageDonations, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(btnDonationStatsChart, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel1)
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowsePublicData, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewDonationHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmitDonations, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDonationStatsChart, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageDonations, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(225, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitDonationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitDonationsActionPerformed
        // Navigate to SubmitDonation
        userProcessContainer.add("SubmitDonation", contentPanel.getComponent(0));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnSubmitDonationsActionPerformed

    private void btnViewDonationHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDonationHistoryActionPerformed
        // Navigate to ViewDonationHistory
        userProcessContainer.add("ViewDonationHistory", contentPanel.getComponent(1));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnViewDonationHistoryActionPerformed

    private void btnBrowsePublicDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowsePublicDataActionPerformed
        // Navigate to BrowsePublicData
        userProcessContainer.add("BrowsePublicData", contentPanel.getComponent(3));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnBrowsePublicDataActionPerformed

    private void btnManageDonationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageDonationsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManageDonationsActionPerformed

    private void btnDonationStatsChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonationStatsChartActionPerformed
        // Navigate to ManageDonations
        userProcessContainer.add("ManageDonations", contentPanel.getComponent(2));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnDonationStatsChartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowsePublicData;
    private javax.swing.JButton btnDonationStatsChart;
    private javax.swing.JButton btnManageDonations;
    private javax.swing.JButton btnSubmitDonations;
    private javax.swing.JButton btnViewDonationHistory;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
