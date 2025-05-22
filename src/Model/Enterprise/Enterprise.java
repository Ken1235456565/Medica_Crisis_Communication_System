package Model.Enterprise;

import Model.Organization.Organization;
import Model.Person.ContactInfo;
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
    private List<Organization> organizations; // ADDED: List of organizations

    // Define EnterpriseType enum INSIDE the Enterprise class
    public enum EnterpriseType {
        Hospital,
        Emergency,
        Logistics,
        PublicHealth  // Added PublicHealth
        // ... other types
    }

    public Enterprise() {
        this.organizations = new ArrayList<>(); // Initialize the list
        this.contactInfo = new ContactInfo(); // Initialize ContactInfo
    }

    public Enterprise(String id, String name, EnterpriseType type, String description, String manager) {
        this(); // Call default constructor
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.manager = manager;
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

    public List<Organization> getOrganizations() { return organizations; } // ADDED
    public void setOrganizations(List<Organization> organizations) { this.organizations = organizations; } // ADDED

    // Method to add an organization
    public void addOrganization(Organization organization) {
        this.organizations.add(organization);
    }

    // Method to find an organization by name (optional)
    public Organization findOrganizationByName(String name) {
        for (Organization org : this.organizations) {
            if (org.getOrganizationName().equals(name)) {
                return org;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name; // Useful for ComboBox display
    }
}