/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.PayrollOfficer;

import Model.Organization.Organization;
import Model.Organization.OperationsSupportUnit;
import Model.User.UserAccount;
import Model.Employee.Employee;
import Model.Employee.EmployeeDirectory;
import Model.Employee.PayrollRecord;
import Model.WorkQueue.PayrollRequest;
import Model.WorkQueue.Report;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

/**
 *
 * @author tiankaining
 */
public class PayrollOfficerWorkAreaPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private UserAccount userAccount;
    private OperationsSupportUnit operationsUnit;
    private EmployeeDirectory employeeDirectory;

    private CardLayout cardLayout;
    private JPanel contentPanel;

    // 薪资统计数据
    private Map<String, Double> monthlyPayrollCosts;
    private Map<String, Integer> payrollRequestCounts;

    public PayrollOfficerWorkAreaPanel(JPanel userProcessContainer, Organization organization, UserAccount userAccount) {
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.userAccount = userAccount;
        this.operationsUnit = findOperationsSupportUnit();
        this.employeeDirectory = organization.getEmployeeDirectory();
        this.monthlyPayrollCosts = new HashMap<>();
        this.payrollRequestCounts = new HashMap<>();

        initComponents();
        initContentPanel();
        initializePayrollData();
        populateTimeSpanComboBox();
    }

    private OperationsSupportUnit findOperationsSupportUnit() {
        for (Organization org : organization.getOrganizations().getOrganizationList()) {
            if (org instanceof OperationsSupportUnit) {
                return (OperationsSupportUnit) org;
            }
        }
        return null;
    }

    private void initContentPanel() {
        contentPanel = new JPanel(new CardLayout());
        this.cardLayout = (CardLayout) contentPanel.getLayout();

        // 添加子面板到contentPanel
        contentPanel.add("GeneratePayrolls", new GeneratePayrolls(userProcessContainer, organization, userAccount, employeeDirectory));
        contentPanel.add("ManageEmployeeSalary", new ManageEmployeeSalary(userProcessContainer, organization, userAccount, employeeDirectory));
    }

    private void initializePayrollData() {
        // 初始化历史薪资数据
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        
        // 生成过去12个月的薪资成本数据
        for (int i = 0; i < 12; i++) {
            String monthKey = monthFormat.format(cal.getTime());
            double totalCost = calculateMonthlyPayrollCost(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
            monthlyPayrollCosts.put(monthKey, totalCost);
            
            // 模拟薪资请求数量
            int requestCount = (int) (Math.random() * 50) + 20; // 20-70个请求
            payrollRequestCounts.put(monthKey, requestCount);
            
            cal.add(Calendar.MONTH, -1);
        }
    }

    private void populateTimeSpanComboBox() {
        cmbTimeSpan.removeAllItems();
        cmbTimeSpan.addItem("-- Select Time Span --");
        cmbTimeSpan.addItem("Current Month");
        cmbTimeSpan.addItem("Last 3 Months");
        cmbTimeSpan.addItem("Last 6 Months");
        cmbTimeSpan.addItem("Last 12 Months");
        cmbTimeSpan.addItem("Current Year");
        cmbTimeSpan.addItem("Last Year");
    }

    private double calculateMonthlyPayrollCost(int year, int month) {
        double totalCost = 0.0;
        
        for (Employee emp : employeeDirectory.getEmployeeList()) {
            // 计算员工的基础成本
            double baseSalary = getBaseSalaryByRole(emp.getPosition());
            double benefits = baseSalary * 0.25; // 25%的福利和税费
            double totalEmpCost = baseSalary + benefits;
            
            // 添加一些月度变动
            double monthVariation = (Math.random() - 0.5) * 0.1; // ±5%的变动
            totalEmpCost *= (1 + monthVariation);
            
            totalCost += totalEmpCost;
        }
        
        return totalCost;
    }

    private double getBaseSalaryByRole(String role) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGeneratePayrolls = new javax.swing.JButton();
        btnTotalPayrollChart = new javax.swing.JButton();
        btnCaculateMonthCost = new javax.swing.JButton();
        btnManageEmployeeSalary = new javax.swing.JButton();
        cmbTimeSpan = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Payroll Officer WorkArea");

        btnGeneratePayrolls.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnGeneratePayrolls.setText("Generate Payrolls");
        btnGeneratePayrolls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneratePayrollsActionPerformed(evt);
            }
        });

        btnTotalPayrollChart.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnTotalPayrollChart.setText("Total Payroll Chart");
        btnTotalPayrollChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalPayrollChartActionPerformed(evt);
            }
        });

        btnCaculateMonthCost.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnCaculateMonthCost.setText("Caculate Month Cost");
        btnCaculateMonthCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaculateMonthCostActionPerformed(evt);
            }
        });

        btnManageEmployeeSalary.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnManageEmployeeSalary.setText("Manage Employee Salary");
        btnManageEmployeeSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageEmployeeSalaryActionPerformed(evt);
            }
        });

        cmbTimeSpan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "3 days", "7 days", "30 days" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnManageEmployeeSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(btnTotalPayrollChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGeneratePayrolls, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCaculateMonthCost, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                    .addComponent(cmbTimeSpan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(jLabel1)))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel1)
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGeneratePayrolls, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbTimeSpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCaculateMonthCost, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTotalPayrollChart, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageEmployeeSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(226, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGeneratePayrollsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneratePayrollsActionPerformed
        // Navigate to GeneratePayrolls
        userProcessContainer.add("GeneratePayrolls", contentPanel.getComponent(0));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnGeneratePayrollsActionPerformed

    private void btnTotalPayrollChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalPayrollChartActionPerformed
        try {
            // 生成薪资图表报告
            generatePayrollChart();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error generating payroll chart: " + e.getMessage(),
                "Chart Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTotalPayrollChartActionPerformed

    private void btnCaculateMonthCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaculateMonthCostActionPerformed
        String selectedTimeSpan = (String) cmbTimeSpan.getSelectedItem();
        if (selectedTimeSpan == null || selectedTimeSpan.startsWith("--")) {
            JOptionPane.showMessageDialog(this, "Please select a time span first.", 
                                        "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 根据选择的时间段计算成本
            calculateCostForTimeSpan(selectedTimeSpan);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error calculating monthly cost: " + e.getMessage(),
                "Calculation Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCaculateMonthCostActionPerformed

    private void btnManageEmployeeSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageEmployeeSalaryActionPerformed
        // Navigate to ManageEmployeeSalary
        userProcessContainer.add("ManageEmployeeSalary", contentPanel.getComponent(1));
        ((CardLayout)userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_btnManageEmployeeSalaryActionPerformed

    private void generatePayrollChart() {
        StringBuilder chartData = new StringBuilder();
        chartData.append("=== PAYROLL COST ANALYSIS CHART ===\n\n");
        
        // 按月份显示薪资成本
        chartData.append("Monthly Payroll Costs (Last 12 Months):\n");
        chartData.append("----------------------------------------\n");
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM yyyy");
        SimpleDateFormat keyFormat = new SimpleDateFormat("yyyy-MM");
        
        double totalYearCost = 0.0;
        double maxMonthlyCost = 0.0;
        double minMonthlyCost = Double.MAX_VALUE;
        
        for (int i = 0; i < 12; i++) {
            String monthKey = keyFormat.format(cal.getTime());
            String monthDisplay = monthFormat.format(cal.getTime());
            
            Double monthlyCost = monthlyPayrollCosts.get(monthKey);
            if (monthlyCost == null) monthlyCost = 0.0;
            
            // 创建简单的条形图
            int barLength = (int) (monthlyCost / 10000); // 每10k一个字符
            String bar = "█".repeat(Math.max(1, Math.min(50, barLength)));
            
            chartData.append(String.format("%-12s: $%,10.2f %s\n", 
                           monthDisplay, monthlyCost, bar));
            
            totalYearCost += monthlyCost;
            maxMonthlyCost = Math.max(maxMonthlyCost, monthlyCost);
            minMonthlyCost = Math.min(minMonthlyCost, monthlyCost);
            
            cal.add(Calendar.MONTH, -1);
        }
        
        // 统计信息
        chartData.append("\n=== SUMMARY STATISTICS ===\n");
        chartData.append(String.format("Total Annual Cost: $%,.2f\n", totalYearCost));
        chartData.append(String.format("Average Monthly Cost: $%,.2f\n", totalYearCost / 12));
        chartData.append(String.format("Highest Monthly Cost: $%,.2f\n", maxMonthlyCost));
        chartData.append(String.format("Lowest Monthly Cost: $%,.2f\n", minMonthlyCost));
        
        // 按部门分析
        chartData.append("\n=== COST BY DEPARTMENT ===\n");
        Map<String, Double> deptCosts = calculateCostByDepartment();
        for (Map.Entry<String, Double> entry : deptCosts.entrySet()) {
            double percentage = (entry.getValue() / totalYearCost) * 100;
            chartData.append(String.format("%-20s: $%,10.2f (%.1f%%)\n", 
                           entry.getKey(), entry.getValue(), percentage));
        }
        
        // 按角色分析
        chartData.append("\n=== COST BY ROLE ===\n");
        Map<String, Double> roleCosts = calculateCostByRole();
        for (Map.Entry<String, Double> entry : roleCosts.entrySet()) {
            double percentage = (entry.getValue() / totalYearCost) * 100;
            chartData.append(String.format("%-20s: $%,10.2f (%.1f%%)\n", 
                           entry.getKey(), entry.getValue(), percentage));
        }
        
        // 显示图表
        JOptionPane.showMessageDialog(this, chartData.toString(), 
                                    "Payroll Cost Chart", JOptionPane.INFORMATION_MESSAGE);
        
        // 如果有Operations Support Unit，创建报告记录
        if (operationsUnit != null) {
            Report report = operationsUnit.generatePerformanceReport(
                "Payroll Cost Analysis", 
                "Financial", 
                new Date(), 
                getStartOfYear(), 
                new Date(),
                createPayrollMetrics(totalYearCost, maxMonthlyCost, minMonthlyCost)
            );
        }
    }

    private void calculateCostForTimeSpan(String timeSpan) {
        Calendar cal = Calendar.getInstance();
        Calendar startCal = Calendar.getInstance();
        SimpleDateFormat keyFormat = new SimpleDateFormat("yyyy-MM");
        
        // 根据时间段设置开始日期
        switch (timeSpan) {
            case "Current Month":
                startCal.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case "Last 3 Months":
                startCal.add(Calendar.MONTH, -3);
                break;
            case "Last 6 Months":
                startCal.add(Calendar.MONTH, -6);
                break;
            case "Last 12 Months":
                startCal.add(Calendar.MONTH, -12);
                break;
            case "Current Year":
                startCal.set(Calendar.MONTH, Calendar.JANUARY);
                startCal.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case "Last Year":
                startCal.add(Calendar.YEAR, -1);
                startCal.set(Calendar.MONTH, Calendar.JANUARY);
                startCal.set(Calendar.DAY_OF_MONTH, 1);
                cal.set(Calendar.MONTH, Calendar.DECEMBER);
                cal.set(Calendar.DAY_OF_MONTH, 31);
                cal.add(Calendar.YEAR, -1);
                break;
            default:
                startCal.add(Calendar.MONTH, -1);
                break;
        }
        
        // 计算时间段内的总成本
        double totalCost = 0.0;
        int monthCount = 0;
        List<String> monthlyBreakdown = new ArrayList<>();
        
        Calendar tempCal = (Calendar) startCal.clone();
        while (tempCal.before(cal) || tempCal.equals(cal)) {
            String monthKey = keyFormat.format(tempCal.getTime());
            Double monthlyCost = monthlyPayrollCosts.get(monthKey);
            if (monthlyCost == null) {
                monthlyCost = calculateMonthlyPayrollCost(
                    tempCal.get(Calendar.YEAR), 
                    tempCal.get(Calendar.MONTH) + 1
                );
            }
            
            totalCost += monthlyCost;
            monthCount++;
            
            SimpleDateFormat displayFormat = new SimpleDateFormat("MMM yyyy");
            monthlyBreakdown.add(String.format("%-12s: $%,.2f", 
                               displayFormat.format(tempCal.getTime()), monthlyCost));
            
            tempCal.add(Calendar.MONTH, 1);
        }
        
        // 构建结果报告
        StringBuilder result = new StringBuilder();
        result.append("=== PAYROLL COST CALCULATION ===\n\n");
        result.append("Time Span: ").append(timeSpan).append("\n");
        result.append("Period: ").append(new SimpleDateFormat("MMM dd, yyyy").format(startCal.getTime()));
        result.append(" to ").append(new SimpleDateFormat("MMM dd, yyyy").format(cal.getTime())).append("\n\n");
        
        result.append("Monthly Breakdown:\n");
        result.append("------------------\n");
        for (String breakdown : monthlyBreakdown) {
            result.append(breakdown).append("\n");
        }
        
        result.append("\n=== SUMMARY ===\n");
        result.append(String.format("Total Cost: $%,.2f\n", totalCost));
        result.append(String.format("Number of Months: %d\n", monthCount));
        result.append(String.format("Average Monthly Cost: $%,.2f\n", 
                                   monthCount > 0 ? totalCost / monthCount : 0));
        
        // 员工统计
        int totalEmployees = employeeDirectory.getEmployeeList().size();
        result.append(String.format("Total Employees: %d\n", totalEmployees));
        result.append(String.format("Average Cost per Employee: $%,.2f\n", 
                                   totalEmployees > 0 ? totalCost / totalEmployees : 0));
        
        // 预算分析
        double budgetVariance = calculateBudgetVariance(totalCost, monthCount);
        result.append(String.format("Budget Variance: %.1f%%\n", budgetVariance));
        
        if (budgetVariance > 10) {
            result.append("⚠️  WARNING: Costs exceed budget by more than 10%\n");
        } else if (budgetVariance < -10) {
            result.append("✅ GOOD: Costs are significantly under budget\n");
        } else {
            result.append("✓ OK: Costs are within acceptable budget range\n");
        }
        
        // 显示结果
        JOptionPane.showMessageDialog(this, result.toString(), 
                                    "Cost Calculation Results", JOptionPane.INFORMATION_MESSAGE);
        
        // 如果有Operations Support Unit，创建成本分析请求
        if (operationsUnit != null) {
            Date startDate = startCal.getTime();
            Date endDate = cal.getTime();
            
            operationsUnit.createCostReportRequest(
                "Payroll Cost Analysis - " + timeSpan,
                startDate, endDate,
                "All Departments",
                "Payroll Analysis",
                userAccount.getEmployee()
            );
        }
    }

    private Map<String, Double> calculateCostByDepartment() {
        Map<String, Double> deptCosts = new HashMap<>();
        
        for (Employee emp : employeeDirectory.getEmployeeList()) {
            String dept = emp.getDepartment() != null ? emp.getDepartment() : "Unknown";
            double empCost = getBaseSalaryByRole(emp.getPosition()) * 1.25; // 包含福利
            deptCosts.put(dept, deptCosts.getOrDefault(dept, 0.0) + empCost);
        }
        
        return deptCosts;
    }

    private Map<String, Double> calculateCostByRole() {
        Map<String, Double> roleCosts = new HashMap<>();
        
        for (Employee emp : employeeDirectory.getEmployeeList()) {
            String role = emp.getPosition() != null ? emp.getPosition() : "Unknown";
            double empCost = getBaseSalaryByRole(role) * 1.25; // 包含福利
            roleCosts.put(role, roleCosts.getOrDefault(role, 0.0) + empCost);
        }
        
        return roleCosts;
    }

    private double calculateBudgetVariance(double actualCost, int monthCount) {
        // 假设每个员工的预算成本是75000/年
        double budgetedAnnualCost = employeeDirectory.getEmployeeList().size() * 75000.0;
        double budgetedCost = (budgetedAnnualCost / 12.0) * monthCount;
        
        return ((actualCost - budgetedCost) / budgetedCost) * 100;
    }

    private Date getStartOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Map<String, Object> createPayrollMetrics(double totalYearCost, double maxMonthlyCost, double minMonthlyCost) {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalAnnualCost", totalYearCost);
        metrics.put("averageMonthlyCost", totalYearCost / 12);
        metrics.put("maxMonthlyCost", maxMonthlyCost);
        metrics.put("minMonthlyCost", minMonthlyCost);
        metrics.put("totalEmployees", employeeDirectory.getEmployeeList().size());
        metrics.put("averageCostPerEmployee", totalYearCost / employeeDirectory.getEmployeeList().size());
        return metrics;
    }

    // 薪资趋势分析
    public void analyzePayrollTrends() {
        StringBuilder analysis = new StringBuilder();
        analysis.append("=== PAYROLL TREND ANALYSIS ===\n\n");
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        
        List<Double> last6Months = new ArrayList<>();
        List<String> monthLabels = new ArrayList<>();
        
        for (int i = 0; i < 6; i++) {
            String monthKey = monthFormat.format(cal.getTime());
            Double cost = monthlyPayrollCosts.get(monthKey);
            if (cost != null) {
                last6Months.add(cost);
                monthLabels.add(new SimpleDateFormat("MMM").format(cal.getTime()));
            }
            cal.add(Calendar.MONTH, -1);
        }
        
        if (last6Months.size() >= 3) {
            // 计算趋势
            double trend = calculateTrend(last6Months);
            
            analysis.append("6-Month Trend Analysis:\n");
            analysis.append("----------------------\n");
            
            for (int i = 0; i < last6Months.size(); i++) {
                analysis.append(String.format("%-4s: $%,.2f\n", 
                               monthLabels.get(i), last6Months.get(i)));
            }
            
            analysis.append(String.format("\nTrend: %.2f%% %s\n", 
                           Math.abs(trend), trend > 0 ? "INCREASING" : "DECREASING"));
            
            if (Math.abs(trend) > 5) {
                analysis.append("⚠️  Significant trend detected - review required\n");
            } else {
                analysis.append("✓ Trend within normal range\n");
            }
        }
        
        JOptionPane.showMessageDialog(this, analysis.toString(), 
                                    "Payroll Trend Analysis", JOptionPane.INFORMATION_MESSAGE);
    }

    private double calculateTrend(List<Double> values) {
        if (values.size() < 2) return 0.0;
        
        double firstValue = values.get(values.size() - 1); // 最早的值
        double lastValue = values.get(0); // 最新的值
        
        return ((lastValue - firstValue) / firstValue) * 100;
    }

    // 薪资预算预测
    public void generatePayrollForecast() {
        StringBuilder forecast = new StringBuilder();
        forecast.append("=== PAYROLL FORECAST ===\n\n");
        
        // 基于历史数据预测未来6个月
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM yyyy");
        
        // 计算平均月增长率
        double avgGrowthRate = calculateAverageGrowthRate();
        double currentCost = getCurrentMonthCost();
        
        forecast.append("Forecast Based on Historical Trends:\n");
        forecast.append("-----------------------------------\n");
        forecast.append(String.format("Current Monthly Cost: $%,.2f\n", currentCost));
        forecast.append(String.format("Average Growth Rate: %.2f%%\n\n", avgGrowthRate));
        
        double totalForecastCost = 0.0;
        
        for (int i = 1; i <= 6; i++) {
            cal.add(Calendar.MONTH, 1);
            currentCost *= (1 + avgGrowthRate / 100);
            totalForecastCost += currentCost;
            
            forecast.append(String.format("%-12s: $%,.2f\n", 
                           monthFormat.format(cal.getTime()), currentCost));
        }
        
        forecast.append(String.format("\nTotal 6-Month Forecast: $%,.2f\n", totalForecastCost));
        forecast.append(String.format("Average Monthly Forecast: $%,.2f\n", totalForecastCost / 6));
        
        // 预算建议
        forecast.append("\n=== BUDGET RECOMMENDATIONS ===\n");
        if (avgGrowthRate > 3) {
            forecast.append("• Consider budget increase of 10-15% for next quarter\n");
            forecast.append("• Review salary adjustment policies\n");
            forecast.append("• Evaluate headcount planning\n");
        } else if (avgGrowthRate < -2) {
            forecast.append("• Budget may be reduced by 5-10%\n");
            forecast.append("• Consider efficiency improvements\n");
        } else {
            forecast.append("• Current budget allocation appears appropriate\n");
            forecast.append("• Monitor for seasonal variations\n");
        }
        
        JOptionPane.showMessageDialog(this, forecast.toString(), 
                                    "Payroll Forecast", JOptionPane.INFORMATION_MESSAGE);
    }

    private double calculateAverageGrowthRate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat keyFormat = new SimpleDateFormat("yyyy-MM");
        
        List<Double> growthRates = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) { // 计算过去5个月的增长率
            String currentMonth = keyFormat.format(cal.getTime());
            cal.add(Calendar.MONTH, -1);
            String previousMonth = keyFormat.format(cal.getTime());
            
            Double currentCost = monthlyPayrollCosts.get(currentMonth);
            Double previousCost = monthlyPayrollCosts.get(previousMonth);
            
            if (currentCost != null && previousCost != null && previousCost > 0) {
                double growthRate = ((currentCost - previousCost) / previousCost) * 100;
                growthRates.add(growthRate);
            }
        }
        
        return growthRates.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private double getCurrentMonthCost() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat keyFormat = new SimpleDateFormat("yyyy-MM");
        String currentMonth = keyFormat.format(cal.getTime());
        
        Double cost = monthlyPayrollCosts.get(currentMonth);
        if (cost != null) {
            return cost;
        }
        
        // 如果没有当月数据，计算实时成本
        return calculateMonthlyPayrollCost(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
    }

    // 生成薪资合规报告
    public void generateComplianceReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== PAYROLL COMPLIANCE REPORT ===\n\n");
        
        // 检查薪资合规性
        int totalEmployees = employeeDirectory.getEmployeeList().size();
        int compliantEmployees = 0;
        int nonCompliantEmployees = 0;
        List<String> issues = new ArrayList<>();
        
        double minimumWage = 15.0; // 假设最低工资标准
        
        for (Employee emp : employeeDirectory.getEmployeeList()) {
            double hourlySalary = getBaseSalaryByRole(emp.getPosition()) / (52 * 40); // 年薪转小时工资
            
            if (hourlySalary >= minimumWage) {
                compliantEmployees++;
            } else {
                nonCompliantEmployees++;
                issues.add(String.format("Employee %s (%s): $%.2f/hour (below minimum $%.2f)", 
                          emp.getName(), emp.getId(), hourlySalary, minimumWage));
            }
        }
        
        report.append(String.format("Total Employees Reviewed: %d\n", totalEmployees));
        report.append(String.format("Compliant: %d (%.1f%%)\n", 
                     compliantEmployees, (compliantEmployees * 100.0) / totalEmployees));
        report.append(String.format("Non-Compliant: %d (%.1f%%)\n", 
                     nonCompliantEmployees, (nonCompliantEmployees * 100.0) / totalEmployees));
        
        if (!issues.isEmpty()) {
            report.append("\n=== COMPLIANCE ISSUES ===\n");
            for (String issue : issues) {
                report.append("⚠️  ").append(issue).append("\n");
            }
            
            report.append("\n=== RECOMMENDED ACTIONS ===\n");
            report.append("• Review and adjust salaries for non-compliant employees\n");
            report.append("• Update payroll policies to ensure compliance\n");
            report.append("• Schedule follow-up compliance review in 30 days\n");
        } else {
            report.append("\n✅ All employees meet minimum wage requirements\n");
        }
        
        JOptionPane.showMessageDialog(this, report.toString(), 
                                    "Compliance Report", JOptionPane.INFORMATION_MESSAGE);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCaculateMonthCost;
    private javax.swing.JButton btnGeneratePayrolls;
    private javax.swing.JButton btnManageEmployeeSalary;
    private javax.swing.JButton btnTotalPayrollChart;
    private javax.swing.JComboBox<String> cmbTimeSpan;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
