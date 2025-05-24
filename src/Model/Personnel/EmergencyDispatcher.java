// Model/personnel/roles/EmergencyDispatcherRole.java
package Model.Personnel;

import Model.Role.Role;

public class EmergencyDispatcher extends Role {
    private String dispatchZone;
    private String certificationLevel;

    public EmergencyDispatcher() {
        super("EMERGENCYDISPATCHER", "Emergency Dispatcher", false, "Dispatches emergency services"); // Added line
    }

    public EmergencyDispatcher(String id, String name, boolean isAdmin, String description, String dispatchZone, String certificationLevel) {
        super(id, name, isAdmin, description);
        this.dispatchZone = dispatchZone;
        this.certificationLevel = certificationLevel;
    }

    public String getDispatchZone() {
        return dispatchZone;
    }

    public void setDispatchZone(String dispatchZone) {
        this.dispatchZone = dispatchZone;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }
}