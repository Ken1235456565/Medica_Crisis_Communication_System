/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.HospitalDoctor;

import Model.Organization.Organization;
import Model.Personnel.Doctor;
import Model.Patient.PatientDirectory;
import Model.Supplies.SupplyItemCatalog;
import Model.Supplies.ICUbedCatalog;
import Model.Employee.EmployeeDirectory;
import Model.User.UserAccount;
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 *
 * @author tiankaining
 */
public class HospitalDoctorWorkAreaPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private Doctor doctor; // The logged-in doctor
    
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public HospitalDoctorWorkAreaPanel(JPanel userProcessContainer, Organization organization, UserAccount userAccount) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.doctor = (Doctor) userAccount.getEmployee(); // Or similar logic to get the doctor object
        initComponents();
        initContentPanel();
    }
    
    private void initContentPanel() {
        contentPanel = new JPanel(new CardLayout());
        this.cardLayout = (CardLayout) contentPanel.getLayout();

        // Assuming organization has methods to get its directories/catalogs
        PatientDirectory patientDirectory = organization.getPatientDirectory(); // Placeholder method
        SupplyItemCatalog supplyCatalog = organization.getSupplyItemCatalog(); // Placeholder method
        ICUbedCatalog icuBedCatalog = organization.getICUbedCatalog(); // Placeholder method
        EmployeeDirectory employeeDirectory = organization.getEmployeeDirectory(); // Placeholder method


        // Add sub-panels to the contentPanel
        contentPanel.add("ManagePatientList", new ManagePatientList(userProcessContainer, organization, userAccount, patientDirectory));
        contentPanel.add("ManageICURequest", new ManageICURequest(userProcessContainer, organization, userAccount, patientDirectory, icuBedCatalog));
        contentPanel.add("ManageMedicalRequest", new ManageMedicalRequest(userProcessContainer, organization, userAccount, patientDirectory, supplyCatalog));
        contentPanel.add("ViewOnDutyHistory", new ViewOnDutyHistory(userProcessContainer, organization, userAccount, employeeDirectory));
        // contentPanel.add("PersonalHistorylinked", new PersonalHistorylinked(userProcessContainer, organization, userAccount, selectedPatient)); // Requires a selected patient
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
        btnViewPatientList = new javax.swing.JButton();
        btnSubmitICURequest = new javax.swing.JButton();
        btnSubmitMedicalOrder = new javax.swing.JButton();
        btnDutyHistory = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Hospital Doctor WorkArea");

        btnViewPatientList.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnViewPatientList.setText("Manage Patient List");
        btnViewPatientList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPatientListActionPerformed(evt);
            }
        });

        btnSubmitICURequest.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnSubmitICURequest.setText("Manage ICU Request");
        btnSubmitICURequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitICURequestActionPerformed(evt);
            }
        });

        btnSubmitMedicalOrder.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnSubmitMedicalOrder.setText("Manage Medical Order");
        btnSubmitMedicalOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitMedicalOrderActionPerformed(evt);
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
                                .addComponent(btnSubmitICURequest, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnViewPatientList, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(btnSubmitMedicalOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(btnViewPatientList, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmitMedicalOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmitICURequest, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDutyHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(231, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewPatientListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPatientListActionPerformed
        // Navigate to ManagePatientList
        userProcessContainer.add("ManagePatientList", contentPanel.getComponent(0)); 
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnViewPatientListActionPerformed

    private void btnSubmitICURequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitICURequestActionPerformed
        // Navigate to ManageICURequest
        userProcessContainer.add("ManageICURequest", contentPanel.getComponent(1)); 
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnSubmitICURequestActionPerformed

    private void btnSubmitMedicalOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitMedicalOrderActionPerformed
        // Navigate to ManageMedicalRequest
        userProcessContainer.add("ManageMedicalOrder", contentPanel.getComponent(2)); 
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnSubmitMedicalOrderActionPerformed

    private void btnDutyHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDutyHistoryActionPerformed
        // Navigate to ViewOnDutyHistory
        userProcessContainer.add("ViewOnDutyHistory", contentPanel.getComponent(3)); 
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnDutyHistoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDutyHistory;
    private javax.swing.JButton btnSubmitICURequest;
    private javax.swing.JButton btnSubmitMedicalOrder;
    private javax.swing.JButton btnViewPatientList;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
