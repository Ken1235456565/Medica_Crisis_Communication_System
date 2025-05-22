// Model/enterprise/EmergencyEnterprise.java
package Model.Enterprise;

import Model.Organization.EmergencyResponseUnit;
import Model.Organization.ResourceDispatchUnit;

/**
 *
 * @author tiankaining
 */
public class EmergencyEnterprise extends Enterprise {

    public EmergencyEnterprise(String id, String name, String description, String manager) {
        super(id, name, EnterpriseType.Emergency, description, manager);

        // Initialize with Emergency-specific organizations
        EmergencyResponseUnit responseUnit = new EmergencyResponseUnit("Field Operations");
        ResourceDispatchUnit dispatchUnit = new ResourceDispatchUnit("911 Dispatch");

        this.addOrganization(responseUnit);
        this.addOrganization(dispatchUnit);
    }
}