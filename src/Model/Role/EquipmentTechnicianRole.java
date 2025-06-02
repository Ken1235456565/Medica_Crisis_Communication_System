/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Role;

/**
 *
 * @author tiankaining
 */
public class EquipmentTechnicianRole extends Role {

    public EquipmentTechnicianRole() {
        super("EQUIP_TECH", "Equipment Technician", false, "Equipment Technician Role");
    }
    
    public EquipmentTechnicianRole(String id, String name, String desc, boolean isAdmin) {
        super(id, name, desc, isAdmin);
    }

    public String getRoleName() {
        return "Equipment Technician";
    }
}
