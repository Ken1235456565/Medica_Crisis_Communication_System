package Model.Supplies;

import java.util.Date;

/**
 * Represents a general supply item.
 */
public class SupplyItem {

    private String supplyId;
    private String name;
    private String description;
    private String type;       // e.g., "Medical", "Equipment", "Stationery"
    private int quantity;
    private double unitPrice;
    private Date expirationDate; // If applicable
    private String manufacturer;
    private String vendor;

    private static int count = 1;

    public SupplyItem() {
        this.supplyId = "SUP" + count++;
    }

    public SupplyItem(String name, String description, String type, int quantity, double unitPrice) {
        this();
        this.name = name;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getters and setters

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getTotalValue() {
        return unitPrice * quantity;
    }

    // Methods to adjust quantity
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void deductQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
        } else {
            // Handle insufficient quantity (throw exception, log error, etc.)
            System.out.println("Insufficient quantity of " + name);
        }
    }

    public boolean isExpired() {
        if (expirationDate == null) {
            return false; // Not applicable
        }
        return expirationDate.before(new Date());
    }

    @Override
    public String toString() {
        return name + " (" + type + ") - Qty: " + quantity;
    }
}