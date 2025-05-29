package Model.Personnel;

import Model.Employee.Employee;
import Model.Role.SupplychainManagerRole;
import Model.Person.ContactInfo;

/**
 * Supply Officer role class
 * @author tiankaining
 */
public class SupplychainManager extends Employee {
    private String supplychainManagerID;

    public SupplychainManager(String supplychainManagerID,
                               String id, String name, String gender, int age, String dateOfBirth,
                               String position, String department, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, position, department, contactInfo);
        this.supplychainManagerID = supplychainManagerID;
    }

    public SupplychainManager() {
        super();
    }

    public String getSupplyOfficerID() {
        return supplychainManagerID;
    }

    public void setSupplyOfficerID(String supplyOfficerID) {
        this.supplychainManagerID = supplyOfficerID;
    }
}
