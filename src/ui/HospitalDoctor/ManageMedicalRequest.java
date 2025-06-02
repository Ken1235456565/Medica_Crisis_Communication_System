/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.HospitalDoctor;

import Model.Organization.Organization;
import Model.Patient.PatientDirectory;
import Model.Patient.Patient;
import Model.Patient.MedicationAdministration;
import Model.Supplies.SupplyItemCatalog;
import Model.Supplies.SupplyItem;
import Model.User.UserAccount;
import Model.WorkQueue.SupplyWorkRequest;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.awt.Container;
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
public class ManageMedicalRequest extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private PatientDirectory patientDirectory; // List of patients to manage
    private SupplyItemCatalog supplyCatalog; // To request medical supplies
    private List<MedicationAdministration> medicationRequests; // List of medication requests
    private MedicationAdministration selectedRequest; // Currently selected request
    private DefaultTableModel tableModel;

    public ManageMedicalRequest(JPanel userProcessContainer, Organization organization, UserAccount userAccount, PatientDirectory patientDirectory, SupplyItemCatalog supplyCatalog) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.patientDirectory = patientDirectory;
        this.supplyCatalog = supplyCatalog;
        this.medicationRequests = new ArrayList<>();
        this.selectedRequest = null;
        initComponents();
        initializeTable();
        setupEventHandlers();
    }

    private void initializeTable() {
        String[] columnNames = {"Request ID", "Patient Name", "Medication", "Dosage", "Prescription Date", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblMedicalRequest.setModel(tableModel);

        // 添加演示数据
        addDemoData();

        // 刷新视图
        refreshTable();
    }
    
    private void addDemoData() {
        Patient p1 = patientDirectory.createPatient("John Doe", "Male", 35, "1989-02-15");
        Patient p2 = patientDirectory.createPatient("Alice Smith", "Female", 28, "1996-03-20");

        MedicationAdministration m1 = new MedicationAdministration(
            p1, "2025-06-01", "High", "", "Take with food", "Created by Admin",
            "2025-06-02", "Amoxicillin", 500, "", "Pending");

        MedicationAdministration m2 = new MedicationAdministration(
            p2, "2025-05-30", "Low", "", "Before bedtime", "Created by Admin",
            "2025-06-01", "Ibuprofen", 200, "", "Approved");

        medicationRequests.add(m1);
        medicationRequests.add(m2);

        // 添加到患者历史
        p1.addMedicationAdministration(m1);
        p2.addMedicationAdministration(m2);
    }


    private void setupEventHandlers() {
        // Add table selection listener
        tblMedicalRequest.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblMedicalRequest.getSelectedRow();
                if (selectedRow >= 0) {
                    selectedRequest = medicationRequests.get(selectedRow);
                    populateViewFields();
                }
            }
        });
    }

private void refreshTable() {
    tableModel.setRowCount(0); // 清空表格

    for (MedicationAdministration request : medicationRequests) {
        Object[] row = new Object[]{
            request.getRequestId(),
            request.getPatient() != null ? request.getPatient().getName() : "Unknown",
            request.getMedication(),
            request.getDosage(),
            request.getPrescriptionDate(),
            
        };
        tableModel.addRow(row);
    }
}


    private void populateViewFields() {
        if (selectedRequest != null) {
            txtviewPatientName.setText(selectedRequest.getPatient() != null ? selectedRequest.getPatient().getName() : "");
            txtviewPrescriptionDate.setText(selectedRequest.getPrescriptionDate() != null ? selectedRequest.getPrescriptionDate() : "");
            CmbviewUrgency.setSelectedItem(selectedRequest.getUrgency() != null ? selectedRequest.getUrgency() : "Medium");
            txtviewMedication.setText(selectedRequest.getMedication() != null ? selectedRequest.getMedication() : "");
            txtviewDosage.setText(String.valueOf(selectedRequest.getDosage()));
            txtviewSpecialInstructions.setText(selectedRequest.getSpecialInstructions() != null ? selectedRequest.getSpecialInstructions() : "");
        }
    }

    private void clearCreateFields() {
        txtcreatePatientName.setText("");
        txtcreatePrescriptionDate.setText("");
        CmbcreateUrgency.setSelectedIndex(0);
        txtcreateMedication.setText("");
        txtcreateDosage.setText("");
        txtcreateSpecialInstructions.setText("");
    }

    private void clearViewFields() {
        txtviewPatientName.setText("");
        txtviewPrescriptionDate.setText("");
        CmbviewUrgency.setSelectedIndex(0);
        txtviewMedication.setText("");
        txtviewDosage.setText("");
        txtviewSpecialInstructions.setText("");
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicalRequest = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
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
        txtviewPrescriptionDate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtviewDosage = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcreatePatientName = new javax.swing.JTextField();
        txtviewPatientName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtcreateDosage = new javax.swing.JTextField();
        txtcreatePrescriptionDate = new javax.swing.JTextField();
        txtcreateSpecialInstructions = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtviewMedication = new javax.swing.JTextField();
        txtcreateMedication = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        CmbcreateUrgency = new javax.swing.JComboBox<>();
        txtviewSpecialInstructions = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnViewPersonHistory = new javax.swing.JButton();
        btnExportToCSV = new javax.swing.JButton();
        btnViewPrescriptionDetails = new javax.swing.JButton();
        btnViewNurseNotes = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Manage Medical Request");

        tblMedicalRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Request ID", "Patient Name", "Medication", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMedicalRequest);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel9.setText("Medical Request History:");

        btnModify.setText("Modify");
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel16.setText("View Details:");

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel15.setText("Medication:");

        CmbviewUrgency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Medical supplies", "food", "daily necessities", "money" }));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText("Patient Name:");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setText("Prescription Date:");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setText("Special Instructions:");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("Urgency:");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Dosage:");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setText("Medication:");

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel12.setText("Special Instructions:");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel11.setText("Prescription Date:");

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel10.setText("Patient Name:");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Create Report:");

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel14.setText("Dosage:");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel13.setText("Urgency:");

        CmbcreateUrgency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Medical supplies", "food", "daily necessities", "money" }));
        CmbcreateUrgency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbcreateUrgencyActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnViewPersonHistory.setText("View Person History");
        btnViewPersonHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPersonHistoryActionPerformed(evt);
            }
        });

        btnExportToCSV.setText("Export to csv");
        btnExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVActionPerformed(evt);
            }
        });

        btnViewPrescriptionDetails.setText("View Prescription Details");
        btnViewPrescriptionDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPrescriptionDetailsActionPerformed(evt);
            }
        });

        btnViewNurseNotes.setText("View Nurse Notes");
        btnViewNurseNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewNurseNotesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                    .addGap(266, 266, 266)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnViewPrescriptionDetails)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtcreatePatientName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtcreatePrescriptionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtcreateDosage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtcreateSpecialInstructions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtcreateMedication, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CmbcreateUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnExportToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnViewPersonHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtviewDosage)
                                    .addComponent(txtviewSpecialInstructions, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(txtviewMedication, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtviewPrescriptionDate)
                                    .addComponent(CmbviewUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtviewPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnViewNurseNotes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewPersonHistory)
                    .addComponent(btnDelete)
                    .addComponent(btnExportToCSV)
                    .addComponent(btnViewPrescriptionDetails))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtviewPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtviewPrescriptionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(CmbviewUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtviewMedication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtviewDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtviewSpecialInstructions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcreatePatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcreatePrescriptionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CmbcreateUrgency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcreateMedication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcreateDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcreateSpecialInstructions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreate)
                    .addComponent(btnModify)
                    .addComponent(btnViewNurseNotes))
                .addGap(25, 25, 25)
                .addComponent(btnBack)
                .addContainerGap(133, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
    CardLayout layout = (CardLayout) userProcessContainer.getLayout();
    layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedRequest == null) {
            JOptionPane.showMessageDialog(this, "Please select a medical request to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this medical request?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            medicationRequests.remove(selectedRequest);
            selectedRequest = null;
            refreshTable();
            clearViewFields();
            JOptionPane.showMessageDialog(this, "Medical request deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        if (selectedRequest == null) {
            JOptionPane.showMessageDialog(this, "Please select a medical request to modify.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String patientName = txtviewPatientName.getText().trim();
            String prescriptionDate = txtviewPrescriptionDate.getText().trim();
            String urgency = (String) CmbviewUrgency.getSelectedItem();
            String medication = txtviewMedication.getText().trim();
            String dosageStr = txtviewDosage.getText().trim();
            String specialInstructions = txtviewSpecialInstructions.getText().trim();

            // Validate fields
            if (patientName.isEmpty() || medication.isEmpty() || dosageStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int dosage;
            try {
                dosage = Integer.parseInt(dosageStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid dosage number.", "Invalid Dosage", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the request (Note: MedicationAdministration might need setter methods)
            // For now, we'll create a new one to replace it
            Patient patient = selectedRequest.getPatient();
            MedicationAdministration updatedRequest = new MedicationAdministration(
                patient, selectedRequest.getAdmissionDate(), urgency,
                selectedRequest.getTreatmentPlan(), specialInstructions, selectedRequest.getDoctorNotes(),
                prescriptionDate, medication, dosage, selectedRequest.getSideEffect(), selectedRequest.getNurseNotes()
            );

            int index = medicationRequests.indexOf(selectedRequest);
            medicationRequests.set(index, updatedRequest);
            selectedRequest = updatedRequest;

            refreshTable();
            JOptionPane.showMessageDialog(this, "Medical request modified successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error modifying medical request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnViewNurseNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewNurseNotesActionPerformed
        if (selectedRequest == null) {
            JOptionPane.showMessageDialog(this, "Please select a medical request to view nurse notes.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nurseNotes = selectedRequest.getNurseNotes();
        if (nurseNotes == null || nurseNotes.trim().isEmpty()) {
            nurseNotes = "No nurse notes available.";
        }

        JOptionPane.showMessageDialog(this, nurseNotes, "Nurse Notes", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnViewNurseNotesActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // Validate input fields
        if (!validateCreateFields()) {
            return;
        }

        try {
            String patientName = txtcreatePatientName.getText().trim();
            String prescriptionDate = txtcreatePrescriptionDate.getText().trim();
            String urgency = (String) CmbcreateUrgency.getSelectedItem();
            String medication = txtcreateMedication.getText().trim();
            String dosageStr = txtcreateDosage.getText().trim();
            String specialInstructions = txtcreateSpecialInstructions.getText().trim();

            // Parse dosage
            int dosage;
            try {
                dosage = Integer.parseInt(dosageStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid dosage number.", "Invalid Dosage", JOptionPane.ERROR_MESSAGE);
                return;
            }

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

            // Create medication administration record
            MedicationAdministration newRequest = new MedicationAdministration(
                patient, formatDate(new Date()), urgency,
                "", specialInstructions, "Created by " + userAccount.getName(),
                prescriptionDate, medication, dosage, "", ""
            );

            // Add to list
            medicationRequests.add(newRequest);
            
            // Add to patient's medication history if available
            if (patient.getMedicationHistory() != null) {
                patient.addMedicationAdministration(newRequest);
            }

            // Refresh table
            refreshTable();
            
            // Clear form
            clearCreateFields();
            
            JOptionPane.showMessageDialog(this, "Medical request created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating medical request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateActionPerformed
    private boolean validateCreateFields() {
        if (txtcreatePatientName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter patient name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtcreatePrescriptionDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter prescription date.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtcreateMedication.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter medication.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtcreateDosage.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter dosage.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    private void btnViewPersonHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPersonHistoryActionPerformed
        if (selectedRequest == null) {
            JOptionPane.showMessageDialog(this, "Please select a medical request first.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Patient patient = selectedRequest.getPatient();
        if (patient == null) {
            JOptionPane.showMessageDialog(this, "No patient associated with this request.", "No Patient", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Open PersonalHistorylinked window
        PersonalHistorylinked historyWindow = new PersonalHistorylinked(userProcessContainer, organization, userAccount, patient);
        historyWindow.setVisible(true);
    }//GEN-LAST:event_btnViewPersonHistoryActionPerformed

    private void btnExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVActionPerformed
        if (medicationRequests.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No medical requests to export.", "No Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String fileName = "medical_requests_" + System.currentTimeMillis() + ".csv";
            FileWriter writer = new FileWriter(fileName);

            // Write CSV header
            writer.append("Request ID,Patient Name,Medication,Dosage,Prescription Date,Urgency,Special Instructions,Doctor Notes,Nurse Notes\n");

            // Write data
            for (int i = 0; i < medicationRequests.size(); i++) {
                MedicationAdministration request = medicationRequests.get(i);
                writer.append(escapeCSV("MED" + (i + 1))).append(",");
                writer.append(escapeCSV(request.getPatient() != null ? request.getPatient().getName() : "")).append(",");
                writer.append(escapeCSV(request.getMedication())).append(",");
                writer.append(String.valueOf(request.getDosage())).append(",");
                writer.append(escapeCSV(request.getPrescriptionDate())).append(",");
                writer.append(escapeCSV(request.getUrgency())).append(",");
                writer.append(escapeCSV(request.getSpecialInstructions())).append(",");
                writer.append(escapeCSV(request.getDoctorNotes())).append(",");
                writer.append(escapeCSV(request.getNurseNotes())).append("\n");
            }

            writer.close();
            JOptionPane.showMessageDialog(this, "Medical requests exported to: " + fileName, "Export Successful", JOptionPane.INFORMATION_MESSAGE);

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
    private void btnViewPrescriptionDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPrescriptionDetailsActionPerformed
        if (selectedRequest == null) {
            JOptionPane.showMessageDialog(this, "Please select a medical request to view prescription details.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder details = new StringBuilder();
        details.append("Prescription Details:\n\n");
        details.append("Patient: ").append(selectedRequest.getPatient() != null ? selectedRequest.getPatient().getName() : "Unknown").append("\n");
        details.append("Medication: ").append(selectedRequest.getMedication()).append("\n");
        details.append("Dosage: ").append(selectedRequest.getDosage()).append("\n");
        details.append("Prescription Date: ").append(selectedRequest.getPrescriptionDate()).append("\n");
        details.append("Urgency: ").append(selectedRequest.getUrgency()).append("\n");
        details.append("Special Instructions: ").append(selectedRequest.getSpecialInstructions()).append("\n");
        details.append("Doctor Notes: ").append(selectedRequest.getDoctorNotes()).append("\n");
        details.append("Side Effects: ").append(selectedRequest.getSideEffect()).append("\n");
        details.append("Nurse Notes: ").append(selectedRequest.getNurseNotes()).append("\n");

        JOptionPane.showMessageDialog(this, details.toString(), "Prescription Details", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnViewPrescriptionDetailsActionPerformed

    private void CmbcreateUrgencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbcreateUrgencyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbcreateUrgencyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbcreateUrgency;
    private javax.swing.JComboBox<String> CmbviewUrgency;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportToCSV;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnViewNurseNotes;
    private javax.swing.JButton btnViewPersonHistory;
    private javax.swing.JButton btnViewPrescriptionDetails;
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
    private javax.swing.JTable tblMedicalRequest;
    private javax.swing.JTextField txtcreateDosage;
    private javax.swing.JTextField txtcreateMedication;
    private javax.swing.JTextField txtcreatePatientName;
    private javax.swing.JTextField txtcreatePrescriptionDate;
    private javax.swing.JTextField txtcreateSpecialInstructions;
    private javax.swing.JTextField txtviewDosage;
    private javax.swing.JTextField txtviewMedication;
    private javax.swing.JTextField txtviewPatientName;
    private javax.swing.JTextField txtviewPrescriptionDate;
    private javax.swing.JTextField txtviewSpecialInstructions;
    // End of variables declaration//GEN-END:variables
}
