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

    public SupplychainManager(String supplyOfficerID,
                         String id, String name, String gender, int age, String dateOfBirth,
                         String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new SupplychainManagerRole(), organization, contactInfo);
        this.supplychainManagerID = supplyOfficerID;
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
