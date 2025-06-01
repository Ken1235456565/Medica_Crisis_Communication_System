/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.DeliveryStaff;

import Model.Organization.Organization;
import Model.Supplies.DeliveryCatalog;
import Model.Supplies.Delivery;
import Model.User.UserAccount;
import util.CSVExporter;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author tiankaining
 */
public class ManageDeliveryTasks extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private DeliveryCatalog deliveryCatalog;
    private CSVExporter csvExporter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public ManageDeliveryTasks(JPanel userProcessContainer, Organization organization, UserAccount userAccount, DeliveryCatalog deliveryCatalog) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.deliveryCatalog = deliveryCatalog;
        this.csvExporter = new CSVExporter();
        
        initComponents();
        initializeFilters();
        loadDeliveryTasks();
    }

    private void initializeFilters() {
        // 初始化任务状态过滤器
        cmbTaskStatus.removeAllItems();
        cmbTaskStatus.addItem("全部");
        cmbTaskStatus.addItem("Pending");
        cmbTaskStatus.addItem("In Transit");
        cmbTaskStatus.addItem("Delivered");
        cmbTaskStatus.addItem("Cancelled");
        
        // 初始化日期过滤器
        cmbDateFilter.removeAllItems();
        cmbDateFilter.addItem("全部时间");
        cmbDateFilter.addItem("今天");
        cmbDateFilter.addItem("本周");
        cmbDateFilter.addItem("本月");
        cmbDateFilter.addItem("逾期");
    }

    
    private void loadDeliveryTasks() {
        try {
            loadAssignedTasks();
            loadDeliveryLog();
        } catch (Exception e) {
            showErrorMessage("加载配送任务失败: " + e.getMessage());
        }
    }

    
    private void loadAssignedTasks() {
        DefaultTableModel model = (DefaultTableModel) tblAssignedTasks.getModel();
        model.setRowCount(0);
        
        if (deliveryCatalog == null) return;
        
        String currentDriver = userAccount.getName();
        String statusFilter = (String) cmbTaskStatus.getSelectedItem();
        String dateFilter = (String) cmbDateFilter.getSelectedItem();
        String keywordFilter = txtKeywordSearch.getText().toLowerCase().trim();
        
        List<Delivery> deliveries = deliveryCatalog.getDeliveryList();
        for (Delivery delivery : deliveries) {
            if (isUserTask(delivery, currentDriver) && 
                matchesFilters(delivery, statusFilter, dateFilter, keywordFilter)) {
                addTaskToTable(model, delivery);
            }
        }
    }

    
    private boolean matchesFilters(Delivery delivery, String statusFilter, String dateFilter, String keywordFilter) {
        // 状态过滤
        if (!"全部".equals(statusFilter) && !statusFilter.equals(delivery.getStatus())) {
            return false;
        }
        
        // 日期过滤
        if (!matchesDateFilter(delivery, dateFilter)) {
            return false;
        }
        
        // 关键词过滤
        if (!keywordFilter.isEmpty()) {
            String searchText = (delivery.getDeliveryId() + " " + 
                               delivery.getDestination() + " " + 
                               (delivery.getNotes() != null ? delivery.getNotes() : "")).toLowerCase();
            if (!searchText.contains(keywordFilter)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean matchesDateFilter(Delivery delivery, String dateFilter) {
        if ("全部时间".equals(dateFilter)) {
            return true;
        }
        
        Date now = new Date();
        Date deliveryDate = delivery.getDeliveryDate();
        
        if (deliveryDate == null) {
            return false;
        }
        
        long dayInMillis = 24 * 60 * 60 * 1000;
        long timeDiff = now.getTime() - deliveryDate.getTime();
        
        switch (dateFilter) {
            case "今天":
                return Math.abs(timeDiff) < dayInMillis;
            case "本周":
                return timeDiff >= 0 && timeDiff < 7 * dayInMillis;
            case "本月":
                return timeDiff >= 0 && timeDiff < 30 * dayInMillis;
            case "逾期":
                return timeDiff > 0 && !"Delivered".equals(delivery.getStatus());
            default:
                return true;
        }
    }

    
    private boolean isUserTask(Delivery delivery, String currentDriver) {
        return currentDriver.equals(delivery.getDriverName());
    }

    
    private void addTaskToTable(DefaultTableModel model, Delivery delivery) {
        Object[] row = {
            delivery.getDeliveryId(),
            getRecipientName(delivery),
            delivery.getDestination(),
            delivery.getStatus(),
            delivery.getDeliveryDate() != null ? dateFormat.format(delivery.getDeliveryDate()) : "未设置"
        };
        model.addRow(row);
    }

   
    private String getRecipientName(Delivery delivery) {
        // 从destination中提取或使用默认值
        return "收件人"; // 可以扩展为从delivery的destination解析出收件人信息
    }

    
    private void loadDeliveryLog() {
        DefaultTableModel model = (DefaultTableModel) tblDeliveryLog.getModel();
        model.setRowCount(0);
        
        if (deliveryCatalog == null) return;
        
        String currentDriver = userAccount.getName();
        List<Delivery> deliveries = deliveryCatalog.getDeliveryList();
        
        // 显示最近的配送日志
        for (Delivery delivery : deliveries) {
            if (isUserTask(delivery, currentDriver) && 
                ("Delivered".equals(delivery.getStatus()) || "Cancelled".equals(delivery.getStatus()))) {
                addLogToTable(model, delivery);
            }
        }
    }

    
    private void addLogToTable(DefaultTableModel model, Delivery delivery) {
        String uploadTime = delivery.getDeliveryDate() != null ? 
            dateFormat.format(delivery.getDeliveryDate()) : "未知";
        String recipient = getRecipientName(delivery);
        String notes = delivery.getNotes() != null ? delivery.getNotes() : "无备注";
        
        Object[] row = {uploadTime, recipient, notes};
        model.addRow(row);
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
        cmbTaskStatus = new javax.swing.JComboBox<>();
        cmbDateFilter = new javax.swing.JComboBox<>();
        txtKeywordSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAssignedTasks = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDeliveryLog = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btnMarkAsDelivered = new javax.swing.JButton();
        btnViewDetails = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnExportToCSV = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("ViewDeliveryTasks");

        jLabel2.setText("Task status");

        jLabel3.setText("Date");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Assigned Tasks");

        tblAssignedTasks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Task ID", "Recipient", "Delivery Address", "Status", "Deadline"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAssignedTasks);

        tblDeliveryLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Upload time", "Recipient", "Notes/Status description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDeliveryLog);

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel6.setText("Delivery Log");

        btnMarkAsDelivered.setText("Mark as delivered");
        btnMarkAsDelivered.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarkAsDeliveredActionPerformed(evt);
            }
        });

        btnViewDetails.setText("View details");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnExportToCSV.setText("Export To CSV");
        btnExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVActionPerformed(evt);
            }
        });

        jLabel4.setText("Keyword:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnMarkAsDelivered, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)
                                        .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExportToCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(403, 403, 403)))
                        .addContainerGap(161, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbTaskStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbDateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)))
                                .addGap(40, 40, 40)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKeywordSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTaskStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKeywordSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(btnSearch)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMarkAsDelivered)
                    .addComponent(btnViewDetails))
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportToCSV)
                .addGap(30, 30, 30)
                .addComponent(btnBack)
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMarkAsDeliveredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarkAsDeliveredActionPerformed
        try {
            int selectedRow = tblAssignedTasks.getSelectedRow();
            if (selectedRow == -1) {
                showWarningMessage("请先选择一个任务");
                return;
            }
            
            String deliveryId = (String) tblAssignedTasks.getValueAt(selectedRow, 0);
            Delivery delivery = deliveryCatalog.findDeliveryById(deliveryId);
            
            if (delivery == null) {
                showErrorMessage("未找到选中的配送任务");
                return;
            }
            
            if ("Delivered".equals(delivery.getStatus())) {
                showWarningMessage("该任务已经标记为已配送");
                return;
            }
            
            // 确认操作
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "确认将任务 " + deliveryId + " 标记为已配送？",
                "确认操作",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                markAsDelivered(delivery);
                refreshData();
                showSuccessMessage("任务已标记为已配送");
            }
            
        } catch (Exception e) {
            showErrorMessage("标记任务失败: " + e.getMessage());
        }
    }//GEN-LAST:event_btnMarkAsDeliveredActionPerformed

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        try {
            int selectedRow = tblAssignedTasks.getSelectedRow();
            if (selectedRow == -1) {
                showWarningMessage("请先选择一个任务");
                return;
            }
            
            String deliveryId = (String) tblAssignedTasks.getValueAt(selectedRow, 0);
            Delivery delivery = deliveryCatalog.findDeliveryById(deliveryId);
            
            if (delivery == null) {
                showErrorMessage("未找到选中的配送任务");
                return;
            }
            
            showDeliveryDetails(delivery);
            
        } catch (Exception e) {
            showErrorMessage("查看详情失败: " + e.getMessage());
        }
    }//GEN-LAST:event_btnViewDetailsActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.previous(userProcessContainer);
        } catch (Exception e) {
            showErrorMessage("返回失败: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVActionPerformed
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnExportToCSVActionPerformed

    private void markAsDelivered(Delivery delivery) {
        delivery.completeDelivery();
        
        // 添加完成时间和配送员信息
        String completionNote = "配送完成 - 时间: " + dateFormat.format(new Date()) + 
                               ", 配送员: " + userAccount.getName();
        
        String existingNotes = delivery.getNotes();
        delivery.setNotes(existingNotes != null ? 
            existingNotes + "\n" + completionNote : completionNote);
    }


    private void showDeliveryDetails(Delivery delivery) {
        StringBuilder details = new StringBuilder();
        details.append("配送详情\n");
        details.append("================\n");
        details.append("任务ID: ").append(delivery.getDeliveryId()).append("\n");
        details.append("目的地: ").append(delivery.getDestination()).append("\n");
        details.append("状态: ").append(delivery.getStatus()).append("\n");
        details.append("配送日期: ").append(
            delivery.getDeliveryDate() != null ? 
            dateFormat.format(delivery.getDeliveryDate()) : "未设置").append("\n");
        details.append("配送员: ").append(delivery.getDriverName()).append("\n");
        details.append("使用车辆: ").append(
            delivery.getVehicleUsed() != null ? delivery.getVehicleUsed() : "未指定").append("\n");
        
        if (delivery.getItems() != null && !delivery.getItems().isEmpty()) {
            details.append("\n配送物品:\n");
            for (int i = 0; i < delivery.getItems().size(); i++) {
                var item = delivery.getItems().get(i);
                details.append("- ").append(item.getName())
                       .append(" (数量: ").append(item.getQuantity()).append(")\n");
            }
        }
        
        if (delivery.getNotes() != null && !delivery.getNotes().isEmpty()) {
            details.append("\n备注:\n").append(delivery.getNotes()).append("\n");
        }
        
        details.append("\n总价值: $").append(String.format("%.2f", delivery.getTotalDeliveryValue()));
        
        JOptionPane.showMessageDialog(this, details.toString(), "配送详情", JOptionPane.INFORMATION_MESSAGE);
        
        // 更新配送日志显示
        updateDeliveryLogForTask(delivery);
    }


    private void updateDeliveryLogForTask(Delivery delivery) {
        // 这里可以显示该任务的详细日志信息
        // 简化实现：清空日志表格并只显示当前任务的信息
        DefaultTableModel logModel = (DefaultTableModel) tblDeliveryLog.getModel();
        logModel.setRowCount(0);
        addLogToTable(logModel, delivery);
    }

    /**
     * 导出数据到CSV
     */
    private void exportToCSV() {
        StringBuilder csvContent = new StringBuilder();
        
        // 配送任务CSV
        csvContent.append("配送任务报告\n");
        csvContent.append("任务ID,收件人,配送地址,状态,截止日期\n");
        
        DefaultTableModel taskModel = (DefaultTableModel) tblAssignedTasks.getModel();
        for (int i = 0; i < taskModel.getRowCount(); i++) {
            for (int j = 0; j < taskModel.getColumnCount(); j++) {
                csvContent.append(taskModel.getValueAt(i, j));
                if (j < taskModel.getColumnCount() - 1) {
                    csvContent.append(",");
                }
            }
            csvContent.append("\n");
        }
        
        csvContent.append("\n配送日志\n");
        csvContent.append("上传时间,收件人,备注/状态描述\n");
        
        DefaultTableModel logModel = (DefaultTableModel) tblDeliveryLog.getModel();
        for (int i = 0; i < logModel.getRowCount(); i++) {
            for (int j = 0; j < logModel.getColumnCount(); j++) {
                csvContent.append(logModel.getValueAt(i, j));
                if (j < logModel.getColumnCount() - 1) {
                    csvContent.append(",");
                }
            }
            csvContent.append("\n");
        }
        
        // 显示导出结果（实际实现中应该保存到文件）
        String filename = "delivery_tasks_" + userAccount.getName().replaceAll("\\s+", "_") + 
                         "_" + System.currentTimeMillis() + ".csv";
        
        JOptionPane.showMessageDialog(this, 
            "数据已导出到: " + filename + "\n\n预览:\n" + csvContent.substring(0, Math.min(500, csvContent.length())) + "...", 
            "导出成功", 
            JOptionPane.INFORMATION_MESSAGE);
    }


    private void refreshData() {
        loadDeliveryTasks();
    }
    private void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Warning",
            JOptionPane.WARNING_MESSAGE);
    }
    
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "错误", JOptionPane.ERROR_MESSAGE);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExportToCSV;
    private javax.swing.JButton btnMarkAsDelivered;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JComboBox<String> cmbDateFilter;
    private javax.swing.JComboBox<String> cmbTaskStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAssignedTasks;
    private javax.swing.JTable tblDeliveryLog;
    private javax.swing.JTextField txtKeywordSearch;
    // End of variables declaration//GEN-END:variables
}
