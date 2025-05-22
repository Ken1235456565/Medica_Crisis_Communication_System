//package Model.Personnel;
//
//import Model.Role.Role;
//
///**
// * Supply Officer role class
// * @author tiankaining
// */
//public class SupplyOfficerRole extends Role {
//    
//    private String department;
//    
//    // Default constructor
//    public SupplyOfficerRole() {
//        super("SUPPLY", "Supply Officer", false, "Manages supply inventory and distribution", "Supply", "Support");
//    }
//    
//    // Constructor with department
//    public SupplyOfficerRole(String department) {
//        super("SUPPLY", "Supply Officer", false, "Manages supply inventory for " + department, "Supply", "Support");
//        this.department = department;
//    }
//    
//    // Constructor with organization and enterprise
//    public SupplyOfficerRole(String department, String organization, String enterprise) {
//        super("SUPPLY", "Supply Officer", false, "Manages supply inventory for " + department, organization, enterprise);
//        this.department = department;
//    }
//    
//    // Getters and setters
//    public String getDepartment() {
//        return department;
//    }
//    
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//    
//    @Override
//    public String toString() {
//        return department != null ? "Supply Officer - " + department : "Supply Officer";
//    }
//}