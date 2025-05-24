/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Model/Employee/Payment.java (New Class)
package Model.Employee;

import java.time.LocalDate;

/**
 * Represents a single payment made to an employee.
 */
public class Payment {
    private double amount;
    private LocalDate paymentDate;

    public Payment(double amount, LocalDate paymentDate) {
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + ", Date: " + paymentDate;
    }
}
