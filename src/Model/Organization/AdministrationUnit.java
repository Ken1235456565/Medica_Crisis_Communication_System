// Model/organization/AdministrationUnit.java
package Model.Organization;

import Model.Personnel.Employee;
import Model.Person.Person;
import Model.Role.Role;

import Model.User.UserAccount;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Administration Unit for system and organization management
 * @author tiankaining
 */
public class AdministrationUnit extends Organization { // EXTENDS Organization
    private List<UserAccount> userAccounts;
    private List<Role> definedRoles;
    private List<Organization> managedOrganizations;
    private Map<String, String> auditLogs; // 用户操作记录
    private boolean systemActive;

    // Default constructor
    public AdministrationUnit() {
        super("Administration");
        this.userAccounts = new ArrayList<>();
        this.definedRoles = new ArrayList<>();
        this.managedOrganizations = new ArrayList<>();
        this.auditLogs = new HashMap<>();
        this.systemActive = true;
    }

    // Constructor with admin
    public AdministrationUnit(String unitName, Employee admin) { // Modified to accept unit name
        super(unitName, admin);
        this.userAccounts = new ArrayList<>();
        this.definedRoles = new ArrayList<>();
        this.managedOrganizations = new ArrayList<>();
        this.auditLogs = new HashMap<>();
        this.systemActive = true;
    }

    // Getters and setters
    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public List<Role> getDefinedRoles() {
        return definedRoles;
    }

    public void setDefinedRoles(List<Role> definedRoles) {
        this.definedRoles = definedRoles;
    }

    public List<Organization> getManagedOrganizations() {
        return managedOrganizations;
    }

    public void setManagedOrganizations(List<Organization> managedOrganizations) {
        this.managedOrganizations = managedOrganizations;
    }

    public Map<String, String> getAuditLogs() {
        return auditLogs;
    }

    public void setAuditLogs(Map<String, String> auditLogs) {
        this.auditLogs = auditLogs;
    }

    public boolean isSystemActive() {
        return systemActive;
    }

    public void setSystemActive(boolean systemActive) {
        this.systemActive = systemActive;
    }

    // Create user account
    public UserAccount createUserAccount(String username, String password, Person person, Role role) {
        UserAccount userAccount = new UserAccount(username, password, role, null, person.getContactInfo()); // Pass contact info from person

        // Set basic details from person (these are now handled by UserAccount extending Person)
        userAccount.setId(person.getId());
        userAccount.setName(person.getName());
        userAccount.setGender(person.getGender());
        userAccount.setAge(person.getAge());
        userAccount.setDateOfBirth(person.getDateOfBirth());

        userAccounts.add(userAccount);

        // Log account creation
        logAction(userAccount.getId(), "User account created with role: " + role.getName());

        return userAccount;
    }

    // Find user account by username
    public UserAccount findUserAccountByUsername(String username) {
        for (UserAccount account : userAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    // Find user account by ID
    public UserAccount findUserAccountById(String id) {
        for (UserAccount account : userAccounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    // Add role to system
    public void addRole(Role role) {
        definedRoles.add(role);
    }

    // Find role by name
    public Role findRoleByName(String roleName) {
        for (Role role : definedRoles) {
            if (role.getName().equals(roleName)) {
                return role;
            }
        }
        return null;
    }

    // Add organization to managed list
    public void addOrganization(Organization organization) {
        managedOrganizations.add(organization);
    }

    // Find organization by name
    public Organization findOrganizationByName(String orgName) {
        for (Organization org : managedOrganizations) {
            if (org.getOrganizationName().equals(orgName)) {
                return org;
            }
        }
        return null;
    }

    // Find organization by ID
    public Organization findOrganizationById(String orgId) {
        for (Organization org : managedOrganizations) {
            if (org.getOrganizationId().equals(orgId)) {
                return org;
            }
        }
        return null;
    }

    // Log system action
    public void logAction(String userId, String action) {
        String timestamp = new Date().toString();
        String logEntry = timestamp + " - User [" + userId + "]: " + action;
        auditLogs.put(timestamp, logEntry);
    }

    // Authenticate user
    public UserAccount authenticateUser(String username, String password) {
        for (UserAccount account : userAccounts) {
            if (account.getUsername().equals(username) &&
                account.getPassword().equals(password)) {

                // Log successful login
                logAction(account.getId(), "Logged in");
                return account;
            }
        }

        // Log failed login attempt
        logAction("unknown", "Failed login attempt for username: " + username);
        return null;
    }

    // Check if username is unique
    public boolean isUsernameUnique(String username) {
        for (UserAccount account : userAccounts) {
            if (account.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    // Assign user to organization
    public boolean assignUserToOrganization(UserAccount user, Organization organization) {
        // First check if the user exists
        if (!userAccounts.contains(user)) {
            return false;
        }

        // Then check if the organization exists
        if (!managedOrganizations.contains(organization)) {
            return false;
        }

        // If user is an employee, add to organization
        if (user instanceof Employee) {
            organization.addEmployee((Employee) user);

            // Log assignment
            logAction(user.getId(), "Assigned to organization: " + organization.getOrganizationName());
            return true;
        }

        return false;
    }

    // Create and assign a new organization
    public Organization createOrganization(String orgName, String orgType, Employee admin) {
        Organization newOrg = null;

        // Create the appropriate organization type
        switch (orgType) {
            case "Clinical":
                newOrg = new ClinicalServicesUnit(orgName, admin, false);
                break;
            case "Dispatch":
                newOrg = new ResourceDispatchUnit(orgName, admin);
                break;
            case "Inventory":
                newOrg = new InventoryManager(orgName); // InventoryManager constructor doesn't take admin directly
                break;
            case "Operations":
                newOrg = new OperationsSupportUnit(orgName, admin, "2025");
                break;
            case "Donation":
                newOrg = new DonationManagementUnit(admin);
                break;
            case "Administration":
                newOrg = new AdministrationUnit(orgName, admin); // Pass orgName and admin
                break;
            case "EmergencyResponse": // Added EmergencyResponse
                newOrg = new EmergencyResponseUnit(orgName, admin);
                break;
            default:
                return null;
        }

        // If created successfully, add to managed organizations
        if (newOrg != null) {
            addOrganization(newOrg);

            // Log creation
            logAction(admin.getId(), "Created new organization: " + orgName + " (" + orgType + ")");
        }

        return newOrg;
    }

    // Get system statistics
    public Map<String, Object> getSystemStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // Count users by role
        Map<String, Integer> usersByRole = new HashMap<>();
        for (UserAccount user : userAccounts) {
            String roleName = user.getRole().getName();
            usersByRole.put(roleName, usersByRole.getOrDefault(roleName, 0) + 1);
        }

        // Count organizations by type
        Map<String, Integer> orgsByType = new HashMap<>();
        for (Organization org : managedOrganizations) {
            String orgType = org.getClass().getSimpleName();
            orgsByType.put(orgType, orgsByType.getOrDefault(orgType, 0) + 1);
        }

        // Add counts to stats
        stats.put("totalUsers", userAccounts.size());
        stats.put("totalOrganizations", managedOrganizations.size());
        stats.put("totalRoles", definedRoles.size());
        stats.put("usersByRole", usersByRole);
        stats.put("organizationsByType", orgsByType);
        stats.put("systemActive", systemActive);

        return stats;
    }

    // Reset user password
    public boolean resetUserPassword(String username, String newPassword) {
        UserAccount user = findUserAccountByUsername(username);
        if (user == null) {
            return false;
        }

        user.setPassword(newPassword);

        // Log password reset
        logAction(user.getId(), "Password reset");
        return true;
    }

    // Deactivate user account
    public boolean deactivateUserAccount(String username) {
        UserAccount user = findUserAccountByUsername(username);
        if (user == null) {
            return false;
        }

        // If user is an employee, mark as inactive
        if (user instanceof Employee) {
            ((Employee) user).setActive(false);
        }

        // Log deactivation
        logAction(user.getId(), "Account deactivated");
        return true;
    }

    @Override
    public String toString() {
        return "Administration Unit [Users: " + userAccounts.size() +
               ", Organizations: " + managedOrganizations.size() +
               ", System Active: " + (systemActive ? "Yes" : "No") + "]";
    }
}