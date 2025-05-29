package Model.WorkQueue;

import Model.Supplies.Delivery;
import Model.Employee.Employee;
import java.util.Date;

/**
 * Represents a request to assign a delivery to a driver/vehicle.
 * This would be used by the ResourceDispatchUnit.
 */
public class DeliveryAssignmentRequest extends WorkRequest { // Extends WorkRequest

    private Delivery delivery;
    private Employee assignedDriver;
    private String assignedVehicle;
    private String dispatchNotes;

    public DeliveryAssignmentRequest() {
        this.setStatus("Pending");
        this.setMessage("Delivery Assignment Request");
    }

    public DeliveryAssignmentRequest(Delivery delivery) {
        this();
        this.delivery = delivery;
        this.setMessage("Assign driver for Delivery: " + delivery.getDeliveryId());
    }

    // Getters and setters

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Employee getAssignedDriver() {
        return assignedDriver;
    }

    public void setAssignedDriver(Employee assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    public String getAssignedVehicle() {
        return assignedVehicle;
    }

    public void setAssignedVehicle(String assignedVehicle) {
        this.assignedVehicle = assignedVehicle;
    }

    public String getDispatchNotes() {
        return dispatchNotes;
    }

    public void setDispatchNotes(String dispatchNotes) {
        this.dispatchNotes = dispatchNotes;
    }

    // Methods to assign/complete

    public void assignDelivery(Employee driver, String vehicle) {
        this.assignedDriver = driver;
        this.assignedVehicle = vehicle;
        this.setStatus("Assigned");
        this.setReceiver(driver.getUserAccount()); // Set the driver as the receiver
    }

    public void completeAssignment(String notes) {
        this.setStatus("Completed");
        this.dispatchNotes = notes;
        this.setResolveDate(new Date());
    }

    @Override
    public String toString() {
        return getMessage() + " - " + getStatus(); // Use getMessage() and getStatus()
    }
}