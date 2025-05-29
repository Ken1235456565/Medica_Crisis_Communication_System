// Model/User/UserAccount.java
package Model.User;

import Model.Person.ContactInfo;
import Model.Role.Role;
import Model.Person.Person; // Import Person
import Model.Employee.Employee;
import Model.Personnel.Donor;

public class UserAccount {

    private String username;
    private String password;
    private Role role;
    private String organization;
    private Employee employee;
    private Donor donor;

    public UserAccount() {
        // 默认构造器
    }

    public UserAccount(String username, String password, Role role, String organization, Employee employee) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.organization = organization;
        this.employee = employee;
    }
    
    public UserAccount(String username, String password, Role role, String organization, Person person) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.organization = organization;

    if (person instanceof Employee) {
        this.employee = (Employee) person;
    } else if (person instanceof Donor) {
        this.donor = (Donor) person; // 👉 你可以加这个字段来引用
    }
}

    //  所有信息都从 employee 里取
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getOrganization() {
        return organization;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // 如果你需要访问名字、性别等，可以转发
    public String getName() {
        return employee.getName();
    }

    public String getGender() {
        return employee.getGender();
    }

    public ContactInfo getContactInfo() {
        return employee.getContactInfo();
    }
    
    public String getId() {
        return employee != null ? employee.getId() : "unknown";
    }

    @Override
    public String toString() {
        return username + " (" + (role != null ? role.getName() : "No Role") + ")";
    }
}
