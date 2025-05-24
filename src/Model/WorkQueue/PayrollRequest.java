package Model.WorkQueue;

import Model.Employee.Employee;
import java.util.Date;

/**
 * Represents a request to process payroll for an employee.
 * This would be used by the OperationsSupportUnit.
 */
public class PayrollRequest extends WorkRequest { // Extends WorkRequest

    private Employee employee;
    private Date payPeriodStart;
    private Date payPeriodEnd;
    private Date paymentDate;
    private double hoursWorked;
    private double overtimeHours;
    private double bonus;
    private double deductions;
    private String paymentMethod;

    public PayrollRequest() {
        this.setStatus("Submitted");
        this.setMessage("Payroll Request");
    }

    public PayrollRequest(Employee employee, Date payPeriodStart, Date payPeriodEnd, Date paymentDate) {
        this();
        this.employee = employee;
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
        this.paymentDate = paymentDate;
        this.setMessage("Payroll for " + employee.getName() + " (" + payPeriodStart + " to " + payPeriodEnd + ")");
    }

    // Getters and setters

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getPayPeriodStart() {
        return payPeriodStart;
    }

    public void setPayPeriodStart(Date payPeriodStart) {
        this.payPeriodStart = payPeriodStart;
    }

    public Date getPayPeriodEnd() {
        return payPeriodEnd;
    }

    public void setPayPeriodEnd(Date payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Methods to process payroll

    public void processPayroll() {
        this.setStatus("Processed");
        this.setResolveDate(new Date());
    }

    public double calculateGrossPay() {
        //  Calculate gross pay (replace with actual logic)
        return 5000;
    }

    public double calculateNetPay() {
        // Calculate net pay (replace with actual logic)
        return 3500;
    }

    @Override
    public String toString() {
        return getMessage() + " - " + getStatus();
    }
}