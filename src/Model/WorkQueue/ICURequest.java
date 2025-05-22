package Model.WorkQueue;

import Model.Patient.Patient;
import java.util.Date;

/**
 * Represents a request for an ICU bed.
 * This would be used by the ClinicalServicesUnit.
 */
public class ICURequest extends WorkRequest { // Extends WorkRequest

    private Patient patient;
    private String icuReason;
    private Date requestedDate;
    private Date bedAssignedDate;
    private String bedId;
    private String attendingPhysician;

    public ICURequest() {
        this.setStatus("Requested");
        this.setMessage("ICU Bed Request");
        this.requestedDate = new Date();
    }

    public ICURequest(Patient patient, String icuReason) {
        this();
        this.patient = patient;
        this.icuReason = icuReason;
        this.setMessage("ICU for " + patient.getName() + " due to " + icuReason);
    }

    // Getters and setters

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getIcuReason() {
        return icuReason;
    }

    public void setIcuReason(String icuReason) {
        this.icuReason = icuReason;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Date getBedAssignedDate() {
        return bedAssignedDate;
    }

    public void setBedAssignedDate(Date bedAssignedDate) {
        this.bedAssignedDate = bedAssignedDate;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public String getAttendingPhysician() {
        return attendingPhysician;
    }

    public void setAttendingPhysician(String attendingPhysician) {
        this.attendingPhysician = attendingPhysician;
    }

    // Methods to manage the ICU request

    public void assignBed(String bedId, Date assignDate) {
        this.bedId = bedId;
        this.bedAssignedDate = assignDate;
        this.setStatus("Bed Assigned");
    }

    public void completeRequest() {
        this.setStatus("Completed");
        this.setResolveDate(new Date());
    }

    public void cancelRequest() {
        this.setStatus("Cancelled");
        this.setResolveDate(new Date());
    }

    @Override
    public String toString() {
        return getMessage() + " - " + getStatus();
    }
}