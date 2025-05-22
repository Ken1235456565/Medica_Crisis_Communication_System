// Model/personnel/roles/ManagerRole.java
package Model.Personnel;
import Model.Personnel.Role;

public class Manager extends Role {
    public Manager() {
        super("MANAGER", "Manager", false, "General Manager Role"); // Added line
    }

    public Manager(String id, String name, boolean isAdmin, String description) {
        super(id, name, isAdmin, description);
    }
}