/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.DeliveryStaff;

import Model.Organization.Organization;
import Model.Supplies.DeliveryCatalog;
import Model.Supplies.Delivery;
import Model.User.UserAccount;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author tiankaining
 */
public class TaskInstructions extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private DeliveryCatalog deliveryCatalog;

    public TaskInstructions(JPanel userProcessContainer, Organization organization, UserAccount userAccount, DeliveryCatalog deliveryCatalog) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.deliveryCatalog = deliveryCatalog;
        
        initComponents();
        loadTaskData();
    }

    private void loadTaskData() {
        try {
            loadNearDeadlineTasks();
            loadOverdueTasks();
            loadProofNotUploadedTasks();
        } catch (Exception e) {
            showErrorMessage("加载任务数据失败: " + e.getMessage());
        }
    }
    
    private void loadNearDeadlineTasks() {
        DefaultTableModel model = (DefaultTableModel) tblTasksNearDeadline.getModel();
        model.setRowCount(0); // 清空表格
        
        if (deliveryCatalog == null) return;
        
        String currentDriver = userAccount.getName();
        Date now = new Date();
        long threeDaysInMillis = 3 * 24 * 60 * 60 * 1000; // 3天的毫秒数
        
        List<Delivery> deliveries = deliveryCatalog.getDeliveryList();
        for (Delivery delivery : deliveries) {
            // 检查是否是当前用户的任务且状态为待处理或进行中
            if (isUserTask(delivery, currentDriver) && 
                ("Pending".equals(delivery.getStatus()) || "In Transit".equals(delivery.getStatus()))) {
                
                // 检查是否接近截止日期（3天内）
                if (delivery.getDeliveryDate() != null) {
                    long timeDiff = delivery.getDeliveryDate().getTime() - now.getTime();
                    if (timeDiff > 0 && timeDiff <= threeDaysInMillis) {
                        addDeliveryToTable(model, delivery, "接近截止日期");
                    }
                }
            }
        }
    }
    
    private void loadOverdueTasks() {
        DefaultTableModel model = (DefaultTableModel) tblTasksOverdue.getModel();
        model.setRowCount(0);
        
        if (deliveryCatalog == null) return;
        
        String currentDriver = userAccount.getName();
        Date now = new Date();
        
        List<Delivery> deliveries = deliveryCatalog.getDeliveryList();
        for (Delivery delivery : deliveries) {
            if (isUserTask(delivery, currentDriver) && 
                !"Delivered".equals(delivery.getStatus()) && !"Cancelled".equals(delivery.getStatus())) {
                
                // 检查是否逾期
                if (delivery.getDeliveryDate() != null && delivery.getDeliveryDate().before(now)) {
                    addDeliveryToTable(model, delivery, "逾期");
                }
            }
        }
    }
    
    private void loadProofNotUploadedTasks() {
        DefaultTableModel model = (DefaultTableModel) tblProofNotUploaded.getModel();
        model.setRowCount(0);
        
        if (deliveryCatalog == null) return;
        
        String currentDriver = userAccount.getName();
        
        List<Delivery> deliveries = deliveryCatalog.getDeliveryList();
        for (Delivery delivery : deliveries) {
            if (isUserTask(delivery, currentDriver) && "Delivered".equals(delivery.getStatus())) {
                // 检查是否未上传证明（这里简化为检查notes字段是否包含证明信息）
                if (delivery.getNotes() == null || !delivery.getNotes().contains("证明已上传")) {
                    addDeliveryToTable(model, delivery, "需要上传证明");
                }
            }
        }
    }
    
    private boolean isUserTask(Delivery delivery, String currentDriver) {
        return currentDriver.equals(delivery.getDriverName());
    }
    
    private void addDeliveryToTable(DefaultTableModel model, Delivery delivery, String status) {
        Object[] row = {
            delivery.getDeliveryId(),
            delivery.getRecipientName(), // ✅ 正确调用方式
            delivery.getDestination(),
            status,
            delivery.getDeliveryDate() != null ? delivery.getDeliveryDate().toString() : "未设置",
            delivery.getNotes() != null ? delivery.getNotes() : "无备注"
        };
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
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTasksNearDeadline = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTasksOverdue = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProofNotUploaded = new javax.swing.JTable();
        btnMarkDelivered = new javax.swing.JButton();
        btnGoUpload = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("View Request Instructions");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("Requests near Deadline");

        tblTasksNearDeadline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Request ID", "Receipient", "Delivery address", "Status", "Deadline", "Request notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTasksNearDeadline.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblTasksNearDeadlineAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tblTasksNearDeadline);

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel6.setText("Requests Proof Unuploaded");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel7.setText("Requests overdue");

        tblTasksOverdue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Request ID", "Receipient", "Delivery address", "Status", "Deadline", "Request notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblTasksOverdue);

        tblProofNotUploaded.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Request ID", "Receipient", "Delivery address", "Status", "Deadline", "Request notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProofNotUploaded);

        btnMarkDelivered.setText("Mark as delivered");
        btnMarkDelivered.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarkDeliveredActionPerformed(evt);
            }
        });

        btnGoUpload.setText("Go Upload");
        btnGoUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoUploadActionPerformed(evt);
            }
        });

        btnBack.setText("Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMarkDelivered, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGoUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(396, 396, 396))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMarkDelivered)
                .addGap(46, 46, 46)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGoUpload)
                    .addComponent(btnBack))
                .addContainerGap(69, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMarkDeliveredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarkDeliveredActionPerformed
        try {
            // 获取选中的任务
            int selectedRow = getSelectedTaskRow();
            if (selectedRow == -1) {
                showWarningMessage("请先选择一个任务");
                return;
            }
            
            String deliveryId = getSelectedDeliveryId(selectedRow);
            Delivery delivery = deliveryCatalog.findDeliveryById(deliveryId);
            
            if (delivery == null) {
                showErrorMessage("未找到选中的配送任务");
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
                markDeliveryAsCompleted(delivery);
                refreshTaskData();
                showSuccessMessage("任务已标记为已配送");
            }
            
        } catch (Exception e) {
            showErrorMessage("标记任务失败: " + e.getMessage());
        }
    }//GEN-LAST:event_btnMarkDeliveredActionPerformed

    private void btnGoUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoUploadActionPerformed
        try {
            // 获取选中的任务
            int selectedRow = getSelectedTaskRow();
            if (selectedRow == -1) {
                showWarningMessage("请先选择一个需要上传证明的任务");
                return;
            }
            
            String deliveryId = getSelectedDeliveryId(selectedRow);
            Delivery delivery = deliveryCatalog.findDeliveryById(deliveryId);
            
            if (delivery == null) {
                showErrorMessage("未找到选中的配送任务");
                return;
            }
            
            // 跳转到上传证明页面
            UploadProof uploadPanel = new UploadProof(userProcessContainer, organization, userAccount, delivery);
            userProcessContainer.add("UploadProof", uploadPanel);
            
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.show(userProcessContainer, "UploadProof");
            
        } catch (Exception e) {
            showErrorMessage("跳转到上传证明失败: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGoUploadActionPerformed

    private void tblTasksNearDeadlineAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblTasksNearDeadlineAncestorAdded
        refreshTaskData();
    }//GEN-LAST:event_tblTasksNearDeadlineAncestorAdded
    private int getSelectedTaskRow() {
        // 检查哪个表格有选中行
        if (tblTasksNearDeadline.getSelectedRow() != -1) {
            return tblTasksNearDeadline.getSelectedRow();
        } else if (tblTasksOverdue.getSelectedRow() != -1) {
            return tblTasksOverdue.getSelectedRow();
        } else if (tblProofNotUploaded.getSelectedRow() != -1) {
            return tblProofNotUploaded.getSelectedRow();
        }
        return -1;
    }

    
    private String getSelectedDeliveryId(int selectedRow) {
        // 根据当前激活的表格获取配送ID
        if (tblTasksNearDeadline.getSelectedRow() == selectedRow) {
            return (String) tblTasksNearDeadline.getValueAt(selectedRow, 0);
        } else if (tblTasksOverdue.getSelectedRow() == selectedRow) {
            return (String) tblTasksOverdue.getValueAt(selectedRow, 0);
        } else if (tblProofNotUploaded.getSelectedRow() == selectedRow) {
            return (String) tblProofNotUploaded.getValueAt(selectedRow, 0);
        }
        return null;
    }

    
    private void markDeliveryAsCompleted(Delivery delivery) {
        delivery.completeDelivery();
        
        // 添加完成备注
        String completionNote = "配送完成于: " + new Date() + " by " + userAccount.getName();
        delivery.setNotes(delivery.getNotes() != null ? 
            delivery.getNotes() + "\n" + completionNote : completionNote);
    }

    
    private void refreshTaskData() {
        loadTaskData();
    }

   
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "错误", JOptionPane.ERROR_MESSAGE);
    }

    
    private void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "警告", JOptionPane.WARNING_MESSAGE);
    }

    
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "成功", JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnGoUpload;
    private javax.swing.JButton btnMarkDelivered;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblProofNotUploaded;
    private javax.swing.JTable tblTasksNearDeadline;
    private javax.swing.JTable tblTasksOverdue;
    // End of variables declaration//GEN-END:variables
}
