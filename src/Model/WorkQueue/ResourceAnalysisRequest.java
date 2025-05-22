package Model.WorkQueue;

import java.util.Date;
import java.util.Map;

/**
 * Represents a request to analyze resource usage.
 * This would be used by the OperationsSupportUnit.
 */
public class ResourceAnalysisRequest extends WorkRequest { // Extends WorkRequest

    private String analysisType;      // e.g., "Staffing", "Supply", "Financial"
    private Date analysisPeriodStart;
    private Date analysisPeriodEnd;
    private String department;
    private Map<String, Object> analysisCriteria;
    private String priority;         // "High", "Medium", "Low"
    private String analystAssigned;

    public ResourceAnalysisRequest() {
        this.setStatus("Requested");
        this.setMessage("Resource Analysis Request");
    }

    public ResourceAnalysisRequest(String analysisType, Date analysisPeriodStart, Date analysisPeriodEnd, String department) {
        this();
        this.analysisType = analysisType;
        this.analysisPeriodStart = analysisPeriodStart;
        this.analysisPeriodEnd = analysisPeriodEnd;
        this.department = department;
        this.setMessage("Analyze " + analysisType + " for " + department);
    }

    // Getters and setters

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public Date getAnalysisPeriodStart() {
        return analysisPeriodStart;
    }

    public void setAnalysisPeriodStart(Date analysisPeriodStart) {
        this.analysisPeriodStart = analysisPeriodStart;
    }

    public Date getAnalysisPeriodEnd() {
        return analysisPeriodEnd;
    }

    public void setAnalysisPeriodEnd(Date analysisPeriodEnd) {
        this.analysisPeriodEnd = analysisPeriodEnd;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Map<String, Object> getAnalysisCriteria() {
        return analysisCriteria;
    }

    public void setAnalysisCriteria(Map<String, Object> analysisCriteria) {
        this.analysisCriteria = analysisCriteria;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAnalystAssigned() {
        return analystAssigned;
    }

    public void setAnalystAssigned(String analystAssigned) {
        this.analystAssigned = analystAssigned;
    }

    // Methods to manage the analysis

    public void assignAnalyst(String analyst) {
        this.analystAssigned = analyst;
        this.setStatus("Assigned");
    }

    public void startAnalysis() {
        this.setStatus("In Progress");
    }

    public void completeAnalysis() {
        this.setStatus("Completed");
        this.setResolveDate(new Date());
    }

    @Override
    public String toString() {
        return getMessage() + " (" + priority + ") - " + getStatus();
    }
}