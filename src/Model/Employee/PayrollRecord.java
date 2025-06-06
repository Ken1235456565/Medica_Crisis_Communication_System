/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Model/Employee/PayrollRecord.java
// Model/Employee/PayrollRecord.java
package Model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PayrollRecord {
    private Employee employee;
    private double baseSalary;
    private double bonus;
    private double deductions;
    private List<Payment> paymentHistory; // To store a history of payments

    public PayrollRecord(Employee employee) {
        this.employee = employee;
        this.baseSalary = 0.0;
        this.bonus = 0.0;
        this.deductions = 0.0;
        this.paymentHistory = new ArrayList<>();
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
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

    public List<Payment> getPaymentHistory() {
        return paymentHistory;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    
    /**
     * Calculates the net salary for the current payroll period.
     * This is a simplified calculation; real-world scenarios would be more complex.
     * @return The net salary.
     */
    public double calculateNetSalary() {
        return baseSalary + bonus - deductions;
    }

    /**
     * Records a payment made to the employee.
     * @param amount The amount paid.
     * @param paymentDate The date of the payment.
     */
    public void recordPayment(double amount, LocalDate paymentDate) {
        this.paymentHistory.add(new Payment(amount, paymentDate));
    }

    @Override
    public String toString() {
        return "Payroll for " + employee.getName() +
               ": Base Salary=" + baseSalary +
               ", Bonus=" + bonus +
               ", Deductions=" + deductions +
               ", Net Salary=" + calculateNetSalary();
    }
}
