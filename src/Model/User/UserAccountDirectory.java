// Model/user/UserAccountDirectory.java
package Model.User;

import Model.Employee.Employee;
import Model.Role.Role;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDirectory {
    private List<UserAccount> userAccountList;

    public UserAccountDirectory() {
        this.userAccountList = new ArrayList<>();
    }

    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public void addUserAccount(UserAccount userAccount) {
        this.userAccountList.add(userAccount);
    }

    public void removeUserAccount(UserAccount userAccount) {
        this.userAccountList.remove(userAccount);
    }

    public UserAccount findUserAccountByUsername(String username) {
        for (UserAccount ua : userAccountList) {
            if (ua.getUsername().equals(username))
                return ua;
        }
        return null;
    }

    public UserAccount createUserAccount(String username, String password, Employee employee, Role role) {
        // Organization can be null at creation
        UserAccount userAccount = new UserAccount(username, password, role, null, employee);
        userAccountList.add(userAccount);
        return userAccount;
    }
    
    public UserAccount authenticateUser(String username, String password) {
        for (UserAccount ua : userAccountList) {
            if (ua.getUsername().equals(username) && ua.getPassword().equals(password)) {
                return ua;  // 登录成功
            }
        }
        return null; // 登录失败
    }
    
    public List<Employee> findEmployeesByRole(String roleName) {
        List<Employee> result = new ArrayList<>();
        for (UserAccount ua : userAccountList) {
            if (ua.getRole() != null &&
                ua.getRole().getName().equals(roleName) &&
                ua.getEmployee() != null) {
                result.add(ua.getEmployee());
            }
        }
        return result;
    }

}