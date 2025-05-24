// Model/personnel/roles/DoctorRole.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Role.DoctorRole;
import Model.User.UserAccount;


public class Doctor extends UserAccount {
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

    public Doctor() {
        super();
    }

    // Getters and Setters
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
}
