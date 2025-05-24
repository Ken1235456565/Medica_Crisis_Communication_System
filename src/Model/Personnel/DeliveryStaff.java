// Model/personnel/roles/DeliveryStaffRole.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Role.DeliveryStaffRole;
import Model.User.UserAccount;

public class DeliveryStaff extends UserAccount {
    private String vehicleType;
    private String licenseNumber;

    public DeliveryStaff(String vehicleType, String licenseNumber,
                         String id, String name, String gender, int age, String dateOfBirth,
                         String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new DeliveryStaffRole(), organization, contactInfo);
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
    }

    public DeliveryStaff() {
        super();
    }

    // Getters and Setters
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
