// Model/Personnel/Employee.java
package Model.Employee;

import Model.Role.Role;
import Model.Person.ContactInfo;
import Model.Employee.PayrollRecord;
import Model.User.UserAccount;


public class Employee extends UserAccount {
    private String position;
    PayrollRecord payrollRecord;
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
    
    public Employee(String id, String name, String gender, int age, String dateOfBirth,
                String username, String password, Role role,
                String organization, ContactInfo contactInfo) {
    super(id, name, gender, age, dateOfBirth, username, password, role, organization, contactInfo);
    this.setId(id != null ? id : generateEmployeeId());
    this.isActive = true;
}

    protected String generateEmployeeId() {
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
    
    public void setPayrollRecord(PayrollRecord payrollRecord) {
        this.payrollRecord = payrollRecord;
    }
    
    public double calculateMySalary() {
        if (this.payrollRecord != null) {
            return this.payrollRecord.calculateNetSalary();
        }
        return 0.0; // Or throw an exception if payroll record is expected to always exist
    }
    
    @Override
    public String toString() {
        return getName() + " (" + position + ", " + department + ")";
    }
}
