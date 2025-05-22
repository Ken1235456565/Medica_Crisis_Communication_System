// Model/patient/Patient.java
package Model.Patient;

import Model.Person.Person;

public class Patient extends Person {
    private String patientId;
    private MedicalRecord medicalRecord;

    private static int patientIdCounter = 1; // Static counter for patientId

    public Patient() {
        super(); // Call Person's default constructor
        this.patientId = "PAT" + patientIdCounter++; // Generate a unique patient ID
        this.medicalRecord = new MedicalRecord();
    }

    public Patient(String id, String name, String gender, int age, String dateOfBirth) {
        super(id, name, gender, age, dateOfBirth);
        this.patientId = "PAT" + patientIdCounter++; // Generate a unique patient ID
        this.medicalRecord = new MedicalRecord();
    }

    // NEW CONSTRUCTOR ADDED: To match (name, gender, age, dateOfBirth)
    public Patient(String name, String gender, int age, String dateOfBirth) {
        super("PERSON" + (Person.getCounter()), name, gender, age, dateOfBirth); // Assuming Person.getCounter() generates unique IDs for Person
        this.patientId = "PAT" + patientIdCounter++; // Generate a unique patient ID
        this.medicalRecord = new MedicalRecord();
    }

    // Constructor for PatientDirectory.createPatient(..., int historyId)
    public Patient(String name, String gender, int age, String dateOfBirth, int historyId) {
        this(name, gender, age, dateOfBirth); // Call the constructor above to set up basic patient info
        this.medicalRecord = new MedicalRecord(); // Re-initialize or assign medicalRecord if needed based on historyId
        // The historyId implies linking to an existing medical record or creating one with a specific ID.
        // For now, let's assume it's just an extra parameter and the MedicalRecord is initialized.
        // You might want to pass historyId to MedicalRecord constructor if it supports it.
        this.medicalRecord.setRecordId(String.valueOf(historyId)); // Set record ID from historyId
    }


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}