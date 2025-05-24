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

    public Admin(String name, String username, String password, String department, String designation, ContactInfo contactInfo) {
        super(name, username, password, new AdminRole(), department, designation);
        this.setContactInfo(contactInfo);
    }

    // 可选无参构造器
    public Admin() {
        super();
    }
}
