/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.admin;

/**
 *
 * @author tiankaining
 */
public class NetworkAdminReports extends javax.swing.JPanel {

    /**
     * Creates new form AdminReportsPanel
     */
    public NetworkAdminReports() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        tblManageEnterprise.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblManageEnterpriseAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
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
        tblManageOrganization.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblManageOrganizationAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
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
        tblManageRoles.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblManageRolesAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
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
        tblManageRoles1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblManageRoles1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
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
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(cmbSearchInOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnViewDetailsOrgnization, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExportAllToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnExportAllToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportAllToCSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportAllToCSVActionPerformed

    private void cmbSearchInRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchInRolesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSearchInRolesActionPerformed

    private void cmbSearchInOrganizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchInOrganizationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSearchInOrganizationActionPerformed

    private void tblManageRoles1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblManageRoles1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblManageRoles1AncestorAdded

    private void cmbSearchInNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchInNetworkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSearchInNetworkActionPerformed

    private void btnViewDetailsNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsNetworkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewDetailsNetworkActionPerformed

    private void tblManageEnterpriseAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblManageEnterpriseAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblManageEnterpriseAncestorAdded

    private void cmbSearchInEnterpriseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchInEnterpriseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSearchInEnterpriseActionPerformed

    private void btnViewDetailsEnterpriseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsEnterpriseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewDetailsEnterpriseActionPerformed

    private void tblManageOrganizationAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblManageOrganizationAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblManageOrganizationAncestorAdded

    private void btnViewDetailsOrgnizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsOrgnizationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewDetailsOrgnizationActionPerformed

    private void tblManageRolesAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblManageRolesAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblManageRolesAncestorAdded


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
