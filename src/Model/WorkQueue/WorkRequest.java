package Model.WorkQueue;

import Model.User.UserAccount;
import java.util.Date;

/**
 * Abstract class representing a generic work request.
 * Other specific work requests (e.g., SupplyWorkRequest, EmergencyWorkRequest)
 * will inherit from this class.
 */
public abstract class WorkRequest {

    private String requestId;
    private String message;       // Description of the request
    private UserAccount sender;     // User who created the request
    private UserAccount receiver;   // User assigned to handle the request
    String status;        // Current status of the request (e.g., "Pending", "InProgress", "Completed")
    private Date requestDate;     // Date when the request was created
    private Date resolveDate;     // Date when the request was resolved (if applicable)
    private static int counter = 1;

   
    
    public WorkRequest() {
        this.requestId = "WR" + counter++; // Initialize with a unique ID
        this.requestDate = new Date();
    }

    // Getters and setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserAccount getSender() {
        return sender;
    }

    public void setSender(UserAccount sender) {
        this.sender = sender;
    }

    public UserAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(UserAccount receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Date resolveDate) {
        this.resolveDate = resolveDate;
    }
    
    public String getRequestId() {
        return requestId; // The implementation returns the requestId field
    }

    @Override
    public String toString() {
        return message; // Basic toString; customize as needed
    }

}