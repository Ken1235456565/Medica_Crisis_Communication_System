// Model/personnel/roles/PayrollRole.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Role.PayrollStaffRole;
import Model.User.UserAccount;

public class PayrollStaff extends UserAccount {
    private String payrollStaffID;
    private String payrollPeriod;

    public PayrollStaff(String payrollStaffID, String payrollPeriod,
                        String id, String name, String gender, int age, String dateOfBirth,
                        String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new PayrollStaffRole(), organization, contactInfo);
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
