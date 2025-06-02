/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Patient;

/**
 *
 * @author tiankaining
 */
public class MedicationAdministration {
    Patient patient;
    private String requestId;
    private String admissionDate;
    private String urgency;
    private String treatmentPlan;
    private String specialInstructions;
    private String doctorNotes;
    private String prescriptionDate;
    private String medication;
    private int dosage;
    private String sideEffect;
    private String nurseNotes;
    private static int idCounter = 1;
     // Assuming a link to patient by ID

    public MedicationAdministration(String patientId, String patientName, String admissionDate, 
                               String urgency, String treatmentPlan, String specialInstructions, 
                               String doctorNotes, String prescriptionDate, String medication, 
                               int dosage, String sideEffect, String nurseNotes) {
        this.requestId = "REQ-" + (idCounter++);
        this.patient = patient;
        this.admissionDate = admissionDate;
        this.urgency = urgency;
        this.treatmentPlan = treatmentPlan;
        this.specialInstructions = specialInstructions;
        this.doctorNotes = doctorNotes;
        this.prescriptionDate = prescriptionDate;
        this.medication = medication;
        this.dosage = dosage;
        this.sideEffect = sideEffect;
        this.nurseNotes = nurseNotes;
    }

public MedicationAdministration(Patient patient, String admissionDate, String urgency,
                                String treatmentPlan, String specialInstructions, String doctorNotes,
                                String prescriptionDate, String medication, int dosage,
                                String sideEffect, String nurseNotes) {
    this.patient = patient;
    this.admissionDate = admissionDate;
    this.urgency = urgency;
    this.treatmentPlan = treatmentPlan;
    this.specialInstructions = specialInstructions;
    this.doctorNotes = doctorNotes;
    this.prescriptionDate = prescriptionDate;
    this.medication = medication;
    this.dosage = dosage;
    this.sideEffect = sideEffect;
    this.nurseNotes = nurseNotes;

    // 自动生成唯一 Request ID
    this.requestId = "REQ-" + (idCounter++);
}


    // Getters for all fields (omitted for brevity)

    public String getRequestId() {
    return requestId;
}

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public String getMedication() {
        return medication;
    }

    public int getDosage() {
        return dosage;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public String getNurseNotes() {
        return nurseNotes;
    }

}
