// Model/personnel/roles/DoctorRole.java
package Model.Personnel;


public class Doctor extends Role {
    private String specialization;
    private String licenseNumber;

    public Doctor() {
        super("DOCTOR", "Doctor", false, "Medical practitioner"); // Added line
    }

    public Doctor(String id, String name, boolean isAdmin, String description, String specialization, String licenseNumber) {
        super(id, name, isAdmin, description);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

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