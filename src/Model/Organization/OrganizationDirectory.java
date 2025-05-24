// Model/Organization/OrganizationDirectory.java
package Model.Organization;

import Model.Employee.Employee; // Import Employee
import java.util.ArrayList;
import java.util.List;

public class OrganizationDirectory {
    private List<Organization> organizationList;

    public OrganizationDirectory() {
        this.organizationList = new ArrayList<>();
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public void addOrganization(Organization organization) {
        this.organizationList.add(organization);
    }

    public void removeOrganization(Organization organization) {
        this.organizationList.remove(organization);
    }

    public Organization findOrganizationById(String id) {
        for (Organization org : organizationList) {
            if (org.getOrganizationId().equals(id)) {
                return org;
            }
        }
        return null;
    }

    public Organization findOrganizationByName(String name) {
        for (Organization org : organizationList) {
            if (org.getOrganizationName().equals(name)) {
                return org;
            }
        }
        return null;
    }

    // Factory method to create and add organizations - ADDED
    public Organization createOrganization(String type, String name, Employee admin) {
        Organization organization = null;
        switch (type) {
            case "ClinicalServices":
                organization = new ClinicalServicesUnit(name, admin, false);
                break;
            case "Administration":
                AdministrationUnit myAdminUnit = new AdministrationUnit("Main Administration Unit", admin); // AdministrationUnit constructor
                break;
            case "ResourceDispatch":
                organization = new ResourceDispatchUnit(name, admin);
                break;
            case "EmergencyResponse":
                organization = new EmergencyResponseUnit(name, admin);
                break;
            case "InventoryManagement":
                organization = new InventoryManager(name);
                break;
            case "DonationManagement":
                organization = new DonationManagementUnit(admin);
                break;
            case "OperationsSupport":
                organization = new OperationsSupportUnit(name, admin, "2025");
                break;
            // Add other organization types as needed
        }
        if (organization != null) {
            organizationList.add(organization);
        }
        return organization;
    }
}