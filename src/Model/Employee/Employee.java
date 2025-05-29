// Model/Personnel/Employee.java
package Model.Employee;

import Model.Role.Role;
import Model.Person.ContactInfo;
import Model.Employee.PayrollRecord;
import Model.Person.Person;
import Model.User.UserAccount;



public class Employee extends Person {
    private String position;
    PayrollRecord payrollRecord;
    private String department;
    private boolean isActive;
    private UserAccount userAccount;
    private static int counter = 1;

    public Employee() {
        super();
        if (this.getId() == null || this.getId().isEmpty()) {
            this.setId(generateEmployeeId());
        }
        this.isActive = true;
    }

    public Employee(String id, String name, String gender, int age, String dateOfBirth,
                String position, String department, ContactInfo contactInfo) {
    super(id != null ? id : generateEmployeeId(), name, gender, age, dateOfBirth, contactInfo);
    this.position = position;
    this.department = department;
    this.isActive = true;
}


    public Employee(String name, String gender, int age, String dateOfBirth,
                String position, String department, ContactInfo contactInfo) {
    super(generateEmployeeId(), name, gender, age, dateOfBirth, contactInfo);
    this.position = position;
    this.department = department;
    this.isActive = true;
}

    
    

    public static String generateEmployeeId() {
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
    
    public UserAccount getUserAccount() {
        return userAccount;
    }
    
    
    public double calculateMySalary() {
        if (this.payrollRecord != null) {
            return this.payrollRecord.calculateNetSalary();
        }
        return 0.0; // Or throw an exception if payroll record is expected to always exist
    }
    
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    
    
    @Override
    public String toString() {
        return getName() + " (" + position + ", " + department + ")";
    }
}
