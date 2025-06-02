/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.HospitalDoctor;

import Model.EcoSystem;
import Model.Employee.Employee;
import Model.Organization.Organization;
import Model.Personnel.Doctor;
import Model.Patient.PatientDirectory;
import Model.Supplies.SupplyItemCatalog;
import Model.Supplies.ICUbedCatalog;
import Model.Employee.EmployeeDirectory;
import Model.Enterprise.Enterprise;
import Model.Organization.ClinicalServicesUnit;
import Model.Organization.SupplyChainManagementUnit;
import Model.Patient.Patient;
import Model.User.UserAccount;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.util.List;

/**
 *
 * @author tiankaining
 */
public class HospitalDoctorWorkAreaPanel extends javax.swing.JPanel {


    private JPanel userProcessContainer;
    private ClinicalServicesUnit clinicalOrg;
    private UserAccount userAccount;
    private Enterprise enterprise;
    private EcoSystem ecoSystem;
    private final java.util.Set<String> addedPanels = new java.util.HashSet<>();

public HospitalDoctorWorkAreaPanel(JPanel userProcessContainer, Organization organization, UserAccount userAccount) {
    this.userProcessContainer = userProcessContainer;
    this.clinicalOrg = (ClinicalServicesUnit) organization;
    this.userAccount = userAccount;
    this.enterprise = (Enterprise) organization.getEnterprise();
    this.ecoSystem = ecoSystem;    

    initComponents();
    
    // 确保主面板被添加到CardLayout中
    if (!addedPanels.contains("HospitalDoctorWorkAreaPanel")) {
        userProcessContainer.add("HospitalDoctorWorkAreaPanel", this);
        addedPanels.add("HospitalDoctorWorkAreaPanel");
    }
}
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnPatientList = new javax.swing.JButton();
        btnICURequest = new javax.swing.JButton();
        btnMedicalRequest = new javax.swing.JButton();
        btnDutyHistory = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Hospital Doctor WorkArea");

        btnPatientList.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnPatientList.setText("Manage Patient List");
        btnPatientList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPatientListActionPerformed(evt);
            }
        });

        btnICURequest.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnICURequest.setText("Manage ICU Request");
        btnICURequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnICURequestActionPerformed(evt);
            }
        });

        btnMedicalRequest.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnMedicalRequest.setText("Manage Medical Order");
        btnMedicalRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicalRequestActionPerformed(evt);
            }
        });

        btnDutyHistory.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnDutyHistory.setText("Duty History");
        btnDutyHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDutyHistoryActionPerformed(evt);
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
                                .addComponent(btnDutyHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnICURequest, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPatientList, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(btnMedicalRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(jLabel1)))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel1)
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPatientList, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMedicalRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnICURequest, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDutyHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(231, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPatientListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPatientListActionPerformed
    PatientDirectory patientDirectory = clinicalOrg.getPatientDirectory();
    ManagePatientList panel = new ManagePatientList(userProcessContainer, clinicalOrg, userAccount, patientDirectory);
    switchToPanel("ManagePatientList", panel);
    }//GEN-LAST:event_btnPatientListActionPerformed

    private void btnICURequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnICURequestActionPerformed
    PatientDirectory patientDirectory = clinicalOrg.getPatientDirectory();
    ICUbedCatalog icuBedCatalog = clinicalOrg.getICUbedCatalog();
    ManageICURequest panel = new ManageICURequest(userProcessContainer, clinicalOrg, userAccount, patientDirectory, icuBedCatalog);
    switchToPanel("ManageICURequest", panel);
    }//GEN-LAST:event_btnICURequestActionPerformed

    private void btnMedicalRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicalRequestActionPerformed
    PatientDirectory patientDirectory = clinicalOrg.getPatientDirectory();
    SupplyItemCatalog supplyCatalog = clinicalOrg.getSupplyItemCatalog();
    ManageMedicalRequest panel = new ManageMedicalRequest(userProcessContainer, clinicalOrg, userAccount, patientDirectory, supplyCatalog);
    switchToPanel("ManageMedicalRequest", panel);
    }//GEN-LAST:event_btnMedicalRequestActionPerformed

    private void btnDutyHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDutyHistoryActionPerformed
    EmployeeDirectory employeeDirectory = clinicalOrg.getEmployeeDirectory();
    ViewOnDutyHistory panel = new ViewOnDutyHistory(userProcessContainer, clinicalOrg, userAccount, employeeDirectory);
    switchToPanel("ViewOnDutyHistory", panel);
    }//GEN-LAST:event_btnDutyHistoryActionPerformed
private void switchToPanel(String panelName, JPanel panel) {
    if (!addedPanels.contains(panelName)) {
        userProcessContainer.add(panelName, panel);
        addedPanels.add(panelName);
    }
    ((CardLayout) userProcessContainer.getLayout()).show(userProcessContainer, panelName);
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDutyHistory;
    private javax.swing.JButton btnICURequest;
    private javax.swing.JButton btnMedicalRequest;
    private javax.swing.JButton btnPatientList;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
