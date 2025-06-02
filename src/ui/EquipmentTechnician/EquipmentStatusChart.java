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
import Model.WorkQueue.WorkRequest;
import Model.Employee.Employee;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tiankaining
 */
public class EquipmentStatusChart extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private EquipmentsCatalog equipmentsCatalog;

    public EquipmentStatusChart(JPanel userProcessContainer, Organization organization, 
                               UserAccount userAccount, EquipmentsCatalog equipmentsCatalog) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.equipmentsCatalog = equipmentsCatalog;
        
        initComponents();
        loadStatusData();
        loadTechnicianWorkHistory();
    }

    private void loadStatusData() {
        DefaultTableModel model = (DefaultTableModel) tblDataOverview.getModel();
        model.setRowCount(0); // 清空表格

        // 获取所有设备
        List<Equipments> equipmentsList = equipmentsCatalog.getEquipmentsList();
        
        // 获取所有工作请求来统计每个设备的请求数量
        List<WorkRequest> allRequests = organization.getWorkQueue();
        
        for (Equipments equipment : equipmentsList) {
            // 统计该设备相关的请求
            Map<String, Integer> statusCounts = getRequestStatusCounts(equipment, allRequests);
            
            Object[] row = {
                equipment.getEquipmentId(),
                equipment.getName(),
                statusCounts.get("total"),
                statusCounts.get("inProgress"),
                statusCounts.get("completed")
            };
            model.addRow(row);
        }

        // 如果没有设备，显示示例数据
        if (equipmentsList.isEmpty()) {
            addSampleEquipmentData(model);
        }
    }

    private Map<String, Integer> getRequestStatusCounts(Equipments equipment, List<WorkRequest> allRequests) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("total", 0);
        counts.put("inProgress", 0);
        counts.put("completed", 0);

        for (WorkRequest request : allRequests) {
            if (request instanceof RepairRequest) {
                RepairRequest repairRequest = (RepairRequest) request;
                if (repairRequest.getEquipment() != null && 
                    repairRequest.getEquipment().equals(equipment)) {
                    
                    counts.put("total", counts.get("total") + 1);
                    
                    String status = repairRequest.getStatus();
                    if ("In Progress".equals(status) || "Assigned".equals(status)) {
                        counts.put("inProgress", counts.get("inProgress") + 1);
                    } else if ("Completed".equals(status)) {
                        counts.put("completed", counts.get("completed") + 1);
                    }
                }
            }
        }

        return counts;
    }

    private void addSampleEquipmentData(DefaultTableModel model) {
        Object[][] sampleData = {
            {"EQP001", "MRI Machine", 5, 2, 3},
            {"EQP002", "X-Ray Machine", 3, 1, 2},
            {"EQP003", "CT Scanner", 7, 3, 4},
            {"EQP004", "Ultrasound", 2, 0, 2}
        };

        for (Object[] row : sampleData) {
            model.addRow(row);
        }
    }

    private void loadTechnicianWorkHistory() {
        DefaultTableModel model = (DefaultTableModel) tblTechnicianWorkHistory.getModel();
        model.setRowCount(0); // 清空表格

        // 获取所有员工并筛选技术员
        List<Employee> technicians = getTechnicians();
        
        for (Employee technician : technicians) {
            Map<String, Integer> workStats = getTechnicianWorkStats(technician);
            
            Object[] row = {
                technician.getId(),
                technician.getName(),
                workStats.get("assigned"),
                workStats.get("inProgress"),
                workStats.get("completed")
            };
            model.addRow(row);
        }

        // 如果没有技术员，显示示例数据
        if (technicians.isEmpty()) {
            addSampleTechnicianData(model);
        }
    }

    private List<Employee> getTechnicians() {
        List<Employee> technicians = new ArrayList<>();
        List<Employee> allEmployees = organization.getEmployeeDirectory().getEmployeeList();
        
        for (Employee employee : allEmployees) {
            if (employee.getPosition() != null && 
                employee.getPosition().toLowerCase().contains("technician")) {
                technicians.add(employee);
            }
        }

        // 添加当前用户（如果是技术员）
        if (userAccount.getEmployee() != null && 
            !technicians.contains(userAccount.getEmployee())) {
            technicians.add(userAccount.getEmployee());
        }

        return technicians;
    }

    private Map<String, Integer> getTechnicianWorkStats(Employee technician) {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("assigned", 0);
        stats.put("inProgress", 0);
        stats.put("completed", 0);

        List<WorkRequest> allRequests = organization.getWorkQueue();
        
        for (WorkRequest request : allRequests) {
            if (request instanceof RepairRequest) {
                RepairRequest repairRequest = (RepairRequest) request;
                if (repairRequest.getAssignedTechnician() != null && 
                    repairRequest.getAssignedTechnician().equals(technician)) {
                    
                    stats.put("assigned", stats.get("assigned") + 1);
                    
                    String status = repairRequest.getStatus();
                    if ("In Progress".equals(status)) {
                        stats.put("inProgress", stats.get("inProgress") + 1);
                    } else if ("Completed".equals(status)) {
                        stats.put("completed", stats.get("completed") + 1);
                    }
                }
            }
        }

        return stats;
    }

    private void addSampleTechnicianData(DefaultTableModel model) {
        Object[][] sampleData = {
            {"TECH001", "张三", 8, 3, 5},
            {"TECH002", "李四", 6, 2, 4},
            {"TECH003", "王五", 10, 4, 6},
            {userAccount.getId(), userAccount.getName(), 5, 2, 3}
        };

        for (Object[] row : sampleData) {
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDataOverview = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTechnicianWorkHistory = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnSalaryCaculation = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("Status Data Overview:");

        tblDataOverview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Equipment ID", "Equipment", "Total Requests Amount", "In Progress", "Completed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDataOverview);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Equipment Status Chart");

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel18.setText("Technician Work History:");

        tblTechnicianWorkHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Technician ID", "Technician", "Assigned Requests Amount", "In Progress", "Completed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblTechnicianWorkHistory);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(353, 353, 353))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel18)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalaryCaculation, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(694, 694, 694))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalaryCaculation)
                .addGap(49, 49, 49)
                .addComponent(btnBack)
                .addContainerGap(111, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnSalaryCaculationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalaryCaculationActionPerformed
        // 计算技术员薪资
        try {
            if (userAccount.getEmployee() != null) {
                double salary = calculateTechnicianSalary();
                
                String salaryInfo = String.format(
                    "技术员薪资计算\n\n" +
                    "员工: %s\n" +
                    "职位: %s\n" +
                    "部门: %s\n" +
                    "计算薪资: $%.2f\n\n" +
                    "计算基于:\n" +
                    "- 基础薪资\n" +
                    "- 完成的维修任务奖金\n" +
                    "- 绩效评估",
                    userAccount.getName(),
                    userAccount.getEmployee().getPosition(),
                    userAccount.getEmployee().getDepartment(),
                    salary
                );

                JOptionPane.showMessageDialog(this, 
                    salaryInfo, 
                    "薪资计算", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "无法获取员工信息", 
                    "错误", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "计算薪资时出错: " + e.getMessage(), 
                "错误", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalaryCaculationActionPerformed
    private double calculateTechnicianSalary() {
        double baseSalary = 5000.0; // 基础薪资
        double bonusPerRepair = 100.0; // 每个维修任务的奖金
        
        // 统计当前技术员完成的维修任务数量
        Map<String, Integer> workStats = getTechnicianWorkStats(userAccount.getEmployee());
        int completedRepairs = workStats.get("completed");
        
        // 计算总薪资
        double totalSalary = baseSalary + (completedRepairs * bonusPerRepair);
        
        // 根据技能等级调整（如果员工有技能等级属性）
        if (userAccount.getEmployee().getPayrollRecord() != null) {
            totalSalary = userAccount.getEmployee().getPayrollRecord().calculateNetSalary();
        }
        
        return totalSalary;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSalaryCaculation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDataOverview;
    private javax.swing.JTable tblTechnicianWorkHistory;
    // End of variables declaration//GEN-END:variables
}
