package Model.Organization;

import Model.Employee.Employee;
import Model.Patient.Patient;
import Model.WorkQueue.EmergencyWorkRequest;
import Model.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Emergency Response Unit for handling urgent medical situations
 * @author tiankaining
 */
public class EmergencyResponseUnit extends Organization { // EXTENDS Organization
    private String unitName;
    private List<Employee> emergencyStaff;
    private List<Patient> emergencyPatients;
    private Map<String, Boolean> emergencyVehicleStatus; // Vehicle ID -> Available status
    private List<WorkRequest> emergencyCalls;
    private int alertLevel; // 1-5, with 5 being highest
    private Map<String, Integer> responseTimeMetrics; // Call ID -> Response time in minutes
    
    // Default constructor
    public EmergencyResponseUnit() {
        super("Emergency Response");
        this.unitName = "Emergency Response";
        this.emergencyStaff = new ArrayList<>();
        this.emergencyPatients = new ArrayList<>();
        this.emergencyVehicleStatus = new HashMap<>();
        this.emergencyCalls = new ArrayList<>();
        this.alertLevel = 1; // Default normal level
        this.responseTimeMetrics = new HashMap<>();
    }
    
    // Constructor with unit name
    public EmergencyResponseUnit(String unitName) {
        super(unitName);
        this.unitName = unitName;
        this.emergencyStaff = new ArrayList<>();
        this.emergencyPatients = new ArrayList<>();
        this.emergencyVehicleStatus = new HashMap<>();
        this.emergencyCalls = new ArrayList<>();
        this.alertLevel = 1; // Default normal level
        this.responseTimeMetrics = new HashMap<>();
    }
    
    // Constructor with admin
    public EmergencyResponseUnit(String unitName, Employee admin) {
        super(unitName, admin);
        this.unitName = unitName;
        this.emergencyStaff = new ArrayList<>();
        this.emergencyPatients = new ArrayList<>();
        this.emergencyVehicleStatus = new HashMap<>();
        this.emergencyCalls = new ArrayList<>();
        this.alertLevel = 1; // Default normal level
        this.responseTimeMetrics = new HashMap<>();
    }
    
    // Getters and setters
    @Override
    public String getOrganizationName() {
        return unitName;
    }
    
    @Override
    public void setOrganizationName(String unitName) {
        this.unitName = unitName;
        super.setOrganizationName(unitName);
    }
    
    public List<Employee> getEmergencyStaff() {
        return emergencyStaff;
    }
    
    public List<Patient> getEmergencyPatients() {
        return emergencyPatients;
    }
    
    public Map<String, Boolean> getEmergencyVehicleStatus() {
        return emergencyVehicleStatus;
    }
    
    public List<WorkRequest> getEmergencyCalls() {
        return emergencyCalls;
    }
    
    public int getAlertLevel() {
        return alertLevel;
    }
    
    public void setAlertLevel(int alertLevel) {
        if (alertLevel >= 1 && alertLevel <= 5) {
            this.alertLevel = alertLevel;
        }
    }
    
    public Map<String, Integer> getResponseTimeMetrics() {
        return responseTimeMetrics;
    }
    
    // Add emergency staff
    public void addEmergencyStaff(Employee employee) {
        emergencyStaff.add(employee);
        addEmployee(employee); // Also add to general employee directory
    }
    
    // Remove emergency staff
    public void removeEmergencyStaff(Employee employee) {
        emergencyStaff.remove(employee);
        removeEmployee(employee); // Also remove from general employee directory
    }
    
    // Add emergency vehicle
    public void addEmergencyVehicle(String vehicleId, boolean isAvailable) {
        emergencyVehicleStatus.put(vehicleId, isAvailable);
    }
    
    // Update vehicle status
    public void updateVehicleStatus(String vehicleId, boolean isAvailable) {
        if (emergencyVehicleStatus.containsKey(vehicleId)) {
            emergencyVehicleStatus.put(vehicleId, isAvailable);
        }
    }
    
    // Get available vehicles
    public List<String> getAvailableVehicles() {
        List<String> availableVehicles = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : emergencyVehicleStatus.entrySet()) {
            if (entry.getValue()) {
                availableVehicles.add(entry.getKey());
            }
        }
        return availableVehicles;
    }
    
    // Register emergency patient
    public void registerEmergencyPatient(Patient patient) {
        emergencyPatients.add(patient);
    }
    
    // Remove emergency patient
    public void removeEmergencyPatient(Patient patient) {
        emergencyPatients.remove(patient);
    }
    
    // Create emergency call
    public EmergencyWorkRequest createEmergencyCall(String emergencyType, String location, String reportedBy) {
        EmergencyWorkRequest emergencyCall = new EmergencyWorkRequest(emergencyType, location, 3, reportedBy);
        emergencyCalls.add(emergencyCall);
        addWorkRequest(emergencyCall); // Also add to general work queue
        return emergencyCall;
    }
    
    // Dispatch emergency response
    public boolean dispatchEmergencyResponse(EmergencyWorkRequest emergencyCall, Employee responder, String vehicleId) {
        // Check if vehicle is available
        if (!emergencyVehicleStatus.containsKey(vehicleId) || !emergencyVehicleStatus.get(vehicleId)) {
            return false;
        }
        
        // Assign responder and update vehicle status
        emergencyCall.setReceiver(responder);
        emergencyCall.setStatus("Dispatched");
        emergencyCall.setAssignedVehicle(vehicleId);
        emergencyVehicleStatus.put(vehicleId, false); // Mark vehicle as unavailable
        
        return true;
    }
    
    // Complete emergency call
    public void completeEmergencyCall(EmergencyWorkRequest emergencyCall, String vehicleId, int responseTimeInMinutes) {
        emergencyCall.setStatus("Completed");
        emergencyCall.setResolveDate(new Date());
        
        // Mark vehicle as available again
        if (emergencyVehicleStatus.containsKey(vehicleId)) {
            emergencyVehicleStatus.put(vehicleId, true);
        }
        
        // Record response time metric
        responseTimeMetrics.put(emergencyCall.getRequestId(), responseTimeInMinutes);
    }
    
    // Get active emergency calls
    public List<WorkRequest> getActiveEmergencyCalls() {
        List<WorkRequest> activeCalls = new ArrayList<>();
        for (WorkRequest call : emergencyCalls) {
            if (call.getStatus().equals("Reported") || call.getStatus().equals("Dispatched") || call.getStatus().equals("In Transit")) {
                activeCalls.add(call);
            }
        }
        return activeCalls;
    }
    
    // Calculate average response time
    public double calculateAverageResponseTime() {
        if (responseTimeMetrics.isEmpty()) {
            return 0;
        }
        
        int total = 0;
        for (int time : responseTimeMetrics.values()) {
            total += time;
        }
        return (double) total / responseTimeMetrics.size();
    }
    
    // Escalate alert level
    public void escalateAlertLevel() {
        if (alertLevel < 5) {
            alertLevel++;
        }
    }
    
    // Deescalate alert level
    public void deescalateAlertLevel() {
        if (alertLevel > 1) {
            alertLevel--;
        }
    }
    
    // Trigger mass casualty protocol
    public void triggerMassCasualtyProtocol(String incident, String location, int estimatedVictims) {
        // Set highest alert level
        alertLevel = 5;
        
        // Create emergency call with mass casualty flag
        String message = "MASS CASUALTY INCIDENT: " + incident + " at " + location + 
                        " with estimated " + estimatedVictims + " victims";
        
        EmergencyWorkRequest massCasualtyCall = new EmergencyWorkRequest("Mass Casualty", location, 5, "System"); // High priority
        massCasualtyCall.setMessage(message);
        
        emergencyCalls.add(massCasualtyCall);
        addWorkRequest(massCasualtyCall); // Also add to general work queue
        
        // Mark all vehicles as needed
        for (String vehicleId : emergencyVehicleStatus.keySet()) {
            emergencyVehicleStatus.put(vehicleId, false); // Mark all as unavailable/in use
        }
    }
    
    @Override
    public String toString() {
        return unitName + " [Staff: " + emergencyStaff.size() + 
               ", Alert Level: " + alertLevel + 
               ", Active Calls: " + getActiveEmergencyCalls().size() + 
               ", Available Vehicles: " + getAvailableVehicles().size() + "]";
    }
}