/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.admin;

import Model.EcoSystem;
import Model.User.UserAccount;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author tiankaining
 */
public class AdminWorkAreaPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public AdminWorkAreaPanel(JPanel userProcessContainer, EcoSystem system, UserAccount userAccount) {
        this.userProcessContainer = userProcessContainer;
        this.system = system;
        this.userAccount = userAccount;

        initComponents(); // 初始化按钮等UI
        initContentPanel(); // 初始化内容页容器
    }

    private void initContentPanel() {
        contentPanel = new JPanel(new CardLayout());
        this.cardLayout = (CardLayout) contentPanel.getLayout();

        // 加载所有子页面
        contentPanel.add("Network", new ManageNetwork(userProcessContainer, system));
        contentPanel.add("Enterprise", new ManageEnterprise(userProcessContainer, getDefaultNetwork()));
        contentPanel.add("Organization", new ManageOrganization(userProcessContainer, getDefaultOrganizationDirectory(), getDefaultEnterprise()));
        contentPanel.add("User", new ManageUserAccounts(userProcessContainer, getDefaultOrganization()));
        contentPanel.add("NetworkReports", new NetworkAdminReports());
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
        btnManageUserAccounts = new javax.swing.JButton();
        btnNetworkAdminReports = new javax.swing.JButton();
        btnManageOrganization = new javax.swing.JButton();
        btnManageNetwork = new javax.swing.JButton();
        btnManageEnterprise = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Admin Workarea");

        btnManageUserAccounts.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnManageUserAccounts.setText("Manage User Accounts");
        btnManageUserAccounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageUserAccountsActionPerformed(evt);
            }
        });

        btnNetworkAdminReports.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnNetworkAdminReports.setText("Network Admin Reports");
        btnNetworkAdminReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNetworkAdminReportsActionPerformed(evt);
            }
        });

        btnManageOrganization.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnManageOrganization.setText("Manage Organization");
        btnManageOrganization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageOrganizationActionPerformed(evt);
            }
        });

        btnManageNetwork.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnManageNetwork.setText("Manage Network");
        btnManageNetwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageNetworkActionPerformed(evt);
            }
        });

        btnManageEnterprise.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnManageEnterprise.setText("Manage Enterprise");
        btnManageEnterprise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageEnterpriseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnManageOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnManageUserAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNetworkAdminReports, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(btnManageNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnManageEnterprise, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(jLabel1)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnManageNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageEnterprise, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNetworkAdminReports, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageUserAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(287, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnManageUserAccountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageUserAccountsActionPerformed
        // TODO add your handling code here:
        cardLayout.show(contentPanel, "User");
    }//GEN-LAST:event_btnManageUserAccountsActionPerformed

    private void btnNetworkAdminReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNetworkAdminReportsActionPerformed
        // TODO add your handling code here:
        cardLayout.show(contentPanel, "NetworkReports");
    }//GEN-LAST:event_btnNetworkAdminReportsActionPerformed

    private void btnManageOrganizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageOrganizationActionPerformed
        // TODO add your handling code here:
        cardLayout.show(contentPanel, "Organization");
    }//GEN-LAST:event_btnManageOrganizationActionPerformed

    private void btnManageNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageNetworkActionPerformed
        // TODO add your handling code here:
        cardLayout.show(contentPanel, "Network");
    }//GEN-LAST:event_btnManageNetworkActionPerformed

    private void btnManageEnterpriseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageEnterpriseActionPerformed
        // TODO add your handling code here:
        cardLayout.show(contentPanel, "Enterprise");
    }//GEN-LAST:event_btnManageEnterpriseActionPerformed
// 获取系统中第一个 Network（必须已存在）
private Model.Network.Network getDefaultNetwork() {
    if (system.getNetworkDirectory().getNetworkList() != null &&
    !system.getNetworkDirectory().getNetworkList().isEmpty()) {
    return system.getNetworkDirectory().getNetworkList().get(0);
    }
    return null; // 或者 throw new IllegalStateException("No network found");
}

// 获取第一个 Enterprise（必须已存在）
private Model.Enterprise.Enterprise getDefaultEnterprise() {
    Model.Network.Network network = getDefaultNetwork();
    if (network != null && network.getEnterpriseDirectory() != null &&
        !network.getEnterpriseDirectory().getEnterpriseList().isEmpty()) {
        return network.getEnterpriseDirectory().getEnterpriseList().get(0);
    }
    return null;
}

// 获取 OrganizationDirectory（来自第一个 Enterprise）
private Model.Organization.OrganizationDirectory getDefaultOrganizationDirectory() {
    Model.Enterprise.Enterprise enterprise = getDefaultEnterprise();
    if (enterprise != null) {
        return enterprise.getOrganizations();
    }
    return null;
}

// 获取第一个 Organization（必须已存在）
private Model.Organization.Organization getDefaultOrganization() {
    Model.Organization.OrganizationDirectory orgDir = getDefaultOrganizationDirectory();
    if (orgDir != null && !orgDir.getOrganizationList().isEmpty()) {
        return orgDir.getOrganizationList().get(0);
    }
    return null;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnManageEnterprise;
    private javax.swing.JButton btnManageNetwork;
    private javax.swing.JButton btnManageOrganization;
    private javax.swing.JButton btnManageUserAccounts;
    private javax.swing.JButton btnNetworkAdminReports;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
