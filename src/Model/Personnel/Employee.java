// Model/Personnel/Employee.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.User.UserAccount;


public class Employee extends UserAccount {
    // id, name, gender, age, dateOfBirth are now inherited from Person via UserAccount
    private String position;
    private String department;
    private boolean isActive;

    // Static ID counter
    private static int counter = 1;

    // Default constructor
    public Employee() {
        super(); // Calls UserAccount's default constructor which calls Person's default
        // The ID for Employee can still be generated here as a unique employee ID,
        // separate from the Person's ID if needed, or unify it.
        // For simplicity, let's assume Employee's ID is the same as Person's ID.
        // If it needs to be unique for Employee, then update it.
        // For now, let's use the ID inherited from Person and set it as Employee's ID.
        if (this.getId() == null || this.getId().isEmpty()) { // Ensure Person's ID is set
            this.setId(generateEmployeeId());
        }
        this.isActive = true;
    }

    // Constructor with basic employee information and UserAccount details, sets Person details
    public Employee(String name, String username, String password, Role role,
                    String position, String department) {
        // Calls UserAccount's constructor, which now calls Person's constructor.
        // Need to provide dummy/default values for gender, age, dateOfBirth if not provided here.
        // Let's create a new constructor in UserAccount for this specific Employee case.
        // For now, assume UserAccount constructor takes only username, password, role, org, contactinfo
        // and that name, gender, age, dob are set by the Employee's own logic or provided in full.
        super(username, password, role, null, new ContactInfo()); // organization is null, contactInfo is default
        // Now set the Person attributes explicitly
        this.setName(name); // This sets the name inherited from Person
        this.setId(generateEmployeeId()); // Set the employee-specific ID
        this.position = position;
        this.department = department;
        this.isActive = true;
    }

    // Constructor with full details including organization and contact info
    public Employee(String name, String username, String password, Role role,
                    String organization, ContactInfo contactInfo,
                    String position, String department) {
        // Calls UserAccount's constructor, which now takes Person details.
        // Need to provide dummy/default values for gender, age, dateOfBirth if not provided here.
        super(null, name, null, 0, null, // Dummy Person details, or pass them if available
              username, password, role, organization, contactInfo);
        this.setId(generateEmployeeId()); // Set the employee-specific ID
        this.position = position;
        this.department = department;
        this.isActive = true;
    }

    // Generate unique employee ID (can be used to set the 'id' inherited from Person)
    private String generateEmployeeId() {
        return "EMP" + counter++;
    }

    // Getters and setters for Employee specific attributes
    // getId() and setId() are inherited from Person, no need to duplicate

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return getName() + " (" + position + ", " + department + ")"; // getName() is inherited from Person
    }
}