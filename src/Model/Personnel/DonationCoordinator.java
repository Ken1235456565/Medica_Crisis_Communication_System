// Model/personnel/roles/DonationCoordinatorRole.java
package Model.Personnel;

import Model.Personnel.Role;

public class DonationCoordinator extends Role {
    private String region;

    public DonationCoordinator() {
        super("DONATIONCOORD", "Donation Coordinator", false, "Coordinates donation activities"); // Added line
    }

    public DonationCoordinator(String id, String name, boolean isAdmin, String description, String region) {
        super(id, name, isAdmin, description);
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}