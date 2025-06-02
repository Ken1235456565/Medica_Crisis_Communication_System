// Model/WorkQueue/IssueReport.java
package Model.WorkQueue;

import Model.Supplies.Equipments;
import java.util.Date;


public class IssueReport extends WorkRequest {
    private String reportId;
    private String reporterName;
    private String contactEmail;
    private String issueCategory;
    private String itemName;
    private int severityLevel;
    private String notes;
    private Equipments equipment;
    
    private static int counter = 1;

    public IssueReport() {
        super();
        this.reportId = "ISS" + counter++;
        this.setStatus("Reported");
    }

    public IssueReport(String reporterName, String contactEmail, String issueCategory, 
                      String itemName, int severityLevel, String notes) {
        this();
        this.reporterName = reporterName;
        this.contactEmail = contactEmail;
        this.issueCategory = issueCategory;
        this.itemName = itemName;
        this.severityLevel = severityLevel;
        this.notes = notes;
        this.setMessage("Issue Report: " + itemName + " - " + issueCategory);
    }

    // Getters and setters
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }
    
    public String getReporterName() { return reporterName; }
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }
    
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    
    public String getIssueCategory() { return issueCategory; }
    public void setIssueCategory(String issueCategory) { this.issueCategory = issueCategory; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public int getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(int severityLevel) { this.severityLevel = severityLevel; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public Equipments getEquipment() { return equipment; }
    public void setEquipment(Equipments equipment) { this.equipment = equipment; }

    @Override
    public String toString() {
        return reportId + ": " + reporterName + " - " + itemName + " (" + getStatus() + ")";
    }
}