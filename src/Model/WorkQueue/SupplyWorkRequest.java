package Model.WorkQueue;


import Model.Supplies.SupplyItem;
import java.util.Date;

/**
 * Represents a request for supplies.
 * This would be used by any Organization that needs to order supplies.
 */
public class SupplyWorkRequest extends WorkRequest { // Extends WorkRequest

    private SupplyItem supplyItem;
    private int quantity;
    private String urgency;       // "High", "Medium", "Low"
    private String requesterDepartment;
    private String deliveryLocation;

    public SupplyWorkRequest() {
        this.setStatus("Requested");
        this.setMessage("Supply Request");
    }

    public SupplyWorkRequest(SupplyItem supplyItem, int quantity, String requesterDepartment) {
        this();
        this.supplyItem = supplyItem;
        this.quantity = quantity;
        this.requesterDepartment = requesterDepartment;
        this.setMessage("Request " + quantity + " of " + supplyItem.getName());
    }

    // Getters and setters

    public SupplyItem getSupplyItem() {
        return supplyItem;
    }

    public void setSupplyItem(SupplyItem supplyItem) {
        this.supplyItem = supplyItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getRequesterDepartment() {
        return requesterDepartment;
    }

    public void setRequesterDepartment(String requesterDepartment) {
        this.requesterDepartment = requesterDepartment;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    // Methods to manage the supply request

    public void approveRequest() {
        this.setStatus("Approved");
    }

    public void fulfillRequest() {
        this.setStatus("Fulfilled");
        this.setResolveDate(new Date());
    }

    public void rejectRequest(String rejectionReason) {
        this.setStatus("Rejected");
        this.setResolveDate(new Date());
        this.setMessage((this.getMessage() != null ? this.getMessage() + "\n" : "") + "Rejected: " + rejectionReason);
    }

    @Override
    public String toString() {
        return getMessage() + " (" + urgency + ") - " + getStatus();
    }
}