/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.HospitalDoctor;

import Model.Organization.Organization;
import Model.Patient.MedicationAdministration;
import Model.Patient.Patient;
import Model.User.UserAccount;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author tiankaining
 */
public class PersonalHistorylinked extends javax.swing.JFrame {

    private JPanel userProcessContainer; // If it's used within a JPanel hierarchy
    private Organization organization;
    private UserAccount userAccount;
    private Patient selectedPatient; // The patient whose history is being viewed
    private List<MedicationAdministration> medicationHistory;
    private MedicationAdministration selectedRecord;
    private DefaultTableModel tableModel;

    public PersonalHistorylinked(JPanel userProcessContainer, Organization organization, UserAccount userAccount, Patient selectedPatient) {
        this.userProcessContainer = userProcessContainer; // Not typically used for JFrames, but included based on previous context.
        this.organization = organization;
        this.userAccount = userAccount;
        this.selectedPatient = selectedPatient;
        this.medicationHistory = new ArrayList<>();
        this.selectedRecord = null;
        
        initComponents();
        initializeData();
        initializeTable();
        setupEventHandlers();
    }

    private void initializeData() {
        if (selectedPatient != null && selectedPatient.getMedicationHistory() != null) {
            medicationHistory = selectedPatient.getMedicationHistory();
        }
    }

    private void initializeTable() {
        String[] columnNames = {"Patient ID", "Patient Name", "Medication Name", "Dosage", "Prescription Date", "Side Effects"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        tblMedicationAdministration.setModel(tableModel);
        refreshTable();
    }

    private void setupEventHandlers() {
        // Add table selection listener
        tblMedicationAdministration.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblMedicationAdministration.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < medicationHistory.size()) {
                    selectedRecord = medicationHistory.get(selectedRow);
                    populateViewFields();
                }
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (MedicationAdministration record : medicationHistory) {
            Object[] row = {
                selectedPatient != null ? selectedPatient.getPatientId() : "Unknown",
                selectedPatient != null ? selectedPatient.getName() : "Unknown",
                record.getMedication() != null ? record.getMedication() : "",
                record.getDosage(),
                record.getPrescriptionDate() != null ? record.getPrescriptionDate() : "",
                record.getSideEffect() != null ? record.getSideEffect() : ""
            };
            tableModel.addRow(row);
        }
    }

    private void populateViewFields() {
        if (selectedRecord != null) {
            txtvrewPatientName.setText(selectedPatient != null ? selectedPatient.getName() : "");
            txtviewPrescriptionDate.setText(selectedRecord.getPrescriptionDate() != null ? selectedRecord.getPrescriptionDate() : "");
            txtViewMedication.setText(selectedRecord.getMedication() != null ? selectedRecord.getMedication() : "");
            txtviewDosage.setText(String.valueOf(selectedRecord.getDosage()));
            txtViewSideEffect.setText(selectedRecord.getSideEffect() != null ? selectedRecord.getSideEffect() : "");
            txtViewNotes.setText(selectedRecord.getNurseNotes() != null ? selectedRecord.getNurseNotes() : "");
        }
    }

    private void clearCreateFields() {
        txtcreatePatientName.setText("");
        txtcreateAdmissionDate.setText("");
        txtcreateUrgency.setText("");
        txtcreateTreatmentPlan.setText("");
        txtcreateSpecialInstructions.setText("");
        txtCreateNotes.setText("");
    }

    private void clearViewFields() {
        txtvrewPatientName.setText("");
        txtviewPrescriptionDate.setText("");
        txtViewMedication.setText("");
        txtviewDosage.setText("");
        txtViewSideEffect.setText("");
        txtViewNotes.setText("");
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


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnExportToCSV = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtViewMedication = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtcreateUrgency = new javax.swing.JTextField();
        txtViewSideEffect = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtcreatePatientName = new javax.swing.JTextField();
        txtvrewPatientName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtviewPrescriptionDate = new javax.swing.JTextField();
        btnViewDetails = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCreateNotes = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtviewDosage = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtViewNotes = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicationAdministration = new javax.swing.JTable();
        txtcreateTreatmentPlan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcreateSpecialInstructions = new javax.swing.JTextField();
        txtcreateAdmissionDate = new javax.swing.JTextField();
        btnView = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setText("Treatment Plan:");

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel12.setText("Notes :");

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Manage Medication Administration");

        btnExportToCSV.setText("Export to csv");
        btnExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel11.setText("Prescription Date:");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setText("Admission Date:");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel13.setText("Medication:");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setText("Notes :");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel10.setText("Patient Name:");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Doctor Notes:");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText("Patient Name:");

        btnViewDetails.setText("View Details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel9.setText("Medication Administration Log:");

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel14.setText("Side Effect:");

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel16.setText("Nurse notes:");

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel15.setText("Dosage:");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("Urgency:");

        tblMedicationAdministration.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Patient ID", "Patient Name", "Medication Name", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMedicationAdministration);

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Special Instructions:");

        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(52, 52, 52)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtcreateSpecialInstructions, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCreateNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)
                                        .addComponent(txtcreateTreatmentPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtcreatePatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(81, 81, 81)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtcreateAdmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtcreateUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addComponent(jLabel5)
                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(76, 76, 76)
                                    .addComponent(btnExportToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtViewSideEffect)
                                        .addComponent(txtViewNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(txtviewDosage, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(81, 81, 81)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtviewPrescriptionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtViewMedication, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtvrewPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(197, 197, 197)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnExportToCSV)
                    .addComponent(btnViewDetails))
                .addGap(10, 10, 10)
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
                            .addComponent(txtcreateUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtcreateTreatmentPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtcreateSpecialInstructions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCreateNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtvrewPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcreatePatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtviewPrescriptionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtViewMedication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtviewDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtViewSideEffect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtViewNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreate)
                    .addComponent(btnView))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedRecord == null) {
            JOptionPane.showMessageDialog(this, "Please select a medication record to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this medication record?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean removed = false;
            if (selectedPatient != null) {
                removed = selectedPatient.removeMedicationAdministration(selectedRecord);
                medicationHistory.remove(selectedRecord);
            }
            
            if (removed) {
                selectedRecord = null;
                refreshTable();
                clearViewFields();
                JOptionPane.showMessageDialog(this, "Medication record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete medication record.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        if (selectedRecord == null) {
            JOptionPane.showMessageDialog(this, "Please select a medication record to view details.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder details = new StringBuilder();
        details.append("Medication Administration Details:\n\n");
        details.append("Patient: ").append(selectedPatient != null ? selectedPatient.getName() : "Unknown").append("\n");
        details.append("Admission Date: ").append(selectedRecord.getAdmissionDate()).append("\n");
        details.append("Urgency: ").append(selectedRecord.getUrgency()).append("\n");
        details.append("Treatment Plan: ").append(selectedRecord.getTreatmentPlan()).append("\n");
        details.append("Special Instructions: ").append(selectedRecord.getSpecialInstructions()).append("\n");
        details.append("Doctor Notes: ").append(selectedRecord.getDoctorNotes()).append("\n");
        details.append("Prescription Date: ").append(selectedRecord.getPrescriptionDate()).append("\n");
        details.append("Medication: ").append(selectedRecord.getMedication()).append("\n");
        details.append("Dosage: ").append(selectedRecord.getDosage()).append("\n");
        details.append("Side Effects: ").append(selectedRecord.getSideEffect()).append("\n");
        details.append("Nurse Notes: ").append(selectedRecord.getNurseNotes()).append("\n");

        JOptionPane.showMessageDialog(this, details.toString(), "Medication Details", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnViewDetailsActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        if (selectedRecord == null) {
            JOptionPane.showMessageDialog(this, "Please select a medication record to view.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Populate view fields with selected record
        populateViewFields();
        JOptionPane.showMessageDialog(this, "Record loaded in view section.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (!validateCreateFields()) {
            return;
        }

        try {
            String patientName = txtcreatePatientName.getText().trim();
            String admissionDate = txtcreateAdmissionDate.getText().trim();
            String urgency = txtcreateUrgency.getText().trim();
            String treatmentPlan = txtcreateTreatmentPlan.getText().trim();
            String specialInstructions = txtcreateSpecialInstructions.getText().trim();
            String doctorNotes = txtCreateNotes.getText().trim();

            // Validate date format
            if (!isValidDate(admissionDate)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid admission date (yyyy-MM-dd).", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Ensure we're working with the correct patient
            if (selectedPatient == null) {
                JOptionPane.showMessageDialog(this, "No patient selected for creating medication record.", "No Patient", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!selectedPatient.getName().equals(patientName)) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Patient name doesn't match selected patient. Continue anyway?",
                        "Name Mismatch",
                        JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            // Create new medication administration record
            MedicationAdministration newRecord = new MedicationAdministration(
                selectedPatient, admissionDate, urgency, treatmentPlan,
                specialInstructions, doctorNotes, "", "", 0, "", ""
            );

            // Add to patient's history
            selectedPatient.addMedicationAdministration(newRecord);
            medicationHistory.add(newRecord);

            // Refresh table
            refreshTable();
            
            // Clear form
            clearCreateFields();
            
            JOptionPane.showMessageDialog(this, "Medication record created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating medication record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        
        if (txtcreateUrgency.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter urgency level.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtcreateTreatmentPlan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter treatment plan.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    private void btnExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVActionPerformed
        if (medicationHistory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No medication history to export.", "No Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String fileName = "medication_history_" + (selectedPatient != null ? selectedPatient.getPatientId() : "unknown") + "_" + System.currentTimeMillis() + ".csv";
            FileWriter writer = new FileWriter(fileName);

            // Write CSV header
            writer.append("Patient ID,Patient Name,Admission Date,Urgency,Treatment Plan,Special Instructions,Doctor Notes," +
                          "Prescription Date,Medication,Dosage,Side Effect,Nurse Notes\n");

            // Write each record
            for (MedicationAdministration record : medicationHistory) {
                writer.append(escapeCSV(selectedPatient != null ? selectedPatient.getPatientId() : "")).append(",");
                writer.append(escapeCSV(selectedPatient != null ? selectedPatient.getName() : "")).append(",");
                writer.append(escapeCSV(record.getAdmissionDate())).append(",");
                writer.append(escapeCSV(record.getUrgency())).append(",");
                writer.append(escapeCSV(record.getTreatmentPlan())).append(",");
                writer.append(escapeCSV(record.getSpecialInstructions())).append(",");
                writer.append(escapeCSV(record.getDoctorNotes())).append(",");
                writer.append(escapeCSV(record.getPrescriptionDate())).append(",");
                writer.append(escapeCSV(record.getMedication())).append(",");
                writer.append(String.valueOf(record.getDosage())).append(",");
                writer.append(escapeCSV(record.getSideEffect())).append(",");
                writer.append(escapeCSV(record.getNurseNotes())).append("\n");
            }
            
            writer.close();
            JOptionPane.showMessageDialog(this, "Medication history exported to: " + fileName, "Export Successful", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting medication history: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExportToCSVActionPerformed

    private String escapeCSV(String value) {
            if (value == null) {
                return "";
            }
            String escapedValue = value.replace("\"", "\"\"");
            if (escapedValue.contains(",") || escapedValue.contains("\n")) {
                return "\"" + escapedValue + "\"";
            }
            return escapedValue;
        }


        public List<MedicationAdministration> getMedicationAdministrationRecords() {
            if (selectedPatient == null) {
                return List.of(); // Return an empty list if no patient is selected
            }
            return selectedPatient.getMedicationHistory();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportToCSV;
    private javax.swing.JButton btnView;
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
    private javax.swing.JTable tblMedicationAdministration;
    private javax.swing.JTextField txtCreateNotes;
    private javax.swing.JTextField txtViewMedication;
    private javax.swing.JTextField txtViewNotes;
    private javax.swing.JTextField txtViewSideEffect;
    private javax.swing.JTextField txtcreateAdmissionDate;
    private javax.swing.JTextField txtcreatePatientName;
    private javax.swing.JTextField txtcreateSpecialInstructions;
    private javax.swing.JTextField txtcreateTreatmentPlan;
    private javax.swing.JTextField txtcreateUrgency;
    private javax.swing.JTextField txtviewDosage;
    private javax.swing.JTextField txtviewPrescriptionDate;
    private javax.swing.JTextField txtvrewPatientName;
    // End of variables declaration//GEN-END:variables
}
