// Model/personnel/roles/EmergencyDispatcherRole.java
package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.Role.EmergencyDispatcherRole;
import Model.Role.Role;
import Model.User.UserAccount;

public class EmergencyDispatcher extends Employee {
    private String dispatchZone;
    private String certificationLevel;

    public EmergencyDispatcher(String dispatchZone, String certificationLevel,
                               String id, String name, String gender, int age, String dateOfBirth,
                               String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new EmergencyDispatcherRole(), organization, contactInfo);
        this.dispatchZone = dispatchZone;
        this.certificationLevel = certificationLevel;
    }

    public EmergencyDispatcher() {
        super();
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

