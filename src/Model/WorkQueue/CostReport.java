package Model.WorkQueue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a cost report work request.
 * This might be used by the OperationsSupportUnit.
 */
public class CostReport extends WorkRequest { // Extends WorkRequest

    private String reportTitle;
    private Date startDate;
    private Date endDate;
    private Map<String, Double> costData; // Cost breakdown
    private String department;
    private String reportType;   // e.g., "Monthly", "Annual", "Project"

    public CostReport() {
        this.setStatus("Pending"); // Initial status
        this.setMessage("Cost Report Request"); // Default message
    }

    public CostReport(String reportTitle, Date startDate, Date endDate, String department, String reportType) {
        this();
        this.reportTitle = reportTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.department = department;
        this.reportType = reportType;
    }

    // Getters and setters

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Map<String, Double> getCostData() {
        return costData;
    }

    public void setCostData(Map<String, Double> costData) {
        this.costData = costData;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    // Methods to add cost data

    public void addCost(String category, double amount) {
        if (costData == null) {
            costData = new HashMap<>();
        }
        costData.put(category, amount);
    }

    @Override
    public String toString() {
        return reportTitle + " (" + reportType + ") - " + getStatus(); // Use getStatus()
    }
}