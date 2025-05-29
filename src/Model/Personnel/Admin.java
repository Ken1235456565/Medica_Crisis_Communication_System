/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.Role.AdminRole;
import Model.User.UserAccount;


public class Admin extends Employee {

    public Admin(String name, String gender, int age, String dob,
                 String position, String department, ContactInfo contactInfo) {
        super(null, name, gender, age, dob, position, department, contactInfo);
    }

    public Admin(String id, String name, String gender, int age, String dob,
                 String position, String department, ContactInfo contactInfo) {
        super(id, name, gender, age, dob, position, department, contactInfo);
    }

    public Admin() {
        super(); // default constructor of Employee
    }
}


