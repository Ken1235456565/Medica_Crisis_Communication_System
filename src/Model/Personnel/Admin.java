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

    public Admin(String name, String username, String password,
                 String position, String department, ContactInfo contactInfo) {
        super(name, username, password, new AdminRole(), position, department);
        this.setContactInfo(contactInfo);
        this.setPayrollRecord(null);  // Admin 默认无工资记录
    }

    public Admin(String id, String name, String gender, int age, String dob,
                String username, String password, String organization, ContactInfo contactInfo) {
       super(name, username, password, new AdminRole(), organization, contactInfo,
             "Administrator", "Admin Department");
       this.setId(id != null ? id : generateEmployeeId());
   }


    public Admin() {
        super();
        this.setRole(new AdminRole());
        this.setPosition("Administrator");
        this.setDepartment("Admin Department");
    }
}

