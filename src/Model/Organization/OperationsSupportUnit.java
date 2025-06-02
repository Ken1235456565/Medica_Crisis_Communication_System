package Model.Organization;

import Model.Employee.Employee;
import Model.Employee.PayrollRecord;
import Model.Organization.Organization;
import Model.Personnel.Admin;
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




public class OperationsSupportUnit extends Organization {
    private String unitName;
    private String fiscalYear;
    private String operationalYear; // ⬅️ 来自第一个版本，已并入
    private List<PayrollRecord> payrolls;
    private List<Employee> staffList;
    private List<Report> performanceReports;
    private List<WorkRequest> analysisTasks;
    private Date lastPayrollDate;
    

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

    // Constructor with full details
    public OperationsSupportUnit(String unitName, Admin admin, String fiscalYear) {
        super(unitName, admin);
        this.unitName = unitName;
        this.fiscalYear = fiscalYear;
        this.payrolls = new ArrayList<>();
        this.staffList = new ArrayList<>();
        this.performanceReports = new ArrayList<>();
        this.analysisTasks = new ArrayList<>();
    }

    // Optional: constructor with operationalYear too
    public OperationsSupportUnit(String unitName, Admin admin, String fiscalYear, String operationalYear) {
        super(unitName, admin);
        this.unitName = unitName;
        this.fiscalYear = fiscalYear;
        this.operationalYear = operationalYear;
        this.payrolls = new ArrayList<>();
        this.staffList = new ArrayList<>();
        this.performanceReports = new ArrayList<>();
        this.analysisTasks = new ArrayList<>();
    }
    
    public OperationsSupportUnit(String unitName, List<PayrollRecord> payrolls, String fiscalYear) {
    super(unitName); // 调用父类 Organization 的构造器
    this.unitName = unitName;
    this.payrolls = payrolls != null ? payrolls : new ArrayList<>();
    this.staffList = new ArrayList<>(); // 默认空列表，避免 NPE
    this.performanceReports = new ArrayList<>();
    this.analysisTasks = new ArrayList<>();
    this.lastPayrollDate = null;
    this.fiscalYear = fiscalYear;
}


    // ==================== Getters / Setters ====================

    public String getOrganizationName() {
        return unitName;
    }

    public void setOrganizationName(String unitName) {
        this.unitName = unitName;
        super.setOrganizationName(unitName);
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getOperationalYear() {
        return operationalYear;
    }

    public void setOperationalYear(String operationalYear) {
        this.operationalYear = operationalYear;
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
    
    public void setStaffList(List<Employee> staffList) {
    this.staffList = staffList;
}

    // ==================== Core Functional Methods ====================

    public void addStaff(Employee employee) {
        staffList.add(employee);
        addEmployee(employee);
    }

    public void removeStaff(Employee employee) {
        staffList.remove(employee);
        removeEmployee(employee);
    }

    public PayrollRequest createPayrollRequest(Employee employee, Date payStart, Date payEnd, Date payDate) {
        PayrollRequest payrollRequest = new PayrollRequest(employee, payStart, payEnd, payDate);
        payrollRequest.setSender(admin.getUserAccount());
        analysisTasks.add(payrollRequest);
        addWorkRequest(payrollRequest);
        return payrollRequest;
    }

    public List<PayrollRequest> processPayrollForAll(Date payStart, Date payEnd, Date payDate) {
        List<PayrollRequest> requests = new ArrayList<>();
        for (Employee emp : staffList) {
            PayrollRequest req = createPayrollRequest(emp, payStart, payEnd, payDate);
            requests.add(req);
        }
        this.lastPayrollDate = payDate;
        return requests;
    }

    public List<PayrollRequest> findPayrollRequestsByEmployee(Employee employee) {
        List<PayrollRequest> result = new ArrayList<>();
        for (WorkRequest wr : analysisTasks) {
            if (wr instanceof PayrollRequest && ((PayrollRequest) wr).getEmployee() == employee) {
                result.add((PayrollRequest) wr);
            }
        }
        return result;
    }

    public void addPerformanceReport(Report report) {
        performanceReports.add(report);
    }

    public Report generatePerformanceReport(String title, String type, Date start, Date end, Map<String, Object> metrics) {
        Report report = new Report(title, type, new Date(), start, end);
        if (metrics != null) {
            for (Map.Entry<String, Object> entry : metrics.entrySet()) {
                report.addData(entry.getKey(), entry.getValue());
            }
        }
        performanceReports.add(report);
        return report;
    }

    public List<Report> findReportsByType(String type) {
        List<Report> result = new ArrayList<>();
        for (Report report : performanceReports) {
            if (report.getType().equals(type)) {
                result.add(report);
            }
        }
        return result;
    }

    public void addAnalysisTask(WorkRequest task) {
        analysisTasks.add(task);
        addWorkRequest(task);
    }

    public ResourceAnalysisRequest createResourceAnalysisRequest(String type, Date start, Date end, String dept, Employee requestedBy) {
        ResourceAnalysisRequest req = new ResourceAnalysisRequest(type, start, end, dept);
        req.setSender(requestedBy.getUserAccount());
        addAnalysisTask(req);
        return req;
    }

    public CostReport createCostReportRequest(String title, Date start, Date end, String dept, String type, Employee requestedBy) {
        CostReport req = new CostReport(title, start, end, dept, type);
        req.setSender(requestedBy.getUserAccount());
        addAnalysisTask(req);
        return req;
    }

    public List<WorkRequest> getActiveAnalysisTasks() {
        List<WorkRequest> result = new ArrayList<>();
        for (WorkRequest task : analysisTasks) {
            String status = task.getStatus();
            if ("Requested".equals(status) || "Assigned".equals(status) || "In Progress".equals(status)) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return unitName + " [Staff: " + staffList.size() +
                ", Payrolls: " + payrolls.size() +
                ", Reports: " + performanceReports.size() +
                ", Fiscal Year: " + fiscalYear +
                ", Operational Year: " + operationalYear + "]";
    }
}
