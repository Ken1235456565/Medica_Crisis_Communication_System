/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Model/Employee/PayrollDirectory.java
package Model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PayrollDirectory {
    private List<PayrollRecord> payrollRecords;

    public PayrollDirectory() {
        this.payrollRecords = new ArrayList<>();
    }

    public List<PayrollRecord> getPayrollRecords() {
        return payrollRecords;
    }

    public void addPayrollRecord(PayrollRecord payrollRecord) {
        this.payrollRecords.add(payrollRecord);
    }

    public void removePayrollRecord(PayrollRecord payrollRecord) {
        this.payrollRecords.remove(payrollRecord);
    }

    /**
     * Finds a payroll record by the employee's ID.
     * @param employeeId The ID of the employee.
     * @return The PayrollRecord if found, null otherwise.
     */
    public PayrollRecord findPayrollRecordByEmployeeId(String employeeId) {
        for (PayrollRecord record : payrollRecords) {
            if (record.getEmployee().getId().equals(employeeId)) {
                return record;
            }
        }
        return null;
    }

    /**
     * Finds a payroll record by the employee's name.
     * @param employeeName The name of the employee.
     * @return The PayrollRecord if found, null otherwise.
     */
    public PayrollRecord findPayrollRecordByEmployeeName(String employeeName) {
        for (PayrollRecord record : payrollRecords) {
            if (record.getEmployee().getName().equals(employeeName)) {
                return record;
            }
        }
        return null;
    }

    /**
     * Processes payroll for all employees in the directory.
     * This is a simplified example; in a real system, this would involve
     * more complex calculations, tax deductions, and integration with financial systems.
     * For demonstration, it just records a payment.
     */
    public void processAllPayroll() {
        LocalDate today = LocalDate.now();
        System.out.println("Processing payroll for " + today);
        for (PayrollRecord record : payrollRecords) {
            double netSalary = record.calculateNetSalary();
            record.recordPayment(netSalary, today);
            System.out.println("Paid " + netSalary + " to " + record.getEmployee().getName());
        }
    }
}
