/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Role.AdminRole;
import Model.User.UserAccount;


public class Admin extends UserAccount {

    public Admin(String id, String name, String gender, int age, String dateOfBirth,
                 String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, username, password,
              new AdminRole(),
              organization, contactInfo);
    }

    public Admin() {
        super(); // 你可以保留这个无参构造器（如果 UserAccount 有的话）
    }
}
