/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.PayrollOfficer;

import Model.Organization.Organization;
import Model.Organization.OperationsSupportUnit;
import Model.Employee.Employee;
import Model.Employee.EmployeeDirectory;
import Model.Employee.PayrollRecord;
import Model.Enterprise.Enterprise;
import Model.Organization.OrganizationDirectory;
import Model.User.UserAccount;
import Model.WorkQueue.PayrollRequest;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author tiankaining
 */
public class ManageEmployeeSalary extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private OrganizationDirectory organizationDirectory;
    private UserAccount userAccount;
    private EmployeeDirectory employeeDirectory;
    private OperationsSupportUnit operationsUnit;
    private List<PayrollRecord> payrollRecords;
    private DefaultTableModel tableModel;
    private Map<String, List<PayrollRecord>> historicalPayrolls; // 历史薪资记录

    public ManageEmployeeSalary(JPanel userProcessContainer, Enterprise enterprise, UserAccount userAccount) {
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.operationsUnit = findOperationsSupportUnit(enterprise);
        this.employeeDirectory = operationsUnit.getEmployeeDirectory();  // 假设一定非 null

        this.payrollRecords = new ArrayList<>();
        this.historicalPayrolls = new HashMap<>();

        initComponents();
        initializeTable();
        populateEmployeeData();
        loadHistoricalData();
    }


    private OperationsSupportUnit findOperationsSupportUnit(Enterprise enterprise) {
        for (Organization org : enterprise.getOrganizations().getOrganizationList()) {
            if (org instanceof OperationsSupportUnit) {
                return (OperationsSupportUnit) org;
            }
        }
        return null;
    }


    private void initializeTable() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Employee ID", "Employee Name", "Role", "Year", "Month", "Total Cost"});
        tblDonationHistory.setModel(tableModel);
    }

    private void populateEmployeeData() {
        // 填充员工下拉框
        cmbViewEmployeeName.removeAllItems();
        cmbSearch.removeAllItems();
        
        cmbViewEmployeeName.addItem("-- Select Employee --");
        cmbSearch.addItem("-- Search by Employee --");
        
        for (Employee emp : employeeDirectory.getEmployeeList()) {
            String empInfo = emp.getId() + " - " + emp.getName();
            cmbViewEmployeeName.addItem(empInfo);
            cmbSearch.addItem(empInfo);
        }
        
        refreshSalaryTable();
    }

    private void loadHistoricalData() {
        // 为每个员工生成历史薪资数据
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        
        for (Employee emp : employeeDirectory.getEmployeeList()) {
            List<PayrollRecord> empHistory = new ArrayList<>();
            
            // 生成过去12个月的薪资记录
            for (int i = 0; i < 12; i++) {
                cal.add(Calendar.MONTH, -1);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                
                PayrollRecord record = createHistoricalRecord(emp, year, month);
                empHistory.add(record);
            }
            
            historicalPayrolls.put(emp.getId(), empHistory);
        }
    }

    private PayrollRecord createHistoricalRecord(Employee emp, int year, int month) {
        PayrollRecord record = new PayrollRecord(emp);
        
        // 基于员工角色设置不同的基础工资
        double baseSalary = calculateBaseSalaryByRole(emp.getPosition());
        
        // 添加一些随机变动（奖金、扣除等）
        double bonus = Math.random() * 5000; // 0-5000的随机奖金
        double deductions = baseSalary * 0.1 + Math.random() * 1000; // 基础扣除 + 随机扣除
        
        record.setBaseSalary(baseSalary);
        record.setBonus(bonus);
        record.setDeductions(deductions);
        
        return record;
    }

    private double calculateBaseSalaryByRole(String role) {
        if (role == null) return 50000.0;
        
        switch (role.toLowerCase()) {
            case "doctor":
            case "physician":
                return 120000.0;
            case "nurse":
                return 70000.0;
            case "administrator":
            case "admin":
                return 80000.0;
            case "emergency responder":
                return 65000.0;
            case "logistics manager":
                return 75000.0;
            case "payroll staff":
                return 60000.0;
            default:
                return 50000.0;
        }
    }

    private void refreshSalaryTable() {
        tableModel.setRowCount(0);
        
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        
        // 显示所有员工的薪资历史
        for (Employee emp : employeeDirectory.getEmployeeList()) {
            List<PayrollRecord> empHistory = historicalPayrolls.get(emp.getId());
            if (empHistory != null) {
                for (PayrollRecord record : empHistory) {
                    double totalCost = record.calculateNetSalary() + 
                                     (record.getBaseSalary() * 0.15); // 加上雇主承担的税费和保险
                    
                    // 这里简化处理，假设记录按月倒序排列
                    cal.add(Calendar.MONTH, -1);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1;
                    
                    tableModel.addRow(new Object[]{
                        emp.getId(),
                        emp.getName(),
                        emp.getPosition(),
                        year,
                        month,
                        totalCost
                    });
                }
                
                // 重置日历
                cal = Calendar.getInstance();
            }
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
        jLabel16 = new javax.swing.JLabel();
        btnExportToCSV = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonationHistory = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnViewDetails = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cmbSearch = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnModifyPayroll = new javax.swing.JButton();
        txtViewTotalCost = new javax.swing.JTextField();
        txtViewDeductions = new javax.swing.JTextField();
        cmbViewEmployeeName = new javax.swing.JComboBox<>();
        txtViewHealthInsurance = new javax.swing.JTextField();
        txtViewCreateBasicSalary = new javax.swing.JTextField();
        txtViewCreateDepartment = new javax.swing.JTextField();
        txtViewCreateAllowances = new javax.swing.JTextField();
        txtViewCreateRole = new javax.swing.JTextField();
        txtViewUnemploymentTax = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Manage Employee Salary");

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel16.setText("View Employee Costs:");

        btnExportToCSV.setText("Export to csv");
        btnExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setText("Basic Salary:");

        tblDonationHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Employee Name", "Role", "Year", "Month", "Total Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDonationHistory);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel9.setText("Payroll Information:");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setText("Role:");

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel17.setText("Health Insurance:");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("Department:");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText("Employee Name:");

        btnViewDetails.setText("View Details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel19.setText("Search:");

        cmbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Allowances:");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setText("Deductions:");

        jLabel22.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel22.setText("Total Cost:");

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel23.setText("Unemployment Tax:");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnModifyPayroll.setText("Modify Payroll");
        btnModifyPayroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyPayrollActionPerformed(evt);
            }
        });

        cmbViewEmployeeName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(354, 354, 354)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(137, 137, 137)
                                        .addComponent(btnModifyPayroll, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(87, 87, 87))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnViewDetails, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtViewHealthInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtViewCreateAllowances, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtViewDeductions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtViewCreateBasicSalary, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cmbViewEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnExportToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtViewCreateDepartment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtViewCreateRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtViewTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtViewUnemploymentTax, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(168, 168, 168)))))
                .addContainerGap(343, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(103, 103, 103)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(58, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(btnSearch))
                .addGap(193, 193, 193)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnExportToCSV)
                    .addComponent(btnViewDetails))
                .addGap(9, 9, 9)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBack)
                            .addComponent(btnModifyPayroll))
                        .addGap(93, 93, 93))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbViewEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewCreateRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewCreateDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewCreateBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewCreateAllowances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewHealthInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewUnemploymentTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addContainerGap(153, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(109, 109, 109)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(493, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        int selectedRow = tblDonationHistory.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a salary record to delete.", 
                                        "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String employeeId = (String) tableModel.getValueAt(selectedRow, 0);
        String employeeName = (String) tableModel.getValueAt(selectedRow, 1);
        Object year = tableModel.getValueAt(selectedRow, 3);
        Object month = tableModel.getValueAt(selectedRow, 4);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            String.format("Are you sure you want to delete salary record for %s (%s/%s)?", 
                         employeeName, month, year),
            "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            // 删除指定的薪资记录
            List<PayrollRecord> empHistory = historicalPayrolls.get(employeeId);
            if (empHistory != null) {
                // 这里简化处理，删除第一个找到的记录
                if (!empHistory.isEmpty()) {
                    empHistory.remove(0);
                }
            }
            
            refreshSalaryTable();
            clearViewFields();
            
            JOptionPane.showMessageDialog(this, "Salary record deleted successfully!", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnModifyPayrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyPayrollActionPerformed
        String selectedEmployee = (String) cmbViewEmployeeName.getSelectedItem();
        if (selectedEmployee == null || selectedEmployee.startsWith("--")) {
            JOptionPane.showMessageDialog(this, "Please select an employee first.", 
                                        "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 解析选中的员工
            String employeeId = selectedEmployee.split(" - ")[0];
            Employee employee = employeeDirectory.findEmployeeById(employeeId);
            
            if (employee == null) {
                JOptionPane.showMessageDialog(this, "Employee not found.", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 从表单获取数据并验证
            double basicSalary = parseDoubleField(txtViewCreateBasicSalary.getText(), "Basic Salary");
            double allowances = parseDoubleField(txtViewCreateAllowances.getText(), "Allowances");
            double deductions = parseDoubleField(txtViewDeductions.getText(), "Deductions");
            double healthInsurance = parseDoubleField(txtViewHealthInsurance.getText(), "Health Insurance");
            double unemploymentTax = parseDoubleField(txtViewUnemploymentTax.getText(), "Unemployment Tax");
            
            // 创建新的薪资记录
            PayrollRecord newRecord = new PayrollRecord(employee);
            newRecord.setBaseSalary(basicSalary);
            newRecord.setBonus(allowances);
            newRecord.setDeductions(deductions + healthInsurance + unemploymentTax);
            
            // 更新员工的薪资记录
            employee.setPayrollRecord(newRecord);
            
            // 添加到历史记录
            List<PayrollRecord> empHistory = historicalPayrolls.get(employeeId);
            if (empHistory == null) {
                empHistory = new ArrayList<>();
                historicalPayrolls.put(employeeId, empHistory);
            }
            empHistory.add(0, newRecord); // 添加到列表开头（最新记录）
            
            // 计算总成本
            double totalCost = basicSalary + allowances + healthInsurance + unemploymentTax;
            txtViewTotalCost.setText(String.format("%.2f", totalCost));
            
            // 如果是Operations Support Unit，创建薪资请求
            if (operationsUnit != null) {
                Calendar cal = Calendar.getInstance();
                Date payStart = cal.getTime();
                cal.add(Calendar.MONTH, 1);
                Date payEnd = cal.getTime();
                cal.add(Calendar.WEEK_OF_MONTH, 1);
                Date payDate = cal.getTime();
                
                PayrollRequest request = operationsUnit.createPayrollRequest(employee, payStart, payEnd, payDate);
                request.setStatus("Modified");
            }
            
            refreshSalaryTable();
            JOptionPane.showMessageDialog(this, "Employee salary updated successfully!", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                                        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format: " + e.getMessage(), 
                                        "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating salary: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifyPayrollActionPerformed

    private void btnExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Salary Data as CSV");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV files", "csv"));
        fileChooser.setSelectedFile(new java.io.File("salary_history_" + 
            new java.text.SimpleDateFormat("yyyyMMdd").format(new Date()) + ".csv"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                exportSalaryHistoryToCSV(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Salary history exported successfully!", 
                                            "Export Complete", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error exporting data: " + e.getMessage(), 
                                            "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnExportToCSVActionPerformed

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        int selectedRow = tblDonationHistory.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a salary record to view.", 
                                        "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String employeeId = (String) tableModel.getValueAt(selectedRow, 0);
        Employee employee = employeeDirectory.findEmployeeById(employeeId);
        
        if (employee == null) {
            JOptionPane.showMessageDialog(this, "Employee not found.", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 更新查看区域的员工下拉框
        String empInfo = employee.getId() + " - " + employee.getName();
        cmbViewEmployeeName.setSelectedItem(empInfo);
        
        // 显示选中行对应的薪资详细信息
        Object year = tableModel.getValueAt(selectedRow, 3);
        Object month = tableModel.getValueAt(selectedRow, 4);
        
        displayEmployeeSalaryDetails(employee, year, month);
    }//GEN-LAST:event_btnViewDetailsActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchCriteria = (String) cmbSearch.getSelectedItem();
        if (searchCriteria == null || searchCriteria.startsWith("--")) {
            refreshSalaryTable(); // 显示所有记录
            return;
        }

        // 过滤表格数据
        tableModel.setRowCount(0);
        
        String employeeId = searchCriteria.split(" - ")[0];
        Employee employee = employeeDirectory.findEmployeeById(employeeId);
        
        if (employee != null) {
            List<PayrollRecord> empHistory = historicalPayrolls.get(employeeId);
            if (empHistory != null) {
                Calendar cal = Calendar.getInstance();
                
                for (PayrollRecord record : empHistory) {
                    double totalCost = record.calculateNetSalary() + 
                                     (record.getBaseSalary() * 0.15);
                    
                    cal.add(Calendar.MONTH, -1);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1;
                    
                    tableModel.addRow(new Object[]{
                        employee.getId(),
                        employee.getName(),
                        employee.getPosition(),
                        year,
                        month,
                        totalCost
                    });
                }
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed
    private void displayEmployeeSalaryDetails(Employee employee, Object year, Object month) {
        // 根据年月查找对应的薪资记录
        List<PayrollRecord> empHistory = historicalPayrolls.get(employee.getId());
        PayrollRecord targetRecord = null;
        
        if (empHistory != null && !empHistory.isEmpty()) {
            // 简化处理，使用最新的记录
            targetRecord = empHistory.get(0);
        }
        
        if (targetRecord == null) {
            // 如果没有历史记录，创建一个默认记录
            targetRecord = new PayrollRecord(employee);
            targetRecord.setBaseSalary(calculateBaseSalaryByRole(employee.getPosition()));
            targetRecord.setBonus(0.0);
            targetRecord.setDeductions(targetRecord.getBaseSalary() * 0.1);
        }
        
        // 填充详细信息
        txtViewCreateRole.setText(employee.getPosition() != null ? employee.getPosition() : "");
        txtViewCreateDepartment.setText(employee.getDepartment() != null ? employee.getDepartment() : "");
        txtViewCreateBasicSalary.setText(String.format("%.2f", targetRecord.getBaseSalary()));
        txtViewCreateAllowances.setText(String.format("%.2f", targetRecord.getBonus()));
        txtViewDeductions.setText(String.format("%.2f", targetRecord.getDeductions()));
        
        // 计算分项数据
        double healthInsurance = targetRecord.getBaseSalary() * 0.05; // 5% 健康保险
        double unemploymentTax = targetRecord.getBaseSalary() * 0.03; // 3% 失业税
        double totalCost = targetRecord.getBaseSalary() + targetRecord.getBonus() + 
                          healthInsurance + unemploymentTax;
        
        txtViewHealthInsurance.setText(String.format("%.2f", healthInsurance));
        txtViewUnemploymentTax.setText(String.format("%.2f", unemploymentTax));
        txtViewTotalCost.setText(String.format("%.2f", totalCost));
    }

    private void clearViewFields() {
        txtViewCreateRole.setText("");
        txtViewCreateDepartment.setText("");
        txtViewCreateBasicSalary.setText("");
        txtViewCreateAllowances.setText("");
        txtViewDeductions.setText("");
        txtViewHealthInsurance.setText("");
        txtViewUnemploymentTax.setText("");
        txtViewTotalCost.setText("");
    }

    private double parseDoubleField(String text, String fieldName) throws NumberFormatException {
        if (text == null || text.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(fieldName + " must be a valid number");
        }
    }

    private void exportSalaryHistoryToCSV(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        
        // 写入标题行
        writer.append("Employee ID,Employee Name,Role,Department,Year,Month,Basic Salary,Allowances,Deductions,Health Insurance,Unemployment Tax,Total Cost\n");
        
        // 写入数据行
        for (Employee emp : employeeDirectory.getEmployeeList()) {
            List<PayrollRecord> empHistory = historicalPayrolls.get(emp.getId());
            if (empHistory != null) {
                Calendar cal = Calendar.getInstance();
                
                for (int i = 0; i < empHistory.size(); i++) {
                    PayrollRecord record = empHistory.get(i);
                    
                    cal.add(Calendar.MONTH, -i);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1;
                    
                    double healthInsurance = record.getBaseSalary() * 0.05;
                    double unemploymentTax = record.getBaseSalary() * 0.03;
                    double totalCost = record.getBaseSalary() + record.getBonus() + 
                                      healthInsurance + unemploymentTax;
                    
                    writer.append(String.format("%s,%s,%s,%s,%d,%d,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f\n",
                        emp.getId(),
                        emp.getName().replace(",", ";"),
                        emp.getPosition() != null ? emp.getPosition().replace(",", ";") : "",
                        emp.getDepartment() != null ? emp.getDepartment().replace(",", ";") : "",
                        year,
                        month,
                        record.getBaseSalary(),
                        record.getBonus(),
                        record.getDeductions(),
                        healthInsurance,
                        unemploymentTax,
                        totalCost
                    ));
                    
                    // 重置日历
                    cal = Calendar.getInstance();
                }
            }
        }
        
        writer.close();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportToCSV;
    private javax.swing.JButton btnModifyPayroll;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JComboBox<String> cmbSearch;
    private javax.swing.JComboBox<String> cmbViewEmployeeName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDonationHistory;
    private javax.swing.JTextField txtViewCreateAllowances;
    private javax.swing.JTextField txtViewCreateBasicSalary;
    private javax.swing.JTextField txtViewCreateDepartment;
    private javax.swing.JTextField txtViewCreateRole;
    private javax.swing.JTextField txtViewDeductions;
    private javax.swing.JTextField txtViewHealthInsurance;
    private javax.swing.JTextField txtViewTotalCost;
    private javax.swing.JTextField txtViewUnemploymentTax;
    // End of variables declaration//GEN-END:variables
}
