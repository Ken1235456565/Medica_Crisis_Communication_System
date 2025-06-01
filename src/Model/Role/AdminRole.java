/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Role;

/**
 *
 * @author tiankaining
 */
public class AdminRole extends Role {

    public AdminRole() {
        super("ADMIN", "Administrator", true, "System Administrator Role");
    }
    
    public AdminRole(String id, String name, String desc, boolean isAdmin) {
        super(id, name, desc, isAdmin);
    }

   
    public String getRoleName() {
        return "Admin";
    }
}

