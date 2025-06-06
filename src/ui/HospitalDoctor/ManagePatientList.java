/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.HospitalDoctor;

import Model.Organization.Organization;
import Model.Patient.PatientDirectory;
import Model.Patient.Patient;
import Model.Patient.MedicalRecord;
import Model.Patient.MedicalEntry;
import Model.User.UserAccount;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author tiankaining
 */
public class ManagePatientList extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private PatientDirectory patientDirectory; // The list of patients to manage
    private Patient selectedPatient; // Currently selected patient
    private DefaultTableModel tableModel;

    public ManagePatientList(JPanel userProcessContainer, Organization organization, UserAccount userAccount, PatientDirectory patientDirectory) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.patientDirectory = patientDirectory;
        this.selectedPatient = null;
        initComponents();
        initializeTable();
        setupEventHandlers();
    }

    private void initializeTable() {
        String[] columnNames = {"Patient ID", "Patient Name", "Blood Group", "Current Medications", "Age", "Gender"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        tblPatientsList.setModel(tableModel);
        refreshTable();
    }

    private void setupEventHandlers() {
        // Add table selection listener
        tblPatientsList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblPatientsList.getSelectedRow();
                if (selectedRow >= 0) {
                    List<Patient> patients = patientDirectory.getPatientList();
                    if (selectedRow < patients.size()) {
                        selectedPatient = patients.get(selectedRow);
                        populateViewFields();
                    }
                }
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Patient> patients = patientDirectory.getPatientList();
        for (Patient patient : patients) {
            Object[] row = {
                patient.getPatientId(),
                patient.getName(),
                getPatientBloodGroup(patient),
                getCurrentMedications(patient),
                patient.getAge(),
                patient.getGender()
            };
            tableModel.addRow(row);
        }
    }

    private String getPatientBloodGroup(Patient patient) {
        // This would typically be stored in patient's extended profile
        // For now, return a placeholder
        return "O+";
    }

    private String getCurrentMedications(Patient patient) {
        // Get current medications from patient's medical record
        if (patient.getMedicalRecord() != null && patient.getMedicalRecord().getMedicalHistory() != null) {
            StringBuilder medications = new StringBuilder();
            List<MedicalEntry> history = patient.getMedicalRecord().getMedicalHistory();
            for (int i = Math.max(0, history.size() - 3); i < history.size(); i++) {
                if (medications.length() > 0) {
                    medications.append(", ");
                }
                medications.append(history.get(i).getTreatment());
            }
            return medications.toString();
        }
        return "None";
    }

    private void populateViewFields() {
        if (selectedPatient != null) {
            txtviewPatientName.setText(selectedPatient.getName());
            txtviewContactEmail.setText(getPatientEmail(selectedPatient));
            CmbviewBloodGroup.setSelectedItem(getPatientBloodGroup(selectedPatient));
            txtviewAllergies.setText(getPatientAllergies(selectedPatient));
            CmbviewMedicalHistory.setSelectedItem(getMedicalHistoryStatus(selectedPatient));
            txtviewCurrentMedications.setText(getCurrentMedications(selectedPatient));
        }
    }

    private String getPatientEmail(Patient patient) {
        if (patient.getContactInfo() != null) {
            return patient.getContactInfo().getContactEmail();
        }
        return "";
    }

    private String getPatientAllergies(Patient patient) {
        // This would typically be stored in patient's extended profile
        return "None known";
    }

    private String getMedicalHistoryStatus(Patient patient) {
        if (patient.getMedicalRecord() != null && 
            patient.getMedicalRecord().getMedicalHistory() != null && 
            !patient.getMedicalRecord().getMedicalHistory().isEmpty()) {
            return "have history";
        }
        return "new patient";
    }

    private void clearCreateFields() {
        txtcreatePatientName.setText("");
        txtcreateContactEmail.setText("");
        CmbcreateBloodGroup.setSelectedIndex(0);
        txtcreateAllergies.setText("");
        CmbcreateMedicalHistory.setSelectedIndex(0);
        txtcreateCurrentMedications.setText("");
    }

    private void clearViewFields() {
        txtviewPatientName.setText("");
        txtviewContactEmail.setText("");
        CmbviewBloodGroup.setSelectedIndex(0);
        txtviewAllergies.setText("");
        CmbviewMedicalHistory.setSelectedIndex(0);
        txtviewCurrentMedications.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtcreateContactEmail = new javax.swing.JTextField();
        txtcreateCurrentMedications = new javax.swing.JTextField();
        txtcreateAllergies = new javax.swing.JTextField();
        CmbcreateBloodGroup = new javax.swing.JComboBox<>();
        btnExportToCSV = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPatientsList = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtcreatePatientName = new javax.swing.JTextField();
        CmbcreateMedicalHistory = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        txtviewPatientName = new javax.swing.JTextField();
        CmbviewMedicalHistory = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtviewContactEmail = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtviewCurrentMedications = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtviewAllergies = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        CmbviewBloodGroup = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        btnModify = new javax.swing.JButton();
        btnViewDetails = new javax.swing.JButton();

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Create Patient:");

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Manage Patients List");

        CmbcreateBloodGroup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "A", "B", "AB", "O" }));

        btnExportToCSV.setText("Export to csv");
        btnExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVActionPerformed(evt);
            }
        });

        tblPatientsList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Patient ID", "Patient Name", "Blood Group", "Current Medications"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPatientsList);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText("Patient Name:");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setText("Contact Email :");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setText("Current Medications :");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel9.setText("View Patient List:");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("Blood Group:");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Medical History:");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setText("Allergies:");

        CmbcreateMedicalHistory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "new patient", "have history" }));

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        CmbviewMedicalHistory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "new patient", "have history" }));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel10.setText("View Patient Detail:");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel11.setText("Patient Name:");

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel12.setText("Contact Email :");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel13.setText("Current Medications :");

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel14.setText("Blood Group:");

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel15.setText("Medical History:");

        CmbviewBloodGroup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "A", "B", "AB", "O" }));

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel16.setText("Allergies:");

        btnModify.setText("Modify");
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        btnViewDetails.setText("View Details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtcreateAllergies, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtcreateCurrentMedications, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CmbcreateMedicalHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(81, 81, 81)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(CmbcreateBloodGroup, 0, 172, Short.MAX_VALUE)
                                            .addComponent(txtcreateContactEmail)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(76, 76, 76)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnExportToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtcreatePatientName, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtviewAllergies, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtviewCurrentMedications, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CmbviewMedicalHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnModify, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(81, 81, 81)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(CmbviewBloodGroup, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtviewContactEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(txtviewPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(286, 286, 286)
                                .addComponent(jLabel1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(105, 105, 105))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnExportToCSV)
                            .addComponent(btnViewDetails))
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtcreatePatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcreateContactEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(CmbcreateBloodGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtcreateAllergies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(CmbcreateMedicalHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtcreateCurrentMedications, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtviewPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtviewContactEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(CmbviewBloodGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtviewAllergies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(CmbviewMedicalHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtviewCurrentMedications, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnModify))
                .addGap(25, 25, 25)
                .addComponent(btnBack)
                .addContainerGap(130, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
    CardLayout layout = (CardLayout) userProcessContainer.getLayout();
    layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedPatient == null) {
            JOptionPane.showMessageDialog(this, "Please select a patient to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete patient: " + selectedPatient.getName() + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            patientDirectory.removePatient(selectedPatient);
            selectedPatient = null;
            refreshTable();
            clearViewFields();
            JOptionPane.showMessageDialog(this, "Patient deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // Validate input fields
        if (!validateCreateFields()) {
            return;
        }

        try {
            String patientName = txtcreatePatientName.getText().trim();
            String contactEmail = txtcreateContactEmail.getText().trim();
            String bloodGroup = (String) CmbcreateBloodGroup.getSelectedItem();
            String allergies = txtcreateAllergies.getText().trim();
            String medicalHistoryStatus = (String) CmbcreateMedicalHistory.getSelectedItem();
            String currentMedications = txtcreateCurrentMedications.getText().trim();

            // Check if patient already exists
            Patient existingPatient = patientDirectory.findPatientByName(patientName);
            if (existingPatient != null) {
                JOptionPane.showMessageDialog(this, "A patient with this name already exists.", "Duplicate Patient", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create new patient
            Patient newPatient = patientDirectory.createPatient(patientName, "Unknown", 0, "Unknown");
            
            // Set contact information
            if (newPatient.getContactInfo() != null) {
                newPatient.getContactInfo().setContactEmail(contactEmail);
            }

            // Add initial medical entry if medications are specified
            if (!currentMedications.isEmpty()) {
                newPatient.getMedicalRecord().addMedicalEntry("Initial Assessment", currentMedications);
            }

            // Refresh table
            refreshTable();
            
            // Clear form
            clearCreateFields();
            
            JOptionPane.showMessageDialog(this, "Patient created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating patient: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateActionPerformed
    private boolean validateCreateFields() {
        if (txtcreatePatientName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter patient name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtcreateContactEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter contact email.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate email format
        String email = txtcreateContactEmail.getText().trim();
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        if (selectedPatient == null) {
            JOptionPane.showMessageDialog(this, "Please select a patient to modify.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String patientName = txtviewPatientName.getText().trim();
            String contactEmail = txtviewContactEmail.getText().trim();
            String allergies = txtviewAllergies.getText().trim();
            String currentMedications = txtviewCurrentMedications.getText().trim();

            // Validate fields
            if (patientName.isEmpty() || contactEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update patient information
            selectedPatient.setName(patientName);
            if (selectedPatient.getContactInfo() != null) {
                selectedPatient.getContactInfo().setContactEmail(contactEmail);
            }

            // Add new medical entry if medications changed
            String oldMedications = getCurrentMedications(selectedPatient);
            if (!currentMedications.equals(oldMedications) && !currentMedications.isEmpty()) {
                selectedPatient.getMedicalRecord().addMedicalEntry("Updated Medications", currentMedications);
            }

            // Refresh table
            refreshTable();

            JOptionPane.showMessageDialog(this, "Patient information modified successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error modifying patient: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVActionPerformed
    List<Patient> patients = patientDirectory.getPatientList();
    if (patients.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No patients to export.", "No Data", JOptionPane.WARNING_MESSAGE);
        return;
    }

    FileWriter writer = null;

    try {
        String fileName = "patients_list_" + System.currentTimeMillis() + ".csv";
        writer = new FileWriter(fileName);

        // Write CSV header
        writer.append("Patient ID,Patient Name,Blood Group,Current Medications,Age,Gender,Contact Email,Allergies,Medical History Status\n");

        // Write data
        for (Patient patient : patients) {
            writer.append(escapeCSV(patient.getPatientId())).append(",");
            writer.append(escapeCSV(patient.getName())).append(",");
            writer.append(escapeCSV(getPatientBloodGroup(patient))).append(",");
            writer.append(escapeCSV(getCurrentMedications(patient))).append(",");
            writer.append(String.valueOf(patient.getAge())).append(",");
            writer.append(escapeCSV(patient.getGender())).append(",");
            writer.append(escapeCSV(getPatientEmail(patient))).append(",");
            writer.append(escapeCSV(getPatientAllergies(patient))).append(",");
            writer.append(escapeCSV(getMedicalHistoryStatus(patient))).append("\n");
        }

        writer.flush(); // 确保缓冲区内容写入
        JOptionPane.showMessageDialog(this, "Patient list exported to: " + fileName, "Export Successful", JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error exporting data: " + e.getMessage(), "Export Failed", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (writer != null) writer.close();
        } catch (IOException e) {
            // 忽略关闭时的异常
        }
    }
    }//GEN-LAST:event_btnExportToCSVActionPerformed
private String escapeCSV(String value) {
    if (value == null) {
        return "";
    }
    if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
    return value;
}

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        if (selectedPatient == null) {
            JOptionPane.showMessageDialog(this, "Please select a patient to view details.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder details = new StringBuilder();
        details.append("Patient Details:\n\n");
        details.append("Patient ID: ").append(selectedPatient.getPatientId()).append("\n");
        details.append("Name: ").append(selectedPatient.getName()).append("\n");
        details.append("Age: ").append(selectedPatient.getAge()).append("\n");
        details.append("Gender: ").append(selectedPatient.getGender()).append("\n");
        details.append("Date of Birth: ").append(selectedPatient.getDateOfBirth()).append("\n");
        details.append("Contact Email: ").append(getPatientEmail(selectedPatient)).append("\n");
        details.append("Blood Group: ").append(getPatientBloodGroup(selectedPatient)).append("\n");
        details.append("Allergies: ").append(getPatientAllergies(selectedPatient)).append("\n");
        details.append("Medical History Status: ").append(getMedicalHistoryStatus(selectedPatient)).append("\n");
        details.append("Current Medications: ").append(getCurrentMedications(selectedPatient)).append("\n");
        
        // Add medical history summary
        if (selectedPatient.getMedicalRecord() != null && selectedPatient.getMedicalRecord().getMedicalHistory() != null) {
            List<MedicalEntry> history = selectedPatient.getMedicalRecord().getMedicalHistory();
            details.append("\nMedical History (").append(history.size()).append(" entries):\n");
            for (int i = Math.max(0, history.size() - 5); i < history.size(); i++) {
                MedicalEntry entry = history.get(i);
                details.append("- ").append(entry.getDiagnosis()).append(" (").append(entry.getDateCreated()).append(")\n");
            }
        }

        JOptionPane.showMessageDialog(this, details.toString(), "Patient Details", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnViewDetailsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbcreateBloodGroup;
    private javax.swing.JComboBox<String> CmbcreateMedicalHistory;
    private javax.swing.JComboBox<String> CmbviewBloodGroup;
    private javax.swing.JComboBox<String> CmbviewMedicalHistory;
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
    private javax.swing.JTable tblPatientsList;
    private javax.swing.JTextField txtcreateAllergies;
    private javax.swing.JTextField txtcreateContactEmail;
    private javax.swing.JTextField txtcreateCurrentMedications;
    private javax.swing.JTextField txtcreatePatientName;
    private javax.swing.JTextField txtviewAllergies;
    private javax.swing.JTextField txtviewContactEmail;
    private javax.swing.JTextField txtviewCurrentMedications;
    private javax.swing.JTextField txtviewPatientName;
    // End of variables declaration//GEN-END:variables
}
