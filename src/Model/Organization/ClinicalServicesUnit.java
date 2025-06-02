// Model/organization/ClinicalServicesUnit.java
package Model.Organization;

import Model.Employee.Employee;
import Model.Patient.Patient;
import Model.Patient.PatientDirectory;
import Model.Personnel.Admin;
import Model.Supplies.ICUbedCatalog;
import Model.Supplies.SupplyItemCatalog;
import Model.User.UserAccount;
import Model.WorkQueue.Appointment;
import Model.WorkQueue.AppointmentSchedule; // Changed to AppointmentSchedule
import Model.WorkQueue.EmergencyWorkRequest;
import Model.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Clinical Services Unit for managing medical care
 * @author tiankaining
 */
public class ClinicalServicesUnit extends Organization {
    private String unitName;
    private List<Employee> medicalStaff; // 医生、护士
    private List<Patient> patientList;
    private AppointmentSchedule appointmentSchedule; // Changed to AppointmentSchedule
    private List<WorkRequest> activeRequests; // 病人求助、转诊等
    private boolean emergencyReady; // 是否具备急救响应功能
    private ICUbedCatalog ICUbedCatalog;
    private PatientDirectory patientDirectory = new PatientDirectory();
    private SupplyItemCatalog supplyItemCatalog = new SupplyItemCatalog();

    // Default constructor
public ClinicalServicesUnit() {
    super("Clinical Services");
    this.unitName = "Clinical Services";
    this.ICUbedCatalog = new ICUbedCatalog(); // ✅ 防止 null
    this.emergencyReady = true;
    this.medicalStaff = new ArrayList<>();
    this.patientList = new ArrayList<>();
    this.appointmentSchedule = new AppointmentSchedule();
    this.activeRequests = new ArrayList<>();
    this.patientDirectory = new PatientDirectory();
    this.supplyItemCatalog = new SupplyItemCatalog();
}



    // Constructor with unit name
    public ClinicalServicesUnit(String unitName) {
        super(unitName);
        this.unitName = unitName;
        this.medicalStaff = new ArrayList<>();
        this.patientList = new ArrayList<>();
        this.appointmentSchedule = new AppointmentSchedule(); // Initialize AppointmentSchedule
        this.activeRequests = new ArrayList<>();
        this.emergencyReady = false;
    }

    // Constructor with detailed info
    public ClinicalServicesUnit(String unitName, Admin admin, boolean emergencyReady) {
        super(unitName, admin);
        this.unitName = unitName;
        this.medicalStaff = new ArrayList<>();
        this.patientList = new ArrayList<>();
        this.appointmentSchedule = new AppointmentSchedule(); // Initialize AppointmentSchedule
        this.activeRequests = new ArrayList<>();
        this.emergencyReady = emergencyReady;
    }
    
public ClinicalServicesUnit(String unitName, ICUbedCatalog icuBedCatalog, boolean emergencyReady) {
    super(unitName); // 调用父类 Organization 的构造函数
    this.unitName = unitName;
    this.ICUbedCatalog = icuBedCatalog; // ✅ 正确使用传进来的参数
    this.emergencyReady = emergencyReady;

    // 初始化其余字段以避免 NPE
    this.medicalStaff = new ArrayList<>();
    this.patientList = new ArrayList<>();
    this.appointmentSchedule = new AppointmentSchedule();
    this.activeRequests = new ArrayList<>();
    this.patientDirectory = new PatientDirectory();
    this.supplyItemCatalog = new SupplyItemCatalog();
}



    // Getters and setters
    @Override
    public String getOrganizationName() {
        return unitName;
    }

    public PatientDirectory getPatientDirectory() {
        return patientDirectory;
    }
    public SupplyItemCatalog getSupplyItemCatalog() {
        return supplyItemCatalog;
    }
    
    @Override
    public void setOrganizationName(String unitName) {
        this.unitName = unitName;
        super.setOrganizationName(unitName);
    }

    public List<Employee> getMedicalStaff() {
        return medicalStaff;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public AppointmentSchedule getAppointmentSchedule() {
        return appointmentSchedule;
    }

    public void setAppointmentSchedule(AppointmentSchedule appointmentSchedule) {
        this.appointmentSchedule = appointmentSchedule;
    }

    public List<WorkRequest> getActiveRequests() {
        return activeRequests;
    }

    public boolean isEmergencyReady() {
        return emergencyReady;
    }

    public void setEmergencyReady(boolean emergencyReady) {
        this.emergencyReady = emergencyReady;
    }

    // Add medical staff
    public void addMedicalStaff(Employee employee) {
        medicalStaff.add(employee);
        addEmployee(employee); // Also add to the general employee directory
    }

    // Remove medical staff
    public void removeMedicalStaff(Employee employee) {
        medicalStaff.remove(employee);
        removeEmployee(employee); // Also remove from the general employee directory
    }

    // Add patient
    public void addPatient(Patient patient) {
        patientList.add(patient);
    }

    // Remove patient
    public void removePatient(Patient patient) {
        patientList.remove(patient);
    }

    // Schedule appointment
    public boolean scheduleAppointment(Appointment appointment) {
        return appointmentSchedule.scheduleAppointment(appointment);
    }

    // Cancel appointment
    public boolean cancelAppointment(Appointment appointment, String reason) {
        return appointmentSchedule.cancelAppointment(appointment, reason);
    }

    // Add work request
    public void addActiveRequest(WorkRequest request) {
        activeRequests.add(request);
        addWorkRequest(request); // Also add to the general work queue
    }

    public ICUbedCatalog getICUbedCatalog() {
        return ICUbedCatalog;
    }

    public void setICUbedCatalog(ICUbedCatalog ICUbedCatalog) {
        this.ICUbedCatalog = ICUbedCatalog;
    }
    

    // Complete work request
    public void completeActiveRequest(WorkRequest request) {
        request.setStatus("Completed");
    }

    // Find doctor
    public UserAccount findDoctor(String doctorName) {
    for (UserAccount ua : userAccountDirectory.getUserAccountList()) {
        if (ua.getRole().getName().equals("Doctor") &&
            ua.getEmployee() != null &&
            ua.getEmployee().getName().equals(doctorName)) {
            return ua;
        }
    }
    return null;
}

    // Find nurse
    public UserAccount findNurse(String nurseName) {
    for (UserAccount ua : userAccountDirectory.getUserAccountList()) {
        if (ua.getRole().getName().equals("Nurse") &&
            ua.getEmployee() != null &&
            ua.getEmployee().getName().equals(nurseName)) {
            return ua;
        }
    }
    return null;
}

    // Find patient
    public Patient findPatient(String patientName) {
        for (Patient patient : patientList) {
            if (patient.getName().equals(patientName)) {
                return patient;
            }
        }
        return null;
    }

    // Find patient by ID
    public Patient findPatientById(String patientId) {
        for (Patient patient : patientList) {
            if (patient.getId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    // Find appointments for a patient
    public List<Appointment> findPatientAppointments(Patient patient) {
        return appointmentSchedule.findPatientAppointments(patient);
    }

    // Find appointments for a doctor
    public List<Appointment> findDoctorAppointments(Employee doctor) {
        return appointmentSchedule.findDoctorAppointments(doctor);
    }

    // Emergency response
    public boolean initiateEmergencyResponse(String emergencyType, String location) {
        if (!emergencyReady) {
            return false;
        }

        // Create emergency work request using the concrete EmergencyWorkRequest class
        EmergencyWorkRequest emergencyRequest = new EmergencyWorkRequest(
            emergencyType, // Type of emergency
            location,    // Location of emergency
            4,           // Priority level (e.g., 4 for "High")
            (admin != null ? admin.getName() : "Unknown Reporter") // Reported by admin's name
        );
        
        emergencyRequest.setSender(admin.getUserAccount());
        
        // Add to active requests
        addActiveRequest(emergencyRequest);

        return true;
    }

    @Override
    public String toString() {
        return unitName + " [Staff: " + medicalStaff.size() +
               ", Patients: " + patientList.size() +
               ", Emergency Ready: " + (emergencyReady ? "Yes" : "No") + "]";
    }
}