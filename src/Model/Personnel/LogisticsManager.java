package Model.Personnel;

import Model.Role.Role;

public class LogisticsManager extends Role {
    private String department;

    public LogisticsManager() {
        super("LOGISTICSMANAGER", "Logistics Manager", false, "Manages logistics operations"); // Added line
    }

    public LogisticsManager(String id, String name, boolean isAdmin, String description, String department) {
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