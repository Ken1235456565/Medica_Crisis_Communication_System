package Model.Enterprise;

import Model.Organization.Organization;
import Model.Organization.OrganizationDirectory;
import Model.Person.ContactInfo;
import Model.Personnel.Admin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyPC1
 */
public abstract class Enterprise {
    private String id;
    private String name;
    private EnterpriseType type;  // Changed to EnterpriseType enum
    private String description;
    private String manager;
    private ContactInfo contactInfo; // Changed to ContactInfo
    private String networkBelong;
    private OrganizationDirectory organizations; // ADDED: List of organizations
    private Admin admin;

    // Define EnterpriseType enum INSIDE the Enterprise class
    public enum EnterpriseType {
        Hospital,
        Emergency,
        Logistics,
        PublicHealth  
    }

    public Enterprise() {
        this.organizations = new OrganizationDirectory();
        this.contactInfo = new ContactInfo();
    }

    public Enterprise(String id, String name, EnterpriseType type, String description, String manager) {
        this();
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.manager = manager;
    }
    
    public Enterprise(String id, String name, EnterpriseType type, String description, String manager,Admin admin) {
        this();
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.manager = manager;
        this.admin = admin;
    }

    // Getters and setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public EnterpriseType getType() { return type; } // Changed to EnterpriseType
    public void setType(EnterpriseType type) { this.type = type; } // Changed to EnterpriseType

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    public ContactInfo getContactInfo() { return contactInfo; } // Changed to ContactInfo
    public void setContactInfo(ContactInfo contactInfo) { this.contactInfo = contactInfo; } // Changed to ContactInfo

    public String getNetworkBelong() { return networkBelong; }
    public void setNetworkBelong(String networkBelong) { this.networkBelong = networkBelong; }

    public OrganizationDirectory getOrganizations() {
        return organizations;
    }

    public void setOrganizations(OrganizationDirectory organizations) {
        this.organizations = organizations;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    

    // Method to add an organization
    public void addOrganization(Organization organization) {
        this.organizations.addOrganization(organization);
    }

    public Organization findOrganizationByName(String name) {
        return this.organizations.findOrganizationByName(name);
    }


    @Override
    public String toString() {
        return name; // Useful for ComboBox display
    }
}