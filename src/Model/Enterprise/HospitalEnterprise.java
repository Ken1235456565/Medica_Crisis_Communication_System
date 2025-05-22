// Model/Enterprise/HospitalEnterprise.java
package Model.Enterprise;

import Model.Organization.ClinicalServicesUnit;
import Model.Organization.AdministrationUnit;

/**
 *
 * @author MyPC1
 */
public class HospitalEnterprise extends Enterprise { // Corrected: extends Enterprise

    public HospitalEnterprise(String id, String name, String description, String manager) {
        super(id, name, EnterpriseType.Hospital, description, manager);

        // Initialize with Hospital-specific organizations
        ClinicalServicesUnit clinicalServices = new ClinicalServicesUnit("Clinical Services");
        // This is the line that might be causing the error depending on its exact form
        AdministrationUnit adminUnit = new AdministrationUnit("Hospital Admin", null); // (This was the corrected line)

        this.addOrganization(clinicalServices);
        this.addOrganization(adminUnit);
    }
}