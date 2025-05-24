// Model/personnel/roles/DonationCoordinatorRole.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Role.DonationCoordinatorRole;
import Model.User.UserAccount;

public class DonationCoordinator extends UserAccount {
    private String region;

    public DonationCoordinator(String region,
                               String id, String name, String gender, int age, String dateOfBirth,
                               String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new DonationCoordinatorRole(), organization, contactInfo);
        this.region = region;
    }

    public DonationCoordinator() {
        super();
    }

    // Getter and Setter
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
