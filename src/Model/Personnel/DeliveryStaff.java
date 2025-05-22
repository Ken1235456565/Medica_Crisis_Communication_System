// Model/personnel/roles/DeliveryStaffRole.java
package Model.Personnel;

import Model.Personnel.Role;

public class DeliveryStaff extends Role {
    private String vehicleType;
    private String licenseNumber;

    public DeliveryStaff() {
        super("DELIVERYSTAFF", "Delivery Staff", false, "Staff responsible for deliveries"); // Added line
    }

    public DeliveryStaff(String id, String name, boolean isAdmin, String description, String vehicleType, String licenseNumber) {
        super(id, name, isAdmin, description);
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}