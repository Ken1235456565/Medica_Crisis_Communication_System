
// Model/personnel/roles/EmergencyResponderRole.java
package Model.Personnel;

import Model.Role.Role;

public class EmergencyResponder extends Role {
    private String responderType;
    private String certificationLevel;
    private String vehicleAssigned;

    public EmergencyResponder() {
        super("EMERGENCYRESPONDER", "Emergency Responder", false, "Responds to emergency calls"); // Added line
    }

    public EmergencyResponder(String id, String name, boolean isAdmin, String description, String responderType, String certificationLevel, String vehicleAssigned) {
        super(id, name, isAdmin, description);
        this.responderType = responderType;
        this.certificationLevel = certificationLevel;
        this.vehicleAssigned = vehicleAssigned;
    }

    public String getResponderType() {
        return responderType;
    }

    public void setResponderType(String responderType) {
        this.responderType = responderType;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    public String getVehicleAssigned() {
        return vehicleAssigned;
    }

    public void setVehicleAssigned(String vehicleAssigned) {
        this.vehicleAssigned = vehicleAssigned;
    }
}
