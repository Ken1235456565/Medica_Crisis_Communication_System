package Model.Personnel;

import Model.Personnel.Role;

public class LogisticsManagerRole extends Role {
    private String department;

    public LogisticsManagerRole() {
        super("LOGISTICSMANAGER", "Logistics Manager", false, "Manages logistics operations"); // Added line
    }

    public LogisticsManagerRole(String id, String name, boolean isAdmin, String description, String department) {
        super(id, name, isAdmin, description);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}