// Model/User/UserAccount.java
package Model.User;

import Model.Person.ContactInfo;
import Model.Role.Role;
import Model.Person.Person; // Import Person
import Model.Personnel.Employee;

public class UserAccount extends Person { // Extend Person
    private String username;
    private String password;
    private Role role;
    private String organization;
    private ContactInfo contactInfo;

    // Default constructor
    public UserAccount() {
        super(); // Calls Person's default constructor
        this.contactInfo = new ContactInfo(); // Initialize ContactInfo
    }

    // Constructor with all parameters including Person attributes
    public UserAccount(String id, String name, String gender, int age, String dateOfBirth, // Person attributes
                       String username, String password, Role role,
                       String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth); // Call Person's constructor
        this.username = username;
        this.password = password;
        this.role = role;
        this.organization = organization;
        this.contactInfo = contactInfo;
    }

    // New constructor to facilitate Employee creation when Person details are available via Employee's own constructor
    public UserAccount(String username, String password, Role role,
                       String organization, ContactInfo contactInfo) {
        super(); // Calls Person's default constructor, actual person details will be set by Employee
        this.username = username;
        this.password = password;
        this.role = role;
        this.organization = organization;
        this.contactInfo = contactInfo;
    }

    UserAccount(Employee employee, String username, String password, Role role, Object object) {
        // Since Employee extends Person, we can pass employee's Person details to Person's constructor
        super(employee.getId(), employee.getName(), employee.getGender(), employee.getAge(), employee.getDateOfBirth(), employee.getContactInfo());

        this.username = username;
        this.password = password;
        this.role = role;
        // Cast the Object to String for organization, assuming it's a String
        this.organization = (organization instanceof String) ? (String) organization : null;
    }

    // Getters and setters (id, name, gender, age, dateOfBirth are now inherited from Person)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public ContactInfo getContactInfo() { return contactInfo; }
    public void setContactInfo(ContactInfo contactInfo) { this.contactInfo = contactInfo; }

    @Override
    public String toString() {
        return username + " (" + (role != null ? role.getName() : "No Role") + ")";
    }
}