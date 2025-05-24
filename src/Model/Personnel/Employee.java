// Model/Personnel/Employee.java
package Model.Personnel;

import Model.Role.Role;
import Model.Person.ContactInfo;
import Model.User.UserAccount;


public class Employee extends UserAccount {
    private String position;
    private String department;
    private boolean isActive;
    private static int counter = 1;

    public Employee() {
        super();
        if (this.getId() == null || this.getId().isEmpty()) {
            this.setId(generateEmployeeId());
        }
        this.isActive = true;
    }

    public Employee(String name, String username, String password, Role role,
                    String position, String department) {
        super(username, password, role, null, new ContactInfo());
        this.setName(name);
        this.setId(generateEmployeeId());
        this.position = position;
        this.department = department;
        this.isActive = true;
    }

    public Employee(String name, String username, String password, Role role,
                    String organization, ContactInfo contactInfo,
                    String position, String department) {
        super(null, name, null, 0, null,
              username, password, role, organization, contactInfo);
        this.setId(generateEmployeeId());
        this.position = position;
        this.department = department;
        this.isActive = true;
    }

    private String generateEmployeeId() {
        return "EMP" + counter++;
    }

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
        return getName() + " (" + position + ", " + department + ")";
    }
}
