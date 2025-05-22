// Model/enterprise/PublicHealthEnterprise.java
package Model.Enterprise;

import Model.Organization.DonationManagementUnit;
import Model.Organization.AdministrationUnit;

/**
 *
 * @author tiankaining
 */
public class PublicHealthEnterprise extends Enterprise {

    public PublicHealthEnterprise(String id, String name, String description, String manager) {
        super(id, name, EnterpriseType.PublicHealth, description, manager);

        // Initialize with PublicHealth-specific organizations
        DonationManagementUnit donations = new DonationManagementUnit();
        // Corrected line: Pass null for the Employee admin parameter
        AdministrationUnit publicAdmin = new AdministrationUnit("Public Health Admin", null); // Pass null as Employee

        this.addOrganization(donations);
        this.addOrganization(publicAdmin);
    }
}