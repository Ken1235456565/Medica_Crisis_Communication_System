// Model/personnel/roles/DonationCoordinatorRole.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Person.Person;


public class DonationCoordinator extends Person {
    private String region;

    public DonationCoordinator(String id, String name, String gender, int age, String dateOfBirth,
                               String region, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, contactInfo);
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return getName() + " (" + region + ")";
    }
}

