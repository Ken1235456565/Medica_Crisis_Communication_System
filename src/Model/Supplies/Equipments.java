package Model.Supplies;

import java.util.Date;

/**
 * Represents a piece of equipment (which is a type of supply).
 */
public class Equipments extends SupplyItem {

    private String equipmentId;
    private String model;
    private Date purchaseDate;
    private Date maintenanceDate;
    private String warrantyInformation;
    private String department;   // Which department uses it
    private String status;       // "Available", "In Use", "Under Maintenance"

    private static int count = 1;

    public Equipments() {
        this.equipmentId = "EQP" + count++;
    }

    public Equipments(String name, String description, String type, int quantity, double unitPrice,
                      String model, Date purchaseDate, String department) {
        super(name, description, type, quantity, unitPrice);
        this.equipmentId = "EQP" + count++;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.department = department;
        this.status = "Available";
    }

    // Getters and setters

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getWarrantyInformation() {
        return warrantyInformation;
    }

    public void setWarrantyInformation(String warrantyInformation) {
        this.warrantyInformation = warrantyInformation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Equipment-specific methods (e.g., scheduleMaintenance)
    public void scheduleMaintenance(Date date) {
        this.maintenanceDate = date;
        this.status = "Under Maintenance";
    }

    public void markAvailable() {
        this.status = "Available";
    }

    public void markInUse() {
        this.status = "In Use";
    }

    @Override
    public String toString() {
        return super.toString() + " - Model: " + model + ", Status: " + status;
    }
}