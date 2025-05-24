
// Model/personnel/roles/EmergencyResponderRole.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Role.EmergencyResponderRole;
import Model.User.UserAccount;

public class EmergencyResponder extends UserAccount {
    private String responderType;
    private String certificationLevel;
    private String vehicleAssigned;

    public EmergencyResponder(String responderType, String certificationLevel, String vehicleAssigned,
                              String id, String name, String gender, int age, String dateOfBirth,
                              String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new EmergencyResponderRole(), organization, contactInfo);
        this.responderType = responderType;
        this.certificationLevel = certificationLevel;
        this.vehicleAssigned = vehicleAssigned;
    }

    public EmergencyResponder() {
        super();
    }

    // Getters and Setters
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

