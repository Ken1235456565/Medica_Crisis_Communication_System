package Model.Organization;

import Model.Personnel.Employee;
import Model.Person.PayrollRecord;
import Model.WorkQueue.PayrollRequest;
import Model.WorkQueue.ResourceAnalysisRequest;
import Model.WorkQueue.CostReport;
import Model.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.WorkQueue.Report;

/**
 * Operations Support Unit for financial and analytical functions
 * @author tiankaining
 */
public class OperationsSupportUnit extends Organization { // EXTENDS Organization
    private String unitName;
    private List<PayrollRecord> payrolls; // 薪资记录
    private List<Employee> staffList; // 关联员工
    private List<Report> performanceReports;
    private List<WorkRequest> analysisTasks;
    private Date lastPayrollDate;
    private String fiscalYear;
    
    // Default constructor
    public OperationsSupportUnit() {
        super("Operations Support");
        this.unitName = "Operations Support";
        this.payrolls = new ArrayList<>();
        this.staffList = new ArrayList<>();
        this.performanceReports = new ArrayList<>();
        this.analysisTasks = new ArrayList<>();
    }
    
    // Constructor with unit name
    public OperationsSupportUnit(String unitName) {
        super(unitName);
        this.unitName = unitName;
        this.payrolls = new ArrayList<>();
        this.staffList = new ArrayList<>();
        this.performanceReports = new ArrayList<>();
        this.analysisTasks = new ArrayList<>();
    }
    
    // Constructor with detailed info
    public OperationsSupportUnit(String unitName, Employee admin, String fiscalYear) {
        super(unitName, admin);
        this.unitName = unitName;
        this.payrolls = new ArrayList<>();
        this.staffList = new ArrayList<>();
        this.performanceReports = new ArrayList<>();
        this.analysisTasks = new ArrayList<>();
        this.fiscalYear = fiscalYear;
    }
    
    // Getters and setters
    @Override
    public String getOrganizationName() {
        return unitName;
    }
    
    @Override
    public void setOrganizationName(String unitName) {
        this.unitName = unitName;
        super.setOrganizationName(unitName);
    }
    
    public List<PayrollRecord> getPayrolls() {
        return payrolls;
    }
    
    public List<Employee> getStaffList() {
        return staffList;
    }
    
    public List<Report> getPerformanceReports() {
        return performanceReports;
    }
    
    public List<WorkRequest> getAnalysisTasks() {
        return analysisTasks;
    }
    
    public Date getLastPayrollDate() {
        return lastPayrollDate;
    }
    
    public void setLastPayrollDate(Date lastPayrollDate) {
        this.lastPayrollDate = lastPayrollDate;
    }
    
    public String getFiscalYear() {
        return fiscalYear;
    }
    
    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }
    
    // Add staff
    public void addStaff(Employee employee) {
        staffList.add(employee);
        addEmployee(employee); // Also add to general employee directory
    }
    
    // Remove staff
    public void removeStaff(Employee employee) {
        staffList.remove(employee);
        removeEmployee(employee); // Also remove from general employee directory
    }
    
    // Create payroll record (and corresponding PayrollRequest)
    public PayrollRequest createPayrollRequest(Employee employee, Date payPeriodStart, 
                                           Date payPeriodEnd, Date paymentDate) {
        
        PayrollRequest payrollRequest = new PayrollRequest(employee, payPeriodStart, payPeriodEnd, paymentDate);
        payrollRequest.setSender(admin); // Assuming admin sends payroll requests
        
        // Add to payrolls list (if PayrollRecord is part of workqueue, otherwise handle separately)
        // For now, we'll just add the request to analysisTasks and the general work queue.
        analysisTasks.add(payrollRequest);
        addWorkRequest(payrollRequest); // Also add to general work queue
        
        return payrollRequest;
    }

    // Process payroll for all employees (generates requests)
    public List<PayrollRequest> processPayrollForAll(Date payPeriodStart, Date payPeriodEnd, Date paymentDate) {
        List<PayrollRequest> processedRequests = new ArrayList<>();
        
        for (Employee employee : staffList) {
            // Create a payroll request for each employee
            PayrollRequest request = createPayrollRequest(employee, payPeriodStart, payPeriodEnd, paymentDate);
            processedRequests.add(request);
        }
        
        lastPayrollDate = paymentDate;
        return processedRequests;
    }
    
    // Find payroll by employee (finding PayrollRequests)
    public List<PayrollRequest> findPayrollRequestsByEmployee(Employee employee) {
        List<PayrollRequest> result = new ArrayList<>();
        for (WorkRequest request : analysisTasks) {
            if (request instanceof PayrollRequest) {
                PayrollRequest pr = (PayrollRequest) request;
                if (pr.getEmployee() == employee) {
                    result.add(pr);
                }
            }
        }
        return result;
    }
    
    // Add performance report
    public void addPerformanceReport(Report report) {
        performanceReports.add(report);
    }
    
    // Generate performance report (this is a local Report, not a WorkRequest)
    public Report generatePerformanceReport(String title, String type, 
                                           Date startDate, Date endDate, 
                                           Map<String, Object> metrics) {
        Report report = new Report(title, type, new Date(), startDate, endDate);
        
        // Add metrics to the report
        if (metrics != null) {
            for (Map.Entry<String, Object> entry : metrics.entrySet()) {
                report.addData(entry.getKey(), entry.getValue());
            }
        }
        
        performanceReports.add(report);
        return report;
    }
    
    // Find reports by type
    public List<Report> findReportsByType(String type) {
        List<Report> result = new ArrayList<>();
        for (Report report : performanceReports) {
            if (report.getType().equals(type)) {
                result.add(report);
            }
        }
        return result;
    }
    
    // Add analysis task
    public void addAnalysisTask(WorkRequest task) {
        analysisTasks.add(task);
        addWorkRequest(task); // Also add to general work queue
    }
    
    // Create Resource Analysis Request
    public ResourceAnalysisRequest createResourceAnalysisRequest(String analysisType, Date periodStart, Date periodEnd, String department, Employee requestedBy) {
        ResourceAnalysisRequest request = new ResourceAnalysisRequest(analysisType, periodStart, periodEnd, department);
        request.setSender(requestedBy);
        addAnalysisTask(request);
        return request;
    }

    // Create Cost Report Request
    public CostReport createCostReportRequest(String reportTitle, Date startDate, Date endDate, String department, String reportType, Employee requestedBy) {
        CostReport request = new CostReport(reportTitle, startDate, endDate, department, reportType);
        request.setSender(requestedBy);
        addAnalysisTask(request);
        return request;
    }

    // Get active analysis tasks
    public List<WorkRequest> getActiveAnalysisTasks() {
        List<WorkRequest> activeTasks = new ArrayList<>();
        for (WorkRequest task : analysisTasks) {
            if (task.getStatus().equals("Requested") || task.getStatus().equals("Assigned") || task.getStatus().equals("In Progress")) {
                activeTasks.add(task);
            }
        }
        return activeTasks;
    }
    
    @Override
    public String toString() {
        return unitName + " [Staff: " + staffList.size() + 
               ", Payrolls: " + payrolls.size() + 
               ", Reports: " + performanceReports.size() + 
               ", Fiscal Year: " + fiscalYear + "]";
    }
    
    
}