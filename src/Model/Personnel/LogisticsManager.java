package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Role.LogisticsManagerRole;
import Model.User.UserAccount;

public class LogisticsManager extends UserAccount {
    private String logisticsManagerID;

    public LogisticsManager(String logisticsManagerID,
                            String id, String name, String gender, int age, String dateOfBirth,
                            String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new LogisticsManagerRole(), organization, contactInfo);
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
