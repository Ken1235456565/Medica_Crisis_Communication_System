// Model/WorkQueue/RepairRequest.java
package Model.WorkQueue;

import Model.Employee.Employee;
import Model.Supplies.Equipments;
import java.util.Date;


public class RepairRequest extends WorkRequest {
    private String repairRequestId;
    private Equipments equipment;
    private String reporterName;
    private String issueCategory;
    private int priorityLevel;
    private int severityLevel;
    private Employee assignedTechnician;
    private String contactEmail;
    
    private static int counter = 1;

    public RepairRequest() {
        super();
        this.repairRequestId = "RPR" + counter++;
        this.setStatus("Pending");
    }

    public RepairRequest(Equipments equipment, String reporterName, String issueCategory, 
                        int priorityLevel, int severityLevel, String contactEmail) {
        this();
        this.equipment = equipment;
        this.reporterName = reporterName;
        this.issueCategory = issueCategory;
        this.priorityLevel = priorityLevel;
        this.severityLevel = severityLevel;
        this.contactEmail = contactEmail;
        this.setMessage("Repair Request for " + (equipment != null ? equipment.getName() : "Unknown Equipment"));
    }

    // Getters and setters
    public String getRepairRequestId() { return repairRequestId; }
    public void setRepairRequestId(String repairRequestId) { this.repairRequestId = repairRequestId; }
    
    public Equipments getEquipment() { return equipment; }
    public void setEquipment(Equipments equipment) { this.equipment = equipment; }
    
    public String getReporterName() { return reporterName; }
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }
    
    public String getIssueCategory() { return issueCategory; }
    public void setIssueCategory(String issueCategory) { this.issueCategory = issueCategory; }
    
    public int getPriorityLevel() { return priorityLevel; }
    public void setPriorityLevel(int priorityLevel) { this.priorityLevel = priorityLevel; }
    
    public int getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(int severityLevel) { this.severityLevel = severityLevel; }
    
    public Employee getAssignedTechnician() { return assignedTechnician; }
    public void setAssignedTechnician(Employee assignedTechnician) { 
        this.assignedTechnician = assignedTechnician;
        if (assignedTechnician != null) {
            this.setReceiver(assignedTechnician.getUserAccount());
        }
    }
    
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public void completeRepair() {
        this.setStatus("Completed");
        this.setResolveDate(new Date());
        if (equipment != null) {
            equipment.setStatus("Available");
        }
    }

    public void startRepair() {
        this.setStatus("In Progress");
        if (equipment != null) {
            equipment.setStatus("Under Maintenance");
        }
    }

    @Override
    public String toString() {
        return repairRequestId + ": " + 
               (equipment != null ? equipment.getName() : "Unknown") + 
               " (" + getStatus() + ")";
    }
}