// Model/personnel/roles/DoctorRole.java
package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.Role.DoctorRole;
import Model.User.UserAccount;


public class Doctor extends Employee {
    private String specialization;
    private String licenseNumber;

    public Doctor(String specialization, String licenseNumber,
                  String id, String name, String gender, int age, String dateOfBirth,
                  String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new DoctorRole(), organization, contactInfo);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

    // Getter & Setter
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return getName() + " (" + specialization + ")";
    }
}

