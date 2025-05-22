/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Network;

import Model.Enterprise.EnterpriseDirectory;
import Model.Person.ContactInfo;



/**
 *
 * @author MyPC1
 */
// Model/core/Network.java

public class Network {
    private String id;
    private String name;
    private String type; // Consider using an enum for type if multiple types exist
    private String description;
    private String manager;
    private ContactInfo contactInfo;
    private EnterpriseDirectory enterpriseDirectory; // Added EnterpriseDirectory

    public Network() {
        this.contactInfo = new ContactInfo();
        this.enterpriseDirectory = new EnterpriseDirectory(); // Initialize EnterpriseDirectory
    }

    public Network(String id, String name, String type, String description, String manager) {
        this(); // Call default constructor to initialize contactInfo and enterpriseDirectory
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

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    public ContactInfo getContactInfo() { return contactInfo; } // Changed to ContactInfo
    public void setContactInfo(ContactInfo contactInfo) { this.contactInfo = contactInfo; } // Changed to ContactInfo
    
    // Getter for EnterpriseDirectory
    public EnterpriseDirectory getEnterpriseDirectory() {
        return enterpriseDirectory;
    }

    public void setEnterpriseDirectory(EnterpriseDirectory enterpriseDirectory) {
        this.enterpriseDirectory = enterpriseDirectory;
    }
}
