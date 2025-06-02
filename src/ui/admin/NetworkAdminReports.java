/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.admin;

import Model.EcoSystem;
import Model.Network.Network;
import Model.Enterprise.Enterprise;
import Model.Organization.Organization;
import Model.Role.Role;
import Model.User.UserAccount;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
public class NetworkAdminReports extends javax.swing.JPanel {
    private EcoSystem system;
    Network network;

   public NetworkAdminReports() {
       this.system = EcoSystem.getInstance();
       initComponents();
       populateAllTables();
   }

   private void populateAllTables() {
       populateNetworkTable();
       populateEnterpriseTable();
       populateOrganizationTable();
       populateRolesTable();
   }

   private void populateNetworkTable() {
       DefaultTableModel model = (DefaultTableModel) tblManageRoles1.getModel();
       model.setRowCount(0);
       for (Network network : system.getNetworkDirectory().getNetworkList()) {
           Object[] row = {
               network.getId(),
               network.getName(),
               network.getType(),
               network.getDescription(),
               network.getManager()
           };
           model.addRow(row);
       }
   }

   private void populateEnterpriseTable() {
       DefaultTableModel model = (DefaultTableModel) tblManageEnterprise.getModel();
       model.setRowCount(0);
       for (Network network : system.getNetworkDirectory().getNetworkList()) {
           for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
               Object[] row = {
                   enterprise.getId(),
                   enterprise.getName(),
                   enterprise.getType(),
                   enterprise.getDescription(),
                   enterprise.getManager()
               };
               model.addRow(row);
           }
       }
   }

   private void populateOrganizationTable() {
       DefaultTableModel model = (DefaultTableModel) tblManageOrganization.getModel();
       model.setRowCount(0);
       for (Network network : system.getNetworkDirectory().getNetworkList()) {
           for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
               for (Organization org : enterprise.getOrganizations().getOrganizationList()) {
                   Object[] row = {
                       org.getOrganizationId(),
                       org.getOrganizationName(),
                       org.getClass().getSimpleName(),
                       org.getContactInfo().getLocation(),
                       org.getAdmin() != null ? org.getAdmin().getName() : "N/A"
                   };
                   model.addRow(row);
               }
           }
       }
   }

   private void populateRolesTable() {
       DefaultTableModel model = (DefaultTableModel) tblManageRoles.getModel();
       model.setRowCount(0);
       for (Network network : system.getNetworkDirectory().getNetworkList()) {
           for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
               for (Organization org : enterprise.getOrganizations().getOrganizationList()) {
                   if (org.getRoleDirectory() != null) {
                       for (Role role : org.getRoleDirectory().getRoleList()) {
                           Object[] row = {
                               role.getId(),
                               role.getName(),
                               role.isAdmin() ? "Yes" : "No",
                               role.getDescription(),
                               org.getOrganizationName()
                           };
                           model.addRow(row);
                       }
                   }
               }
           }
       }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnViewDetailsNetwork = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnExportAllToCSV = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblManageEnterprise = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblManageOrganization = new javax.swing.JTable();
        btnViewDetailsOrgnization = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblManageRoles = new javax.swing.JTable();
        btnViewDetailsEnterprise = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblManageRoles1 = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cmbSearchInNetwork = new javax.swing.JComboBox<>();
        cmbSearchInEnterprise = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cmbSearchInOrganization = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbSearchInRoles = new javax.swing.JComboBox<>();

        btnViewDetailsNetwork.setText("View Details");
        btnViewDetailsNetwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsNetworkActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel2.setText("Network Admin Reports");

        btnExportAllToCSV.setText("Export all to csv");
        btnExportAllToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportAllToCSVActionPerformed(evt);
            }
        });

        tblManageEnterprise.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Enterprise ID", "Enterprise Name", "Type", "Description", "Manager"
            }
        ));
        jScrollPane1.setViewportView(tblManageEnterprise);

        tblManageOrganization.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Organization ID", "Organization Name", "Type", "Description", "Manager"
            }
        ));
        jScrollPane2.setViewportView(tblManageOrganization);

        btnViewDetailsOrgnization.setText("View Details");
        btnViewDetailsOrgnization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsOrgnizationActionPerformed(evt);
            }
        });

        tblManageRoles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Roles ID", "Roles Name", "Type", "Description", "Manager"
            }
        ));
        jScrollPane3.setViewportView(tblManageRoles);

        btnViewDetailsEnterprise.setText("View Details");
        btnViewDetailsEnterprise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsEnterpriseActionPerformed(evt);
            }
        });

        tblManageRoles1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Network ID", "Network Name", "Type", "Description", "Manager"
            }
        ));
        jScrollPane4.setViewportView(tblManageRoles1);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel12.setText("Search:");

        cmbSearchInNetwork.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));
        cmbSearchInNetwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearchInNetworkActionPerformed(evt);
            }
        });

        cmbSearchInEnterprise.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));
        cmbSearchInEnterprise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearchInEnterpriseActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel13.setText("Search:");

        cmbSearchInOrganization.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));
        cmbSearchInOrganization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearchInOrganizationActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel14.setText("Search:");

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel15.setText("Search:");

        cmbSearchInRoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));
        cmbSearchInRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearchInRolesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(356, 356, 356))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(cmbSearchInEnterprise, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnViewDetailsEnterprise, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(cmbSearchInNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnViewDetailsNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(cmbSearchInOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnViewDetailsOrgnization, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 601, Short.MAX_VALUE)
                                .addComponent(btnExportAllToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(cmbSearchInRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewDetailsNetwork)
                    .addComponent(cmbSearchInNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewDetailsEnterprise)
                    .addComponent(cmbSearchInEnterprise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewDetailsOrgnization)
                    .addComponent(cmbSearchInOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSearchInRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportAllToCSV)
                    .addComponent(btnBack))
                .addContainerGap(61, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
    // Navigate back to AdminWorkAreaPanel
    JPanel parent = (JPanel) this.getParent();
    if (parent != null) {
        parent.removeAll();
        parent.add(new AdminWorkAreaPanel(parent, 
            EcoSystem.getInstance(), getCurrentUserAccount()));
        parent.validate();
        parent.repaint();
    }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnExportAllToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportAllToCSVActionPerformed
        System.out.println("Exporting all data to CSV...");
    }//GEN-LAST:event_btnExportAllToCSVActionPerformed

    private void cmbSearchInRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchInRolesActionPerformed
        populateRolesTable();
    }//GEN-LAST:event_cmbSearchInRolesActionPerformed

    private void cmbSearchInOrganizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchInOrganizationActionPerformed
        populateOrganizationTable();
    }//GEN-LAST:event_cmbSearchInOrganizationActionPerformed

    private void cmbSearchInNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchInNetworkActionPerformed
        populateNetworkTable();
    }//GEN-LAST:event_cmbSearchInNetworkActionPerformed

    private void btnViewDetailsNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsNetworkActionPerformed
      javax.swing.JOptionPane.showMessageDialog(this,
           "Network: " + network.getName() + "\n" +
           "Type: " + network.getType() + "\n" +
           "Description: " + network.getDescription() + "\n" +
           "Manager: " + network.getManager(),
           "Network Details",
           javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnViewDetailsNetworkActionPerformed

    private void cmbSearchInEnterpriseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchInEnterpriseActionPerformed
        populateEnterpriseTable();
    }//GEN-LAST:event_cmbSearchInEnterpriseActionPerformed

    private void btnViewDetailsEnterpriseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsEnterpriseActionPerformed
       int selectedRow = tblManageEnterprise.getSelectedRow();
       if (selectedRow >= 0) {
           String enterpriseId = (String) tblManageEnterprise.getValueAt(selectedRow, 0);
           Enterprise enterprise = findEnterpriseById(enterpriseId);
           if (enterprise != null) {
               // 显示企业详细信息的对话框或新窗口
               showEnterpriseDetails(enterprise);
           }
       }
    }//GEN-LAST:event_btnViewDetailsEnterpriseActionPerformed

    private void btnViewDetailsOrgnizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsOrgnizationActionPerformed
       int selectedRow = tblManageOrganization.getSelectedRow();
       if (selectedRow >= 0) {
           String orgId = (String) tblManageOrganization.getValueAt(selectedRow, 0);
           Organization org = findOrganizationById(orgId);
           if (org != null) {
               // 显示组织详细信息的对话框或新窗口
               showOrganizationDetails(org);
           }
       }
    }//GEN-LAST:event_btnViewDetailsOrgnizationActionPerformed

   // 辅助方法
   private Enterprise findEnterpriseById(String enterpriseId) {
       for (Network network : system.getNetworkDirectory().getNetworkList()) {
           for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
               if (enterprise.getId().equals(enterpriseId)) {
                   return enterprise;
               }
           }
       }
       return null;
   }

   private Organization findOrganizationById(String orgId) {
       for (Network network : system.getNetworkDirectory().getNetworkList()) {
           for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
               for (Organization org : enterprise.getOrganizations().getOrganizationList()) {
                   if (org.getOrganizationId().equals(orgId)) {
                       return org;
                   }
               }
           }
       }
       return null;
   }

   private void showNetworkDetails(Network network) {
       javax.swing.JOptionPane.showMessageDialog(this,
           "Network: " + network.getName() + "\n" +
           "Type: " + network.getType() + "\n" +
           "Description: " + network.getDescription() + "\n" +
           "Manager: " + network.getManager(),
           "Network Details",
           javax.swing.JOptionPane.INFORMATION_MESSAGE);
   }

   private void showEnterpriseDetails(Enterprise enterprise) {
       javax.swing.JOptionPane.showMessageDialog(this,
           "Enterprise: " + enterprise.getName() + "\n" +
           "Type: " + enterprise.getType() + "\n" +
           "Description: " + enterprise.getDescription() + "\n" +
           "Manager: " + enterprise.getManager(),
           "Enterprise Details",
           javax.swing.JOptionPane.INFORMATION_MESSAGE);
   }

   private void showOrganizationDetails(Organization org) {
       javax.swing.JOptionPane.showMessageDialog(this,
           "Organization: " + org.getOrganizationName() + "\n" +
           "Type: " + org.getClass().getSimpleName() + "\n" +
           "Location: " + org.getContactInfo().getLocation() + "\n" +
           "Admin: " + (org.getAdmin() != null ? org.getAdmin().getName() : "N/A"),
           "Organization Details",
           javax.swing.JOptionPane.INFORMATION_MESSAGE);
   }
   
   private UserAccount getCurrentUserAccount() {
    // Return current user account context
    return null; // Would be injected from constructor
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExportAllToCSV;
    private javax.swing.JButton btnViewDetailsEnterprise;
    private javax.swing.JButton btnViewDetailsNetwork;
    private javax.swing.JButton btnViewDetailsOrgnization;
    private javax.swing.JComboBox<String> cmbSearchInEnterprise;
    private javax.swing.JComboBox<String> cmbSearchInNetwork;
    private javax.swing.JComboBox<String> cmbSearchInOrganization;
    private javax.swing.JComboBox<String> cmbSearchInRoles;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblManageEnterprise;
    private javax.swing.JTable tblManageOrganization;
    private javax.swing.JTable tblManageRoles;
    private javax.swing.JTable tblManageRoles1;
    // End of variables declaration//GEN-END:variables
}
