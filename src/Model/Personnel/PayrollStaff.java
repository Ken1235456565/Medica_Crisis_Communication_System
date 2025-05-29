// Model/personnel/roles/PayrollRole.java
package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.Role.PayrollStaffRole;

public class PayrollStaff extends Employee {
    private String payrollStaffID;
    private String payrollPeriod;

    public PayrollStaff(String payrollStaffID, String payrollPeriod,
                        String id, String name, String gender, int age, String dateOfBirth,
                        String position, String department, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, position, department, contactInfo);
        this.payrollStaffID = payrollStaffID;
        this.payrollPeriod = payrollPeriod;
    }

    public PayrollStaff() {
        super();
    }

    public String getPayrollStaffID() {
        return payrollStaffID;
    }

    public void setPayrollStaffID(String payrollStaffID) {
        this.payrollStaffID = payrollStaffID;
    }

    public String getPayrollPeriod() {
        return payrollPeriod;
    }

    public void setPayrollPeriod(String payrollPeriod) {
        this.payrollPeriod = payrollPeriod;
    }
}
