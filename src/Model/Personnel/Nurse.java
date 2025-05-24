// Model/personnel/roles/NurseRole.java
package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.Role.NurseRole;

public class Nurse extends Employee {
    private String nurseType;
    private String licenseNumber;

    public Nurse(String nurseType, String licenseNumber,
                 String id, String name, String gender, int age, String dateOfBirth,
                 String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new NurseRole(), organization, contactInfo);
        this.nurseType = nurseType;
        this.licenseNumber = licenseNumber;
    }

    public Nurse() {
        super();
    }

    public String getNurseType() {
        return nurseType;
    }

    public void setNurseType(String nurseType) {
        this.nurseType = nurseType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
