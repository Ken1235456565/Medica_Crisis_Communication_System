package Model.Supplies;

import Model.Patient.Patient;
import java.util.Date;

/**
 * Represents an ICU bed (a specialized equipment).
 */
public class ICUbed extends Equipments {

    private String bedId;
    private boolean occupied;
    private Patient patient; // Who is occupying the bed (if any)
    private Date admissionDate;
    private Date dischargeDate;

    private static int count = 1;

    public ICUbed() {
        this.bedId = "ICU" + count++;
    }

    public ICUbed(String name, String description, int quantity, double unitPrice,
                  String model, Date purchaseDate, String department) {
        super(name, description, "ICU Bed", quantity, unitPrice, model, purchaseDate, department);
        this.bedId = "ICU" + count++;
        this.occupied = false;
    }

    // Getters and setters

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        this.occupied = (patient != null);
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    // ICU bed specific methods

    public void admitPatient(Patient patient, Date date) {
        if (!occupied) {
            this.patient = patient;
            this.occupied = true;
            this.admissionDate = date;
            this.dischargeDate = null; // Reset discharge date
            this.setStatus("In Use");   // Inherited from Equipments
        } else {
            System.out.println("Bed is currently occupied.");
        }
    }

    public void dischargePatient(Date date) {
        if (occupied) {
            this.patient = null;
            this.occupied = false;
            this.dischargeDate = date;
            this.setStatus("Available"); // Inherited from Equipments
        } else {
            System.out.println("Bed is not occupied.");
        }
    }

    @Override
    public String toString() {
        String patientInfo = (occupied && patient != null) ? " - Occupied by " + patient.getName() : " - Available";
        return super.toString() + patientInfo;
    }
}