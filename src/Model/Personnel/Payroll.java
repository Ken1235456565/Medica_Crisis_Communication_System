// Model/personnel/roles/PayrollRole.java
package Model.Personnel;

import Model.Personnel.Role;

public class Payroll extends Role {
    private String department;
    private String payrollPeriod;

    public Payroll() {
        super("PAYROLL", "Payroll Specialist", false, "Manages employee payroll"); // Added line
    }

    public Payroll(String id, String name, boolean isAdmin, String description, String department, String payrollPeriod) {
        super(id, name, isAdmin, description);
        this.department = department;
        this.payrollPeriod = payrollPeriod;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPayrollPeriod() {
        return payrollPeriod;
    }

    public void setPayrollPeriod(String payrollPeriod) {
        this.payrollPeriod = payrollPeriod;
    }
}