package Model.Supplies;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a delivery of supplies.
 */
public class Delivery {

    private String deliveryId;
    private List<SupplyItem> items;
    private String destination;
    private Date deliveryDate;
    private String status;       // "Pending", "In Transit", "Delivered", "Cancelled"
    private String vehicleUsed;
    private String driverName;
    private String notes;

    private static int count = 1;

    public Delivery() {
        this.deliveryId = "DEL" + count++;
        this.items = new ArrayList<>();
        this.status = "Pending";
    }

    public Delivery(String destination, Date deliveryDate) {
        this();
        this.destination = destination;
        this.deliveryDate = deliveryDate;
    }

    // Getters and setters

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public List<SupplyItem> getItems() {
        return items;
    }

    public void setItems(List<SupplyItem> items) {
        this.items = items;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleUsed() {
        return vehicleUsed;
    }

    public void setVehicleUsed(String vehicleUsed) {
        this.vehicleUsed = vehicleUsed;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Delivery-specific methods

    public void addItem(SupplyItem item) {
        this.items.add(item);
    }

    public void removeItem(SupplyItem item) {
        this.items.remove(item);
    }

    public double getTotalDeliveryValue() {
        double total = 0;
        for (SupplyItem item : items) {
            total += item.getUnitPrice() * item.getQuantity();
        }
        return total;
    }

    public void startDelivery(String vehicle, String driver) {
        if (status.equals("Pending")) {
            this.status = "In Transit";
            this.vehicleUsed = vehicle;
            this.driverName = driver;
        } else {
            System.out.println("Cannot start delivery: " + status);
        }
    }

    public void completeDelivery() {
        if (status.equals("In Transit")) {
            this.status = "Delivered";
        } else {
            System.out.println("Cannot complete delivery: " + status);
        }
    }

    public void cancelDelivery(String reason) {
        this.status = "Cancelled";
        this.notes = (this.notes != null ? this.notes + "\n" : "") + "Cancelled: " + reason;
    }

    @Override
    public String toString() {
        return "Delivery to " + destination + " on " + deliveryDate + " [" + status + "]";
    }
}