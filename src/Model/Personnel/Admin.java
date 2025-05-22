/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Personnel;


import Model.Personnel.Role;

public class Admin extends Role {
    public Admin(String id, String name, boolean isAdmin, String description) {
        super(id, name, isAdmin, description);
    }

    public Admin() {
        super("ADMIN", "Administrator", true, "System Administrator Role"); // Added line
    }
}
