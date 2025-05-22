// Model/personnel/Role.java
package Model.Personnel;

public abstract class Role {
    private String id;
    private String name;
    private boolean isAdmin;
    private String description;

    public Role() {
    }

    public Role(String id, String name, boolean isAdmin, String description) {
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}