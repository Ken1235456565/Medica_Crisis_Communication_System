/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.HospitalDoctor;

import Model.Organization.Organization;
import Model.Patient.PatientDirectory;
import Model.Patient.Patient;
import Model.Supplies.ICUbedCatalog;
import Model.Supplies.ICUbed;
import Model.User.UserAccount;
import Model.WorkQueue.ICURequest;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author tiankaining
 */
public class ManageICURequest extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private PatientDirectory patientDirectory; // List of patients to manage
    private ICUbedCatalog icuBedCatalog; // List of ICU beds to manage
    private List<ICURequest> icuRequestList; // List of ICU requests
    private ICURequest selectedRequest; // Currently selected request
    private DefaultTableModel tableModel;

    public ManageICURequest(JPanel userProcessContainer, Organization organization, UserAccount userAccount, PatientDirectory patientDirectory, ICUbedCatalog icuBedCatalog) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.patientDirectory = patientDirectory;
        this.icuBedCatalog = icuBedCatalog;
        this.icuRequestList = new ArrayList<>();
        this.selectedRequest = null;
        initComponents();
        initializeTable();
        setupEventHandlers();
    }

    private void initializeTable() {
        String[] columnNames = {"Request ID", "Patient Name", "Admission Date", "ICU Reason", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        tblICURequestHistory.setModel(tableModel);
        refreshTable();
    }

    private void setupEventHandlers() {
        // Add table selection listener
        tblICURequestHistory.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblICURequestHistory.getSelectedRow();
                if (selectedRow >= 0) {
                    selectedRequest = icuRequestList.get(selectedRow);
                    populateViewFields();
                }
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (ICURequest request : icuRequestList) {
            Object[] row = {
                request.getRequestId(),
                request.getPatient() != null ? request.getPatient().getName() : "Unknown",
                formatDate(request.getRequestedDate()),
                request.getIcuReason(),
                request.getStatus()
            };
            tableModel.addRow(row);
        }
    }

    private void populateViewFields() {
        if (selectedRequest != null) {
            txtviewPatientName.setText(selectedRequest.getPatient() != null ? selectedRequest.getPatient().getName() : "");
            txtviewAdmissionDate.setText(formatDate(selectedRequest.getRequestedDate()));
            CmbviewUrgency.setSelectedItem(getUrgencyFromRequest(selectedRequest));
            txtviewICUReason.setText(selectedRequest.getIcuReason() != null ? selectedRequest.getIcuReason() : "");
            txtviewDiagnosis.setText(""); // This field might need to be added to ICURequest
            txtviewTreatmentPlan.setText(""); // This field might need to be added to ICURequest
        }
    }

    private void clearCreateFields() {
        txtcreatePatientName.setText("");
        txtcreateAdmissionDate.setText("");
        CmbcreateUrgency.setSelectedIndex(0);
        txtIcreateICUReason.setText("");
        txtcreateDiagnosis.setText("");
        txtcreateTreatmentPlan.setText("");
    }

    private void clearViewFields() {
        txtviewPatientName.setText("");
        txtviewAdmissionDate.setText("");
        CmbviewUrgency.setSelectedIndex(0);
        txtviewICUReason.setText("");
        txtviewDiagnosis.setText("");
        txtviewTreatmentPlan.setText("");
    }

    private String formatDate(Date date) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr.trim());
        } catch (ParseException e) {
            return null;
        }
    }

    private String getUrgencyFromRequest(ICURequest request) {
        // This is a placeholder - you might need to add urgency to ICURequest
        return "Medium";
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnViewDetails = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblICURequestHistory = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnModify = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        CmbviewUrgency = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtviewAdmissionDate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtviewDiagnosis = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcreatePatientName = new javax.swing.JTextField();
        txtviewPatientName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtcreateDiagnosis = new javax.swing.JTextField();
        txtcreateAdmissionDate = new javax.swing.JTextField();
        txtcreateTreatmentPlan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtviewICUReason = new javax.swing.JTextField();
        txtIcreateICUReason = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        CmbcreateUrgency = new javax.swing.JComboBox<>();
        txtviewTreatmentPlan = new javax.swing.JTextField();
        btnExportToCSV = new javax.swing.JButton();

        btnViewDetails.setText("View Details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Manage ICU Request");

        tblICURequestHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Request ID", "Patient Name", "Admission Date", "ICU Reason"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblICURequestHistory);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel9.setText("ICU Request History:");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnModify.setText("Modify");
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel16.setText("View Details:");

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel15.setText("ICU Reason:");

        CmbviewUrgency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Medical supplies", "food", "daily necessities", "money" }));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText("Patient Name:");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setText("Admission Date:");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setText("Treatment Plan:");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("Urgency:");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Diagnosis:");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setText("ICU Reason:");

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel12.setText("Treatment Plan:");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel11.setText("Admission Date:");

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel10.setText("Patient Name:");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Create Report:");

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel14.setText("Diagnosis:");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel13.setText("Urgency:");

        CmbcreateUrgency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Medical supplies", "food", "daily necessities", "money" }));

        btnExportToCSV.setText("Export to csv");
        btnExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtcreateDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtcreateTreatmentPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(txtIcreateICUReason, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCreate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(81, 81, 81)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtcreateAdmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(CmbcreateUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnExportToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtcreatePatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtviewDiagnosis)
                                        .addComponent(txtviewTreatmentPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(txtviewICUReason, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(81, 81, 81)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtviewAdmissionDate)
                                        .addComponent(CmbviewUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtviewPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(286, 286, 286)
                            .addComponent(jLabel1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(96, 96, 96))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewDetails)
                    .addComponent(btnDelete)
                    .addComponent(btnExportToCSV))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcreateAdmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(CmbcreateUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtIcreateICUReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtcreateDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtcreateTreatmentPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtviewPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcreatePatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtviewAdmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(CmbviewUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtviewICUReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtviewDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtviewTreatmentPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreate)
                    .addComponent(btnModify))
                .addGap(25, 25, 25)
                .addComponent(btnBack)
                .addContainerGap(121, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedRequest == null) {
            JOptionPane.showMessageDialog(this, "Please select an ICU request to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this ICU request?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            icuRequestList.remove(selectedRequest);
            selectedRequest = null;
            refreshTable();
            clearViewFields();
            JOptionPane.showMessageDialog(this, "ICU request deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
       // Validate input fields
        if (!validateCreateFields()) {
            return;
        }

        try {
            String patientName = txtcreatePatientName.getText().trim();
            String admissionDateStr = txtcreateAdmissionDate.getText().trim();
            String urgency = (String) CmbcreateUrgency.getSelectedItem();
            String icuReason = txtIcreateICUReason.getText().trim();
            String diagnosis = txtcreateDiagnosis.getText().trim();
            String treatmentPlan = txtcreateTreatmentPlan.getText().trim();

            // Find patient
            Patient patient = patientDirectory.findPatientByName(patientName);
            if (patient == null) {
                int create = JOptionPane.showConfirmDialog(this,
                        "Patient not found. Do you want to create a new patient?",
                        "Patient Not Found",
                        JOptionPane.YES_NO_OPTION);
                if (create == JOptionPane.YES_OPTION) {
                    patient = patientDirectory.createPatient(patientName, "Unknown", 0, "Unknown");
                } else {
                    return;
                }
            }

            // Parse admission date
            Date admissionDate = parseDate(admissionDateStr);
            if (admissionDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid admission date (yyyy-MM-dd).", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create ICU request
            ICURequest newRequest = new ICURequest(patient, icuReason);
            newRequest.setRequestedDate(admissionDate);
            
            // Add to list
            icuRequestList.add(newRequest);
            
            // Refresh table
            refreshTable();
            
            // Clear form
            clearCreateFields();
            
            JOptionPane.showMessageDialog(this, "ICU request created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating ICU request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateActionPerformed
    private boolean validateCreateFields() {
        if (txtcreatePatientName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter patient name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtcreateAdmissionDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter admission date.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtIcreateICUReason.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter ICU reason.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        if (selectedRequest == null) {
            JOptionPane.showMessageDialog(this, "Please select an ICU request to modify.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String patientName = txtviewPatientName.getText().trim();
            String admissionDateStr = txtviewAdmissionDate.getText().trim();
            String urgency = (String) CmbviewUrgency.getSelectedItem();
            String icuReason = txtviewICUReason.getText().trim();
            String diagnosis = txtviewDiagnosis.getText().trim();
            String treatmentPlan = txtviewTreatmentPlan.getText().trim();

            // Validate fields
            if (patientName.isEmpty() || admissionDateStr.isEmpty() || icuReason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse date
            Date admissionDate = parseDate(admissionDateStr);
            if (admissionDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid admission date (yyyy-MM-dd).", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update request
            selectedRequest.setIcuReason(icuReason);
            selectedRequest.setRequestedDate(admissionDate);

            // Refresh table
            refreshTable();

            JOptionPane.showMessageDialog(this, "ICU request modified successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error modifying ICU request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        if (selectedRequest == null) {
            JOptionPane.showMessageDialog(this, "Please select an ICU request to view details.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder details = new StringBuilder();
        details.append("ICU Request Details:\n\n");
        details.append("Request ID: ").append(selectedRequest.getRequestId()).append("\n");
        details.append("Patient: ").append(selectedRequest.getPatient() != null ? selectedRequest.getPatient().getName() : "Unknown").append("\n");
        details.append("Requested Date: ").append(formatDate(selectedRequest.getRequestedDate())).append("\n");
        details.append("ICU Reason: ").append(selectedRequest.getIcuReason()).append("\n");
        details.append("Status: ").append(selectedRequest.getStatus()).append("\n");
        details.append("Attending Physician: ").append(selectedRequest.getAttendingPhysician() != null ? selectedRequest.getAttendingPhysician() : "Not Assigned").append("\n");
        details.append("Assigned Bed: ").append(selectedRequest.getBedId() != null ? selectedRequest.getBedId() : "Not Assigned").append("\n");

        JOptionPane.showMessageDialog(this, details.toString(), "ICU Request Details", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnViewDetailsActionPerformed

    private void btnExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVActionPerformed
        if (icuRequestList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No ICU requests to export.", "No Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String fileName = "icu_requests_" + System.currentTimeMillis() + ".csv";
            FileWriter writer = new FileWriter(fileName);

            // Write CSV header
            writer.append("Request ID,Patient Name,Requested Date,ICU Reason,Status,Attending Physician,Assigned Bed\n");

            // Write data
            for (ICURequest request : icuRequestList) {
                writer.append(escapeCSV(request.getRequestId())).append(",");
                writer.append(escapeCSV(request.getPatient() != null ? request.getPatient().getName() : "")).append(",");
                writer.append(escapeCSV(formatDate(request.getRequestedDate()))).append(",");
                writer.append(escapeCSV(request.getIcuReason())).append(",");
                writer.append(escapeCSV(request.getStatus())).append(",");
                writer.append(escapeCSV(request.getAttendingPhysician() != null ? request.getAttendingPhysician() : "")).append(",");
                writer.append(escapeCSV(request.getBedId() != null ? request.getBedId() : "")).append("\n");
            }

            writer.close();
            JOptionPane.showMessageDialog(this, "ICU requests exported to: " + fileName, "Export Successful", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting data: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExportToCSVActionPerformed
private String escapeCSV(String value) {
    if (value == null) {
        return "";
    }
    String escaped = value;
    if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
        escaped = "\"" + value.replace("\"", "\"\"") + "\"";
    }
    return escaped;
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbcreateUrgency;
    private javax.swing.JComboBox<String> CmbviewUrgency;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportToCSV;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblICURequestHistory;
    private javax.swing.JTextField txtIcreateICUReason;
    private javax.swing.JTextField txtcreateAdmissionDate;
    private javax.swing.JTextField txtcreateDiagnosis;
    private javax.swing.JTextField txtcreatePatientName;
    private javax.swing.JTextField txtcreateTreatmentPlan;
    private javax.swing.JTextField txtviewAdmissionDate;
    private javax.swing.JTextField txtviewDiagnosis;
    private javax.swing.JTextField txtviewICUReason;
    private javax.swing.JTextField txtviewPatientName;
    private javax.swing.JTextField txtviewTreatmentPlan;
    // End of variables declaration//GEN-END:variables
}
