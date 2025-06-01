/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.HospitalDoctor;

import Model.Employee.Employee;
import Model.Organization.Organization;
import Model.Employee.EmployeeDirectory;
import Model.Patient.ShiftNote;
import Model.Personnel.Nurse;
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
import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author tiankaining
 */
public class ViewOnDutyHistory extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private EmployeeDirectory employeeDirectory; // To access on-duty history of employees
    private List<ShiftNote> allShiftNotes; // Combined list of all shift notes
    private ShiftNote selectedShiftNote; // Currently selected shift note
    private DefaultTableModel tableModel;
    private Employee employee;

    public ViewOnDutyHistory(JPanel userProcessContainer, Organization organization, UserAccount userAccount, EmployeeDirectory employeeDirectory) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.employeeDirectory = employeeDirectory;
        this.allShiftNotes = new ArrayList<>();
        this.selectedShiftNote = null;
        
        initComponents();
        initializeData();
        initializeTable();
        setupEventHandlers();
    }

    private void initializeData() {
        // Collect all shift notes from all employees
        allShiftNotes.clear();
        List<Employee> employees = employeeDirectory.getEmployeeList();
        for (Employee employee : employees) {
            if (employee instanceof Nurse) {
                Nurse nurse = (Nurse) employee;
                if (nurse.getShiftNotes() != null) {
                    allShiftNotes.addAll(nurse.getShiftNotes());
                }
            }
        }
    }

    private void initializeTable() {
        String[] columnNames = {"Nurse ID", "Nurse Name", "Date", "Notes", "Contact Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        tblPatientShiftNotes.setModel(tableModel);
        refreshTable();
    }

    private void setupEventHandlers() {
        // Add table selection listener
        tblPatientShiftNotes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblPatientShiftNotes.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < allShiftNotes.size()) {
                    selectedShiftNote = allShiftNotes.get(selectedRow);
                    populateViewFields();
                }
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (ShiftNote note : allShiftNotes) {
            Object[] row = {
                note.getNurseId(),
                note.getNurseName(),
                note.getDate(),
                truncateText(note.getNotes(), 50), // Truncate for table display
                note.getContactEmail()
            };
            tableModel.addRow(row);
        }
    }

    private String truncateText(String text, int maxLength) {
        if (text == null) return "";
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength) + "...";
    }

    private void populateViewFields() {
        if (selectedShiftNote != null) {
            txtViewNurseName.setText(selectedShiftNote.getNurseName());
            txtViewContactEmail.setText(selectedShiftNote.getContactEmail());
            CmbViewDate.setSelectedItem(selectedShiftNote.getDate());
            txtViewNotes.setText(selectedShiftNote.getNotes());
        }
    }

    private void clearCreateFields() {
        txtCreateNurseName.setText("");
        txtCreateContactEmail.setText("");
        CmbCreateDate.setSelectedIndex(0);
        txtCreateNotes.setText("");
    }

    private void clearViewFields() {
        txtViewNurseName.setText("");
        txtViewContactEmail.setText("");
        CmbViewDate.setSelectedIndex(0);
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

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
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

        txtCreateNurseName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPatientShiftNotes = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtViewNurseName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnViewDetails = new javax.swing.JButton();
        txtViewContactEmail = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CmbViewDate = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtViewNotes = new javax.swing.JTextField();
        txtCreateContactEmail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCreateNotes = new javax.swing.JTextField();
        CmbCreateDate = new javax.swing.JComboBox<>();
        btnExportToCSV = new javax.swing.JButton();
        btnSalaryCaculation = new javax.swing.JButton();

        tblPatientShiftNotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nurse ID", "Nurse Name", "Date", "Notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPatientShiftNotes);

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel10.setText("View New Shift Note:");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText("Nurse Name:");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setText("Contact Email :");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel9.setText("View Patient List:");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("Date:");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setText("Notes:");

        btnViewDetails.setText("View Details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel12.setText("Contact Email :");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel11.setText("Nurse Name:");

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel16.setText("Notes:");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Create New Shift Note:");

        CmbViewDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Medical supplies", "food", "daily necessities", "money" }));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("View Patient Shift Notes");

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel14.setText("Date:");

        CmbCreateDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Medical supplies", "food", "daily necessities", "money" }));

        btnExportToCSV.setText("Export to csv");
        btnExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVActionPerformed(evt);
            }
        });

        btnSalaryCaculation.setText("Salary Caculation");
        btnSalaryCaculation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalaryCaculationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(txtCreateNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(81, 81, 81)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(CmbCreateDate, 0, 172, Short.MAX_VALUE)
                                            .addComponent(txtCreateContactEmail)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(76, 76, 76)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnExportToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCreateNurseName))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(txtViewNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(81, 81, 81)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(CmbViewDate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtViewContactEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(txtViewNurseName, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalaryCaculation, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(410, 410, 410))
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(286, 286, 286)
                                .addComponent(jLabel1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 1, Short.MAX_VALUE)))
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
                            .addComponent(txtCreateNurseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCreateContactEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(CmbCreateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtCreateNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtViewNurseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewContactEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(CmbViewDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtViewNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnSalaryCaculation))
                .addGap(25, 25, 25)
                .addComponent(btnBack)
                .addContainerGap(131, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (!validateCreateFields()) {
            return;
        }

        try {
            String nurseName = txtCreateNurseName.getText().trim();
            String contactEmail = txtCreateContactEmail.getText().trim();
            String date = (String) CmbCreateDate.getSelectedItem();
            String notes = txtCreateNotes.getText().trim();

            // Use today's date if date field is empty or invalid
            if (date == null || date.trim().isEmpty() || date.equals(" ")) {
                date = formatDate(new Date());
            }

            // Validate date format
            if (!isValidDate(date)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid date (yyyy-MM-dd).", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Find the employee by name, or create if not found
            Employee nurse = employeeDirectory.findEmployeeByName(nurseName);
            if (nurse == null) {
                int create = JOptionPane.showConfirmDialog(this,
                        "Nurse not found. Do you want to create a new employee record?",
                        "Nurse Not Found",
                        JOptionPane.YES_NO_OPTION);
                if (create == JOptionPane.YES_OPTION) {
                    nurse = employeeDirectory.createEmployee("NUR" + (employeeDirectory.getEmployeeList().size() + 1), nurseName, contactEmail);
                } else {
                    return;
                }
            }

            // Create shift note
            ShiftNote newNote = new ShiftNote(nurse.getId(), nurseName, contactEmail, date, notes);
            if (employee instanceof Nurse) {
                ((Nurse) employee).addShiftNote(selectedShiftNote);
            }

            allShiftNotes.add(newNote);

            // Refresh table
            refreshTable();
            
            // Clear form
            clearCreateFields();
            
            JOptionPane.showMessageDialog(this, "Shift note created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating shift note: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateActionPerformed
    private boolean validateCreateFields() {
        if (txtCreateNurseName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter nurse name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtCreateContactEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter contact email.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate email format
        String email = txtCreateContactEmail.getText().trim();
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtCreateNotes.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter shift notes.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       if (selectedShiftNote == null) {
            JOptionPane.showMessageDialog(this, "Please select a shift note to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this shift note?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean removed = false;
            
            // Find the employee and remove the note
            Employee nurse = employeeDirectory.findEmployeeById(selectedShiftNote.getNurseId());
            if (nurse != null) {
                if (employee instanceof Nurse) {
                ((Nurse) employee).removeShiftNote(selectedShiftNote);
            }
            }
            
            if (removed) {
                allShiftNotes.remove(selectedShiftNote);
                selectedShiftNote = null;
                refreshTable();
                clearViewFields();
                JOptionPane.showMessageDialog(this, "Shift note deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete shift note.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSalaryCaculationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalaryCaculationActionPerformed
        showSalaryCalculationDialog();
    }//GEN-LAST:event_btnSalaryCaculationActionPerformed
private void showSalaryCalculationDialog() {
        String nurseId = JOptionPane.showInputDialog(this, "Enter Nurse ID:", "Salary Calculation", JOptionPane.QUESTION_MESSAGE);
        if (nurseId == null || nurseId.trim().isEmpty()) {
            return;
        }

        String hourlyRateStr = JOptionPane.showInputDialog(this, "Enter Hourly Rate ($):", "Salary Calculation", JOptionPane.QUESTION_MESSAGE);
        if (hourlyRateStr == null || hourlyRateStr.trim().isEmpty()) {
            return;
        }

        try {
            double hourlyRate = Double.parseDouble(hourlyRateStr);
            double salary = calculateNurseSalary(nurseId.trim(), hourlyRate);
            
            if (salary >= 0) {
                Employee nurse = employeeDirectory.findEmployeeById(nurseId.trim());
                String nurseName = nurse != null ? nurse.getName() : "Unknown";
                
                StringBuilder result = new StringBuilder();
                result.append("Salary Calculation for: ").append(nurseName).append("\n");
                result.append("Nurse ID: ").append(nurseId).append("\n");
                result.append("Hourly Rate: $").append(String.format("%.2f", hourlyRate)).append("\n");
                result.append("Total Shifts: ").append(getShiftCount(nurseId)).append("\n");
                result.append("Total Hours: ").append(getShiftCount(nurseId) * 8).append(" (8 hours per shift)\n");
                result.append("Calculated Salary: $").append(String.format("%.2f", salary));
                
                JOptionPane.showMessageDialog(this, result.toString(), "Salary Calculation Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nurse not found with ID: " + nurseId, "Nurse Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid hourly rate.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double calculateNurseSalary(String nurseId, double hourlyRate) {
        Employee nurse = employeeDirectory.findEmployeeById(nurseId);
        if (nurse == null) {
            return -1.0;
        }

        Nurse nurseObj = (Nurse) nurse;
        int totalShifts = nurseObj.getShiftNotes() == null ? 0 : nurseObj.getShiftNotes().size();
        double hoursWorked = totalShifts * 8; // Assuming 8 hours per shift
        double salary = hoursWorked * hourlyRate;
        
        return salary;
    }

    private int getShiftCount(String nurseId) {
        Employee nurse = employeeDirectory.findEmployeeById(nurseId);
        if (nurse instanceof Nurse nurseObj && nurseObj.getShiftNotes() != null) {
            return nurseObj.getShiftNotes().size();
        }
        return 0;
    }
    
    public List<ShiftNote> getAllShiftNotes() {
        return allShiftNotes;
    }
    private void btnExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVActionPerformed
        if (allShiftNotes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No shift notes to export.", "No Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String fileName = "shift_notes_" + System.currentTimeMillis() + ".csv";
            FileWriter writer = new FileWriter(fileName);

            // Write CSV header
            writer.append("Nurse ID,Nurse Name,Date,Notes,Contact Email\n");

            for (ShiftNote note : allShiftNotes) {
                writer.append(escapeCSV(note.getNurseId())).append(",");
                writer.append(escapeCSV(note.getNurseName())).append(",");
                writer.append(escapeCSV(note.getDate())).append(",");
                writer.append(escapeCSV(note.getNotes())).append(",");
                writer.append(escapeCSV(note.getContactEmail())).append("\n");
            }
            
            writer.close();
            JOptionPane.showMessageDialog(this, "Shift notes exported to: " + fileName, "Export Successful", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting shift notes: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
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
    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        if (selectedShiftNote == null) {
            JOptionPane.showMessageDialog(this, "Please select a shift note to view details.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder details = new StringBuilder();
        details.append("Shift Note Details:\n\n");
        details.append("Nurse ID: ").append(selectedShiftNote.getNurseId()).append("\n");
        details.append("Nurse Name: ").append(selectedShiftNote.getNurseName()).append("\n");
        details.append("Date: ").append(selectedShiftNote.getDate()).append("\n");
        details.append("Contact Email: ").append(selectedShiftNote.getContactEmail()).append("\n");
        details.append("Notes:\n").append(selectedShiftNote.getNotes()).append("\n");

        JOptionPane.showMessageDialog(this, details.toString(), "Shift Note Details", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnViewDetailsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbCreateDate;
    private javax.swing.JComboBox<String> CmbViewDate;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportToCSV;
    private javax.swing.JButton btnSalaryCaculation;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPatientShiftNotes;
    private javax.swing.JTextField txtCreateContactEmail;
    private javax.swing.JTextField txtCreateNotes;
    private javax.swing.JTextField txtCreateNurseName;
    private javax.swing.JTextField txtViewContactEmail;
    private javax.swing.JTextField txtViewNotes;
    private javax.swing.JTextField txtViewNurseName;
    // End of variables declaration//GEN-END:variables
}
