// Model/Organization/Organization.java
package Model.Organization;

import Model.Employee.Employee;
import Model.WorkQueue.WorkQueue;
import Model.Person.ContactInfo;
import Model.Personnel.Admin;
import Model.Employee.EmployeeDirectory;
import Model.User.UserAccountDirectory;
import Model.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Base abstract class for all organization types in the system
 * @author tiankaining
 */
public abstract class Organization {
    protected String organizationId;
    protected String organizationName;
    protected EmployeeDirectory employeeDirectory;
    protected WorkQueue workQueue;
    protected ContactInfo contactInfo;
    private UserAccountDirectory UserAccountDirectory;
    private Admin admin;
    
    // Static ID counter
    private static int counter = 1;

    // Define Organization Type enum
    public enum Type {
        ClinicalServices("Clinical Services Unit"),
        Administration("Administration Unit"),
        ResourceDispatch("Resource Dispatch Unit"),
        EmergencyResponse("Emergency Response Unit"),
        InventoryManagement("Inventory Management Unit"),
        DonationManagement("Donation Management Unit"),
        OperationsSupport("Operations Support Unit");

        private String value;

        private Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }


    // Default constructor
    public Organization() {
        this.organizationId = generateOrgId();
        this.employeeDirectory = new EmployeeDirectory(); // Initialize EmployeeDirectory
        this.workQueue = new WorkQueue();
        this.contactInfo = new ContactInfo();
    }

    // Constructor with name
    public Organization(String organizationName) {
        this();
        this.organizationName = organizationName;
    }

    // Constructor with name and admin
    public Organization(String organizationName, Admin admin) {
        this(organizationName);
        this.admin = admin;
    }

    // Generate a unique org ID
    private String generateOrgId() {
        return "ORG" + counter++;
    }

    // Getters and setters
    public String getOrganizationId() { return organizationId; }
    public void setOrganizationId(String organizationId) { this.organizationId = organizationId; }
    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }
    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }
    public EmployeeDirectory getEmployeeDirectory() { return employeeDirectory; }
    public List<WorkRequest> getWorkQueue() { return workQueue.getWorkRequestList(); }
    public ContactInfo getContactInfo() { return contactInfo; }
    public void setContactInfo(ContactInfo contactInfo) { this.contactInfo = contactInfo; }

    // Add an employee to the organization
    public void addEmployee(Employee employee) {
        employeeDirectory.addEmployee(employee);
    }

    // Remove an employee from the organization
    public void removeEmployee(Employee employee) {
        employeeDirectory.removeEmployee(employee);
    }

    // Add a work request to the queue
    public void addWorkRequest(WorkRequest request) {
        workQueue.addWorkRequest(request);
    }

    // Complete a work request
    public void completeWorkRequest(WorkRequest request) {
        for (WorkRequest wr : workQueue.getWorkRequestList()) {
            if (wr.equals(request)) {
                wr.setStatus("Completed");
                break;
            }
        }
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return UserAccountDirectory;
    }

    public void setUserAccountDirectory(UserAccountDirectory UserAccountDirectory) {
        this.UserAccountDirectory = UserAccountDirectory;
    }
    
    

    // Find employees by role
    public List<Employee> findEmployeesByRole(String roleName) {
        return employeeDirectory.findEmployeesByRole(roleName);
    }

    // Get pending work requests
    public List<WorkRequest> getPendingWorkRequests() {
        List<WorkRequest> result = new ArrayList<>();
        for (WorkRequest request : workQueue.getWorkRequestList()) {
            if (request.getStatus().equals("Pending")) {
                result.add(request);
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return organizationName;
    }
}