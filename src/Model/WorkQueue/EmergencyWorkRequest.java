package Model.WorkQueue;

import java.util.Date;

/**
 * Represents a work request for an emergency situation.
 * This would be used by the EmergencyResponseUnit.
 */
public class EmergencyWorkRequest extends WorkRequest { // Extends WorkRequest

    private String emergencyType;
    private String location;
    private int priorityLevel; // 1 (low) to 5 (critical)
    private String reportedBy;
    private String assignedVehicle;

    public EmergencyWorkRequest() {
        this.setStatus("Reported");
        this.setMessage("Emergency Request");
    }

    public EmergencyWorkRequest(String emergencyType, String location, int priorityLevel, String reportedBy) {
        this();
        this.emergencyType = emergencyType;
        this.location = location;
        this.priorityLevel = priorityLevel;
        this.reportedBy = reportedBy;
        this.setMessage("Emergency: " + emergencyType + " at " + location);
    }

    // Getters and setters

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getAssignedVehicle() {
        return assignedVehicle;
    }

    public void setAssignedVehicle(String assignedVehicle) {
        this.assignedVehicle = assignedVehicle;
    }

    // Methods to manage the emergency response

    public void dispatch(String vehicle) {
        this.assignedVehicle = vehicle;
        this.setStatus("Dispatched");
    }

    public void respondToEmergency() {
        this.setStatus("In Transit");
    }

    public void completeEmergencyResponse() {
        this.setStatus("Completed");
        this.setResolveDate(new Date());
    }

    @Override
    public String toString() {
        return getMessage() + " (Priority: " + priorityLevel + ") - " + getStatus();
    }

    public String getRequestId() {
        return super.getRequestId();
    }
}