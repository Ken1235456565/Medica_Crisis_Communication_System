package Model.Patient;

import java.util.ArrayList;

/**
 * A directory class to manage Patient objects
 */
public class PatientDirectory {
    private ArrayList<Patient> patientList;
    
    // Constructor
    public PatientDirectory() {
        patientList = new ArrayList<>();
    }
    
    // Get the list of patients
    public ArrayList<Patient> getPatientList() {
        return patientList;
    }
    
    // Create a new patient with basic info
    public Patient createPatient(String name, String gender, int age, String dateOfBirth) {
        Patient patient = new Patient(name, gender, age, dateOfBirth);
        patientList.add(patient);
        return patient;
    }
    
    // Create a patient with history ID
    public Patient createPatient(String name, String gender, int age, String dateOfBirth, int historyId) {
        Patient patient = new Patient(name, gender, age, dateOfBirth, historyId);
        patientList.add(patient);
        return patient;
    }
    
    // Remove a patient from the directory
    public void removePatient(Patient patient) {
        patientList.remove(patient);
    }
    
    // Find a patient by ID
    public Patient findPatientById(String id) {
        for (Patient patient : patientList) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }
    
    // Find a patient by patient ID (numeric)
    public Patient findPatientByPatientId(int patientId) {
        for (Patient patient : patientList) {
            if (patient.getPatientId().equals("PAT" + patientId)) {
                return patient;
            }
        }
        return null;
    }
    
    // Find a patient by name
    public Patient findPatientByName(String name) {
        for (Patient patient : patientList) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }
    
    // Filter patients by age range
    public ArrayList<Patient> filterPatientsByAgeRange(int minAge, int maxAge) {
        ArrayList<Patient> result = new ArrayList<>();
        for (Patient patient : patientList) {
            if (patient.getAge() >= minAge && patient.getAge() <= maxAge) {
                result.add(patient);
            }
        }
        return result;
    }
    
    // Filter patients by gender
    public ArrayList<Patient> filterPatientsByGender(String gender) {
        ArrayList<Patient> result = new ArrayList<>();
        for (Patient patient : patientList) {
            if (patient.getGender().equals(gender)) {
                result.add(patient);
            }
        }
        return result;
    }
    
    // Add a medical entry to a patient's record
    public void addMedicalEntry(Patient patient, String diagnosis, String treatment) {
        patient.getMedicalRecord().addMedicalEntry(diagnosis, treatment);
    }
}
