// Model/enterprise/LogisticsEnterprise.java
package Model.Enterprise;

import Model.Organization.ResourceDispatchUnit;
import Model.Organization.InventoryManager;

/**
 *
 * @author tiankaining
 */
public class LogisticsEnterprise extends Enterprise {

    public LogisticsEnterprise(String id, String name, String description, String manager) {
        super(id, name, EnterpriseType.Logistics, description, manager);

        // Initialize with Logistics-specific organizations
        ResourceDispatchUnit dispatch = new ResourceDispatchUnit("Transport");
        InventoryManager inventory = new InventoryManager("Main Warehouse");

        this.addOrganization(dispatch);
        this.addOrganization(inventory);
    }
}