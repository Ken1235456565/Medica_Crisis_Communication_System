// Model/enterprise/LogisticsEnterprise.java
package Model.Enterprise;

import Model.Organization.ResourceDispatchUnit;
import Model.Organization.SupplyChainManagementUnit;

/**
 *
 * @author tiankaining
 */
public class LogisticsEnterprise extends Enterprise {

    public LogisticsEnterprise(String id, String name, String description, String manager) {
        super(id, name, EnterpriseType.Logistics, description, manager);

        // Initialize with Logistics-specific organizations
        ResourceDispatchUnit dispatch = new ResourceDispatchUnit("Transport");
        SupplyChainManagementUnit inventory = new SupplyChainManagementUnit("Main Warehouse");

        this.addOrganization(dispatch);
        this.addOrganization(inventory);
    }
}