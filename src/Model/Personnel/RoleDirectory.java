// Model/personnel/RoleDirectory.java
package Model.Personnel;

import java.util.ArrayList;
import java.util.List;

public class RoleDirectory {
    private List<Role> roleList;

    public RoleDirectory() {
        this.roleList = new ArrayList<>();
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void addRole(Role role) {
        this.roleList.add(role);
    }

    public void removeRole(Role role) {
        this.roleList.remove(role);
    }

    public Role findRoleById(String id) {
        for (Role r : roleList) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    public Role findRoleByName(String name) {
        for (Role r : roleList) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }
}