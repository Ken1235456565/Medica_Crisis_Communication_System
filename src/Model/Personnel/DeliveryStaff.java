// Model/personnel/roles/DeliveryStaffRole.java
package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.Role.DeliveryStaffRole;

public class DeliveryStaff extends Employee {
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

    public String getVehicleType() {
        return vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return getName() + " (Delivery Staff - " + vehicleType + ")";
    }
}


