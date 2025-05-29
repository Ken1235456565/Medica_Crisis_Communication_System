package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.Role.LogisticsManagerRole;

public class LogisticsManager extends Employee {
    private String logisticsManagerID;

    public LogisticsManager(String logisticsManagerID,
                            String id, String name, String gender, int age, String dateOfBirth,
                            String position, String department, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, position, department, contactInfo);
        this.logisticsManagerID = logisticsManagerID;
    }

    public LogisticsManager() {
        super();
    }

    public String getLogisticsManagerID() {
        return logisticsManagerID;
    }

    public void setLogisticsManagerID(String logisticsManagerID) {
        this.logisticsManagerID = logisticsManagerID;
    }
}
