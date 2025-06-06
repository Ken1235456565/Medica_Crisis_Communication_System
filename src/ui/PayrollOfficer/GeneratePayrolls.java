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

/**
 *
 * @author tiankaining
 */
public class GeneratePayrolls extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private OrganizationDirectory organizationDirectory;
    private UserAccount userAccount;
    private EmployeeDirectory employeeDirectory;
    private OperationsSupportUnit operationsUnit;
    private List<PayrollRecord> payrollRecords;
    private DefaultTableModel tableModel;

public GeneratePayrolls(JPanel userProcessContainer, Enterprise enterprise, UserAccount userAccount) {
    this.userProcessContainer = userProcessContainer;
    this.userAccount = userAccount;
    this.operationsUnit = findOperationsSupportUnit(enterprise);
    if (this.operationsUnit != null) {
        this.employeeDirectory = operationsUnit.getEmployeeDirectory();
    } else {
        System.err.println("OperationsSupportUnit not found.");
    }
    this.payrollRecords = new ArrayList<>();

    initComponents();
    initializeTable();
    populateEmployeeData();
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
        tableModel.setColumnIdentifiers(new String[]{"Employee ID", "Employee Name", "Role", "Total Cost"});
        tblDonationHistory.setModel(tableModel);
    }

private void populateEmployeeData() {
    // 如果员工列表为空，加入一些默认员工
    if (employeeDirectory.getEmployeeList().isEmpty()) {
        employeeDirectory.addEmployee(new Employee("E001", "Alice Johnson"));
        employeeDirectory.addEmployee(new Employee("E002", "Bob Smith"));
        employeeDirectory.addEmployee(new Employee("E003", "Carol White"));
        employeeDirectory.addEmployee(new Employee("E004", "David Brown"));
        employeeDirectory.addEmployee(new Employee("E005", "Eva Green"));
    }

    // 清空旧下拉框内容
    cmbCreateEmployeeName.removeAllItems();
    cmbViewEmployeeName.removeAllItems();
    cmbSearch.removeAllItems();

    // 添加默认提示项
    cmbCreateEmployeeName.addItem("-- Select Employee --");
    cmbViewEmployeeName.addItem("-- Select Employee --");
    cmbSearch.addItem("-- Search by Employee --");

    // 填充员工数据
    for (Employee emp : employeeDirectory.getEmployeeList()) {
        String empInfo = emp.getId() + " - " + emp.getName();
        cmbCreateEmployeeName.addItem(empInfo);
        cmbViewEmployeeName.addItem(empInfo);
        cmbSearch.addItem(empInfo);
    }

    // 更新工资表
    refreshPayrollTable();
}


    private void refreshPayrollTable() {
        tableModel.setRowCount(0);
        
        // 获取所有薪资记录
        if (operationsUnit != null) {
            for (Employee emp : employeeDirectory.getEmployeeList()) {
                PayrollRecord record = findOrCreatePayrollRecord(emp);
                if (record != null) {
                    double totalCost = record.calculateNetSalary();
                    tableModel.addRow(new Object[]{
                        emp.getId(),
                        emp.getName(),
                        emp.getPosition(),
                        totalCost
                    });
                }
            }
        }
    }

    private PayrollRecord findOrCreatePayrollRecord(Employee employee) {
        // 首先查找现有记录
        for (PayrollRecord record : payrollRecords) {
            if (record.getEmployee().getId().equals(employee.getId())) {
                return record;
            }
        }
        
        // 如果没有找到，创建新记录
        PayrollRecord newRecord = new PayrollRecord(employee);
        // 设置默认值
        newRecord.setBaseSalary(50000.0); // 默认基础工资
        newRecord.setBonus(0.0);
        newRecord.setDeductions(5000.0); // 默认扣除
        
        payrollRecords.add(newRecord);
        employee.setPayrollRecord(newRecord);
        
        return newRecord;
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
        tblDonationHistory = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCreateBasicSalary = new javax.swing.JTextField();
        txtCreateDepartment = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCreateAllowances = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCreateRole = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnModify = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnExportToCSV = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCreateDeductions = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt = new javax.swing.JLabel();
        txtCreateHealthInsurance = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnViewDetails = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtCreateUnemploymentTax = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtCreateTotalCost = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cmbCreateEmployeeName = new javax.swing.JComboBox<>();
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
        cmbSearch = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Generate Payrolls");

        tblDonationHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Employee ID", "Employee Name", "Role", "Total Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDonationHistory);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel9.setText("Payroll Information:");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setText("Role:");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel13.setText("Department:");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("Department:");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Allowances:");

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel14.setText("Allowances:");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setText("Deductions:");

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel16.setText("View Employee Costs:");

        btnModify.setText("Modify");
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel12.setText("Deductions:");

        btnExportToCSV.setText("Export to csv");
        btnExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setText("Basic Salary:");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel11.setText("Role:");

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel10.setText("Employee Name:");

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel18.setText("Health Insurance:");

        txt.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        txt.setText("Health Insurance:");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Create Employee Costs:");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText("Employee Name:");

        btnViewDetails.setText("View Details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel15.setText("Basic Salary:");

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel19.setText("Search:");

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel20.setText("Unemployment Tax:");

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel21.setText("Total Cost:");

        jLabel22.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel22.setText("Total Cost:");

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel23.setText("Unemployment Tax:");

        cmbCreateEmployeeName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));

        cmbViewEmployeeName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cmbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(btnExportToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(52, 52, 52)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtCreateAllowances, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtCreateDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(81, 81, 81)
                                                .addComponent(txtCreateBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(81, 81, 81)
                                                .addComponent(cmbCreateEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel5)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(81, 81, 81)
                                            .addComponent(txtCreateDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(81, 81, 81)
                                            .addComponent(txtCreateRole, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(272, 272, 272)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(214, 214, 214))
                                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtViewHealthInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtViewCreateAllowances, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtViewDeductions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtViewCreateBasicSalary, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbViewEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtViewCreateDepartment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtViewCreateRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtViewTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtViewUnemploymentTax, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addComponent(txtCreateHealthInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addComponent(txtCreateTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addComponent(txtCreateUnemploymentTax, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(242, 242, 242)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(jLabel1))
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addGap(96, 96, 96))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSearch))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnExportToCSV)
                            .addComponent(btnViewDetails))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(99, 99, 99)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(cmbCreateEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtCreateRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel4)
                                    .addComponent(txtCreateDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel8)
                                    .addComponent(txtCreateBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtCreateAllowances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtCreateDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt)
                            .addComponent(txtCreateHealthInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel23))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(txtCreateUnemploymentTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtCreateTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbViewEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViewCreateRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViewCreateDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViewCreateBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViewCreateAllowances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViewDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViewHealthInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViewUnemploymentTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViewTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModify)
                    .addComponent(btnSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack)
                .addGap(106, 106, 106))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblDonationHistory.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a payroll record to delete.", 
                                        "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String employeeId = (String) tableModel.getValueAt(selectedRow, 0);
        String employeeName = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete payroll record for " + employeeName + "?",
            "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            // 删除薪资记录
            payrollRecords.removeIf(record -> record.getEmployee().getId().equals(employeeId));
            
            // 从员工记录中移除
            Employee emp = employeeDirectory.findEmployeeById(employeeId);
            if (emp != null) {
                emp.setPayrollRecord(null);
            }
            
            refreshPayrollTable();
            clearViewFields();
            
            JOptionPane.showMessageDialog(this, "Payroll record deleted successfully!", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        String selectedEmployee = (String) cmbCreateEmployeeName.getSelectedItem();
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

            // 获取或创建薪资记录
            PayrollRecord record = findOrCreatePayrollRecord(employee);
            
            // 从表单获取数据并验证
            double basicSalary = parseDoubleField(txtCreateBasicSalary.getText(), "Basic Salary");
            double allowances = parseDoubleField(txtCreateAllowances.getText(), "Allowances");
            double deductions = parseDoubleField(txtCreateDeductions.getText(), "Deductions");
            double healthInsurance = parseDoubleField(txtCreateHealthInsurance.getText(), "Health Insurance");
            double unemploymentTax = parseDoubleField(txtCreateUnemploymentTax.getText(), "Unemployment Tax");
            
            // 更新薪资记录
            record.setBaseSalary(basicSalary);
            record.setBonus(allowances);
            record.setDeductions(deductions + healthInsurance + unemploymentTax);
            
            // 计算总成本
            double totalCost = basicSalary + allowances + healthInsurance + unemploymentTax;
            txtCreateTotalCost.setText(String.format("%.2f", totalCost));
            
            // 如果是Operations Support Unit，创建薪资请求
            if (operationsUnit != null) {
                Calendar cal = Calendar.getInstance();
                Date payStart = cal.getTime();
                cal.add(Calendar.MONTH, 1);
                Date payEnd = cal.getTime();
                cal.add(Calendar.WEEK_OF_MONTH, 1);
                Date payDate = cal.getTime();
                
                PayrollRequest request = operationsUnit.createPayrollRequest(employee, payStart, payEnd, payDate);
                request.setStatus("Updated");
            }
            
            refreshPayrollTable();
            JOptionPane.showMessageDialog(this, "Payroll record updated successfully!", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                                        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format: " + e.getMessage(), 
                                        "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating payroll: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Payroll Data as CSV");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV files", "csv"));
        fileChooser.setSelectedFile(new java.io.File("payroll_data_" + 
            new java.text.SimpleDateFormat("yyyyMMdd").format(new Date()) + ".csv"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                exportToCSV(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Data exported successfully!", 
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
            JOptionPane.showMessageDialog(this, "Please select a payroll record to view.", 
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
        
        // 显示员工详细信息
        displayEmployeeDetails(employee);
    }//GEN-LAST:event_btnViewDetailsActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchCriteria = (String) cmbSearch.getSelectedItem();
        if (searchCriteria == null || searchCriteria.startsWith("--")) {
            refreshPayrollTable(); // 显示所有记录
            return;
        }

        // 过滤表格数据
        tableModel.setRowCount(0);
        
        String employeeId = searchCriteria.split(" - ")[0];
        Employee employee = employeeDirectory.findEmployeeById(employeeId);
        
        if (employee != null) {
            PayrollRecord record = findOrCreatePayrollRecord(employee);
            if (record != null) {
                double totalCost = record.calculateNetSalary();
                tableModel.addRow(new Object[]{
                    employee.getId(),
                    employee.getName(),
                    employee.getPosition(),
                    totalCost
                });
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    String selectedEmployee = (String) cmbCreateEmployeeName.getSelectedItem();
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

        // 如果已存在记录，可提示用户使用“修改”按钮
        PayrollRecord existing = employee.getPayrollRecord();
        if (existing != null) {
            JOptionPane.showMessageDialog(this, "Payroll record already exists. Use Modify instead.", 
                                        "Already Exists", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 解析表单输入
        double basicSalary = parseDoubleField(txtCreateBasicSalary.getText(), "Basic Salary");
        double allowances = parseDoubleField(txtCreateAllowances.getText(), "Allowances");
        double deductions = parseDoubleField(txtCreateDeductions.getText(), "Deductions");
        double healthInsurance = parseDoubleField(txtCreateHealthInsurance.getText(), "Health Insurance");
        double unemploymentTax = parseDoubleField(txtCreateUnemploymentTax.getText(), "Unemployment Tax");

        // 创建薪资记录
        PayrollRecord record = new PayrollRecord(employee);
        record.setEmployee(employee);
        record.setBaseSalary(basicSalary);
        record.setBonus(allowances);
        record.setDeductions(deductions + healthInsurance + unemploymentTax);
        
        // 关联到员工
        employee.setPayrollRecord(record);

        // 显示总成本
        double totalCost = basicSalary + allowances + healthInsurance + unemploymentTax;
        txtCreateTotalCost.setText(String.format("%.2f", totalCost));

        // 如果属于运营单位，则生成一条请求
        if (operationsUnit != null) {
            Calendar cal = Calendar.getInstance();
            Date payStart = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            Date payEnd = cal.getTime();
            cal.add(Calendar.WEEK_OF_MONTH, 1);
            Date payDate = cal.getTime();
            
            PayrollRequest request = operationsUnit.createPayrollRequest(employee, payStart, payEnd, payDate);
            request.setStatus("Created");
        }

        refreshPayrollTable();
        JOptionPane.showMessageDialog(this, "Payroll record saved successfully!", 
                                    "Success", JOptionPane.INFORMATION_MESSAGE);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid number format: " + e.getMessage(), 
                                    "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error saving payroll: " + e.getMessage(), 
                                    "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnSaveActionPerformed
    private void displayEmployeeDetails(Employee employee) {
        PayrollRecord record = findOrCreatePayrollRecord(employee);
        
        if (record != null) {
            txtViewCreateRole.setText(employee.getPosition() != null ? employee.getPosition() : "");
            txtViewCreateDepartment.setText(employee.getDepartment() != null ? employee.getDepartment() : "");
            txtViewCreateBasicSalary.setText(String.format("%.2f", record.getBaseSalary()));
            txtViewCreateAllowances.setText(String.format("%.2f", record.getBonus()));
            txtViewDeductions.setText(String.format("%.2f", record.getDeductions()));
            
            // 计算分项数据（假设）
            double healthInsurance = record.getDeductions() * 0.3; // 30%
            double unemploymentTax = record.getDeductions() * 0.2; // 20%
            double totalCost = record.getBaseSalary() + record.getBonus() + healthInsurance + unemploymentTax;
            
            txtViewHealthInsurance.setText(String.format("%.2f", healthInsurance));
            txtViewUnemploymentTax.setText(String.format("%.2f", unemploymentTax));
            txtViewTotalCost.setText(String.format("%.2f", totalCost));
        } else {
            clearViewFields();
        }
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

    private void exportToCSV(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        
        // 写入标题行
        writer.append("Employee ID,Employee Name,Role,Department,Basic Salary,Allowances,Deductions,Health Insurance,Unemployment Tax,Total Cost\n");
        
        // 写入数据行
        for (Employee emp : employeeDirectory.getEmployeeList()) {
            PayrollRecord record = findOrCreatePayrollRecord(emp);
            if (record != null) {
                double healthInsurance = record.getDeductions() * 0.3;
                double unemploymentTax = record.getDeductions() * 0.2;
                double totalCost = record.getBaseSalary() + record.getBonus() + healthInsurance + unemploymentTax;
                
                writer.append(String.format("%s,%s,%s,%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f\n",
                    emp.getId(),
                    emp.getName().replace(",", ";"), // 避免CSV冲突
                    emp.getPosition() != null ? emp.getPosition().replace(",", ";") : "",
                    emp.getDepartment() != null ? emp.getDepartment().replace(",", ";") : "",
                    record.getBaseSalary(),
                    record.getBonus(),
                    record.getDeductions(),
                    healthInsurance,
                    unemploymentTax,
                    totalCost
                ));
            }
        }
        
        writer.close();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportToCSV;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JComboBox<String> cmbCreateEmployeeName;
    private javax.swing.JComboBox<String> cmbSearch;
    private javax.swing.JComboBox<String> cmbViewEmployeeName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDonationHistory;
    private javax.swing.JLabel txt;
    private javax.swing.JTextField txtCreateAllowances;
    private javax.swing.JTextField txtCreateBasicSalary;
    private javax.swing.JTextField txtCreateDeductions;
    private javax.swing.JTextField txtCreateDepartment;
    private javax.swing.JTextField txtCreateHealthInsurance;
    private javax.swing.JTextField txtCreateRole;
    private javax.swing.JTextField txtCreateTotalCost;
    private javax.swing.JTextField txtCreateUnemploymentTax;
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
