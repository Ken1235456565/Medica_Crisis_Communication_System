// Model/personnel/roles/NurseRole.java
package Model.Personnel;

import Model.Role.Role;

public class Nurse extends Role {
    private String nurseType;
    private String licenseNumber;

    public Nurse() {
    }

    public Nurse(String id, String name, boolean isAdmin, String description, String nurseType, String licenseNumber) {
        super(id, name, isAdmin, description);
        this.nurseType = nurseType;
        this.licenseNumber = licenseNumber;
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