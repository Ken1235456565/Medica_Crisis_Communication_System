/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.EquipmentTechnician;

import Model.Organization.Organization;
import Model.Supplies.EquipmentsCatalog;
import Model.Supplies.Equipments;
import Model.User.UserAccount;
import Model.WorkQueue.RepairRequest;
import Model.Employee.Employee;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.CardLayout;

/**
 *
 * @author tiankaining
 */
public class ManageRepairStatus extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private EquipmentsCatalog equipmentsCatalog;
    private List<RepairRequest> repairRequests;
    private RepairRequest selectedRepairRequest;

    public ManageRepairStatus(JPanel userProcessContainer, Organization organization, UserAccount userAccount, EquipmentsCatalog equipmentsCatalog) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.equipmentsCatalog = equipmentsCatalog;
        this.repairRequests = new ArrayList<>();
        
        initComponents();
        initializeData();
        setupTableListener();
        populateTable();
    }

    private void initializeData() {
        // 创建示例维修请求
        createSampleRepairRequests();
    }

    private void createSampleRepairRequests() {
        // 获取一些设备来创建维修请求
        List<Equipments> equipmentsList = equipmentsCatalog.getEquipmentsList();
        
        if (!equipmentsList.isEmpty()) {
            RepairRequest request1 = new RepairRequest(
                equipmentsList.get(0), "张三", "Hardware", 3, 2, "zhangsan@example.com"
            );
            request1.setStatus("Pending");
            repairRequests.add(request1);

            if (equipmentsList.size() > 1) {
                RepairRequest request2 = new RepairRequest(
                    equipmentsList.get(1), "李四", "Software", 2, 1, "lisi@example.com"
                );
                request2.setStatus("In Progress");
                request2.setAssignedTechnician(userAccount.getEmployee());
                repairRequests.add(request2);
            }
        }

        // 创建一个通用的维修请求
        RepairRequest request3 = new RepairRequest(
            null, "王五", "Maintenance", 1, 3, "wangwu@example.com"
        );
        request3.setStatus("Completed");
        repairRequests.add(request3);
    }

    private void setupTableListener() {
        tblDataOverview.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    selectRepairRequest();
                }
            }
        });
    }

    private void selectRepairRequest() {
        int selectedRow = tblDataOverview.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < repairRequests.size()) {
            selectedRepairRequest = repairRequests.get(selectedRow);
            populateViewFields();
        }
    }

    private void populateViewFields() {
        if (selectedRepairRequest != null) {
            txtViewEquipment.setText(selectedRepairRequest.getEquipment() != null ? 
                selectedRepairRequest.getEquipment().getName() : "未指定设备");
            txtViewReporterName.setText(selectedRepairRequest.getReporterName());
            CmbViewIssueCategory.setSelectedItem(selectedRepairRequest.getIssueCategory());
            txtViewSeverityLevel.setText(String.valueOf(selectedRepairRequest.getSeverityLevel()));
            txtViewPriorityLevel.setText(String.valueOf(selectedRepairRequest.getPriorityLevel()));
            txtViewStatus.setText(selectedRepairRequest.getStatus());
        }
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tblDataOverview.getModel();
        model.setRowCount(0); // 清空表格

        for (RepairRequest request : repairRequests) {
            String technicianName = request.getAssignedTechnician() != null ? 
                request.getAssignedTechnician().getName() : "未分配";
            String equipmentName = request.getEquipment() != null ? 
                request.getEquipment().getName() : "未指定";
            
            Object[] row = {
                request.getRepairRequestId(),
                equipmentName,
                technicianName,
                request.getContactEmail(),
                request.getStatus()
            };
            model.addRow(row);
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
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDataOverview = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCreatePriorityLevel = new javax.swing.JTextField();
        txtCreateStatus = new javax.swing.JTextField();
        CmbCreateIssueCategory = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCreateSeverityLevel = new javax.swing.JTextField();
        txtCreateEquipment = new javax.swing.JTextField();
        txtCreateReporterName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnModify = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        btnSendEmail = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnExportToCSV = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtViewPriorityLevel = new javax.swing.JTextField();
        txtViewStatus = new javax.swing.JTextField();
        CmbViewIssueCategory = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtViewSeverityLevel = new javax.swing.JTextField();
        txtViewEquipment = new javax.swing.JTextField();
        txtViewReporterName = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Manage Repair Status");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tblDataOverview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Repair Request ID", "Equipment", "Techincian Name", "Contact Email", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDataOverview);

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel14.setText("View details:");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Priority Level:");

        CmbCreateIssueCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Medical supplies", "food", "daily necessities", "money" }));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("Equipment:");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setText("Status :");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setText("Severity Level:");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel5.setText("Reporter Name :");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel9.setText("Issue Category:");

        btnModify.setText("Modify");
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel10.setText("Create:");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnSendEmail.setText("Send Email");
        btnSendEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendEmailActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnExportToCSV.setText("Export to csv");
        btnExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("Data overview:");

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel18.setText("Reporter Name :");

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel19.setText("Issue Category:");

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel20.setText("Priority Level:");

        CmbViewIssueCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Medical supplies", "food", "daily necessities", "money" }));

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel21.setText("Equipment:");

        jLabel22.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel22.setText("Status :");

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel23.setText("Severity Level:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(jLabel1)
                .addContainerGap(391, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtViewPriorityLevel)
                    .addComponent(txtViewStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtViewSeverityLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtViewEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtViewReporterName)
                            .addComponent(CmbViewIssueCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(98, 98, 98))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnExportToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(48, 48, 48)
                                    .addComponent(btnSendEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel10)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(6, 6, 6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCreatePriorityLevel)
                                        .addComponent(txtCreateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCreateSeverityLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCreateEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtCreateReporterName)
                                                .addComponent(CmbCreateIssueCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGap(186, 186, 186)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(177, 177, 177)
                                            .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jScrollPane1))
                    .addContainerGap(95, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtViewEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtViewReporterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(CmbViewIssueCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtViewSeverityLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtViewPriorityLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtViewStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(183, 183, 183))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(147, 147, 147)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnSendEmail)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnExportToCSV)
                                    .addComponent(btnDelete)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtCreateEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCreateReporterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(CmbCreateIssueCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtCreateSeverityLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtCreatePriorityLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtCreateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(180, 180, 180)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCreate)
                        .addComponent(btnModify))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnBack)
                    .addContainerGap(110, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try {
            // 验证输入
            if (!validateCreateInput()) {
                return;
            }

            // 从表单获取数据
            String equipmentName = txtCreateEquipment.getText().trim();
            String reporterName = txtCreateReporterName.getText().trim();
            String issueCategory = (String) CmbCreateIssueCategory.getSelectedItem();
            int severityLevel = Integer.parseInt(txtCreateSeverityLevel.getText().trim());
            int priorityLevel = Integer.parseInt(txtCreatePriorityLevel.getText().trim());
            String status = txtCreateStatus.getText().trim();

            // 查找对应的设备
            Equipments equipment = findEquipmentByName(equipmentName);
            
            // 创建新的维修请求
            RepairRequest newRequest = new RepairRequest(
                equipment, reporterName, issueCategory, priorityLevel, severityLevel, ""
            );
            newRequest.setStatus(status.isEmpty() ? "Pending" : status);
            newRequest.setSender(userAccount);

            // 如果是当前用户创建的，可以自动分配给自己
            if (userAccount.getRole().getName().contains("Technician")) {
                newRequest.setAssignedTechnician(userAccount.getEmployee());
            }

            // 添加到列表
            repairRequests.add(newRequest);

            // 添加到组织的工作队列
            organization.addWorkRequest(newRequest);

            // 刷新表格
            populateTable();

            // 清空输入字段
            clearCreateFields();

            JOptionPane.showMessageDialog(this, 
                "维修请求创建成功！请求ID: " + newRequest.getRepairRequestId(), 
                "成功", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "严重等级和优先级必须是数字", 
                "输入错误", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "创建维修请求时出错: " + e.getMessage(), 
                "错误", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateActionPerformed
    private boolean validateCreateInput() {
        if (txtCreateEquipment.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入设备名称", "输入错误", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtCreateReporterName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入报告人姓名", "输入错误", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtCreateSeverityLevel.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入严重等级", "输入错误", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtCreatePriorityLevel.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入优先级", "输入错误", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int severity = Integer.parseInt(txtCreateSeverityLevel.getText().trim());
            int priority = Integer.parseInt(txtCreatePriorityLevel.getText().trim());
            if (severity < 1 || severity > 5 || priority < 1 || priority > 5) {
                JOptionPane.showMessageDialog(this, "严重等级和优先级必须在1-5之间", "输入错误", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "严重等级和优先级必须是数字", "输入错误", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private Equipments findEquipmentByName(String name) {
        for (Equipments equipment : equipmentsCatalog.getEquipmentsList()) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return equipment;
            }
        }
        return null; // 如果找不到设备，返回null
    }

    private void clearCreateFields() {
        txtCreateEquipment.setText("");
        txtCreateReporterName.setText("");
        CmbCreateIssueCategory.setSelectedIndex(0);
        txtCreateSeverityLevel.setText("");
        txtCreatePriorityLevel.setText("");
        txtCreateStatus.setText("");
    }
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // 返回到技术员工作区主页面
        try {
            EquipmentTechnicianWorkAreaPanel mainPanel = new EquipmentTechnicianWorkAreaPanel(
                userProcessContainer, organization, userAccount);
            
            userProcessContainer.removeAll();
            userProcessContainer.add(mainPanel, "TechnicianWorkArea");
            userProcessContainer.revalidate();
            userProcessContainer.repaint();
            
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.show(userProcessContainer, "TechnicianWorkArea");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "返回主页面时出错: " + e.getMessage(), 
                "错误", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        if (selectedRepairRequest == null) {
            JOptionPane.showMessageDialog(this, "请先选择一个维修请求", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 从查看字段获取修改后的数据
            String equipmentName = txtViewEquipment.getText().trim();
            selectedRepairRequest.setEquipment(findEquipmentByName(equipmentName));
            selectedRepairRequest.setReporterName(txtViewReporterName.getText().trim());
            selectedRepairRequest.setIssueCategory((String) CmbViewIssueCategory.getSelectedItem());
            selectedRepairRequest.setSeverityLevel(Integer.parseInt(txtViewSeverityLevel.getText().trim()));
            selectedRepairRequest.setPriorityLevel(Integer.parseInt(txtViewPriorityLevel.getText().trim()));
            selectedRepairRequest.setStatus(txtViewStatus.getText().trim());

            // 如果状态改为"In Progress"，开始维修
            if ("In Progress".equals(selectedRepairRequest.getStatus()) && 
                selectedRepairRequest.getAssignedTechnician() == null) {
                selectedRepairRequest.setAssignedTechnician(userAccount.getEmployee());
                selectedRepairRequest.startRepair();
            }

            // 如果状态改为"Completed"，完成维修
            if ("Completed".equals(selectedRepairRequest.getStatus())) {
                selectedRepairRequest.completeRepair();
            }

            // 刷新表格
            populateTable();

            JOptionPane.showMessageDialog(this, "维修请求修改成功！", "成功", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "严重等级和优先级必须是数字", "输入错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "修改维修请求时出错: " + e.getMessage(), 
                "错误", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVActionPerformed
        try {
            String fileName = "repair_requests_" + System.currentTimeMillis() + ".csv";
            FileWriter writer = new FileWriter(fileName);

            // 写入CSV头
            writer.append("Request ID,Equipment,Technician,Contact Email,Status,Reporter,Category,Severity,Priority\n");

            // 写入数据
            for (RepairRequest request : repairRequests) {
                String technicianName = request.getAssignedTechnician() != null ? 
                    request.getAssignedTechnician().getName() : "未分配";
                String equipmentName = request.getEquipment() != null ? 
                    request.getEquipment().getName() : "未指定";
                
                writer.append(String.format("%s,%s,%s,%s,%s,%s,%s,%d,%d\n",
                    request.getRepairRequestId(),
                    equipmentName,
                    technicianName,
                    request.getContactEmail(),
                    request.getStatus(),
                    request.getReporterName(),
                    request.getIssueCategory(),
                    request.getSeverityLevel(),
                    request.getPriorityLevel()
                ));
            }

            writer.flush();
            writer.close();

            JOptionPane.showMessageDialog(this, 
                "数据已导出到文件: " + fileName, 
                "导出成功", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "导出CSV文件时出错: " + e.getMessage(), 
                "导出错误", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExportToCSVActionPerformed

    private void btnSendEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendEmailActionPerformed
        if (selectedRepairRequest == null) {
            JOptionPane.showMessageDialog(this, "请先选择一个维修请求", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 模拟发送邮件
        String emailContent = String.format(
            "维修请求状态更新\n\n" +
            "请求ID: %s\n" +
            "设备名称: %s\n" +
            "当前状态: %s\n" +
            "分配技术员: %s\n" +
            "优先级: %d\n\n" +
            "感谢您的耐心等待。",
            selectedRepairRequest.getRepairRequestId(),
            selectedRepairRequest.getEquipment() != null ? selectedRepairRequest.getEquipment().getName() : "未指定",
            selectedRepairRequest.getStatus(),
            selectedRepairRequest.getAssignedTechnician() != null ? 
                selectedRepairRequest.getAssignedTechnician().getName() : "未分配",
            selectedRepairRequest.getPriorityLevel()
        );

        JOptionPane.showMessageDialog(this, 
            "邮件发送模拟:\n收件人: " + selectedRepairRequest.getContactEmail() + "\n\n" + emailContent, 
            "邮件发送", 
            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnSendEmailActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedRepairRequest == null) {
            JOptionPane.showMessageDialog(this, "请先选择一个维修请求", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, 
            "确定要删除维修请求 " + selectedRepairRequest.getRepairRequestId() + " 吗？", 
            "确认删除", 
            JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            repairRequests.remove(selectedRepairRequest);
            selectedRepairRequest = null;
            populateTable();
            clearViewFields();
            JOptionPane.showMessageDialog(this, "维修请求删除成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    private void clearViewFields() {
        txtViewEquipment.setText("");
        txtViewReporterName.setText("");
        CmbViewIssueCategory.setSelectedIndex(0);
        txtViewSeverityLevel.setText("");
        txtViewPriorityLevel.setText("");
        txtViewStatus.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbCreateIssueCategory;
    private javax.swing.JComboBox<String> CmbViewIssueCategory;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportToCSV;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnSendEmail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDataOverview;
    private javax.swing.JTextField txtCreateEquipment;
    private javax.swing.JTextField txtCreatePriorityLevel;
    private javax.swing.JTextField txtCreateReporterName;
    private javax.swing.JTextField txtCreateSeverityLevel;
    private javax.swing.JTextField txtCreateStatus;
    private javax.swing.JTextField txtViewEquipment;
    private javax.swing.JTextField txtViewPriorityLevel;
    private javax.swing.JTextField txtViewReporterName;
    private javax.swing.JTextField txtViewSeverityLevel;
    private javax.swing.JTextField txtViewStatus;
    // End of variables declaration//GEN-END:variables
}
