// Model/donation/Donor.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Role.VisitorRole;
import Model.Supplies.Donation;
import Model.User.UserAccount;
import java.util.ArrayList;
import java.util.List;

public class Donor extends UserAccount {
    private String donorId;
    private List<Donation> donationHistory;

    public Donor(String donorId,
                 String id, String name, String gender, int age, String dateOfBirth,
                 String username, String password, String organization, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth,
              username, password,
              new VisitorRole(), 
              organization, contactInfo);
        this.donorId = donorId;
        this.donationHistory = new ArrayList<>();
    }

    public Donor() {
        super();
        this.donationHistory = new ArrayList<>();
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Donation> getDonationHistory() {
        return donationHistory;
    }

    public void setDonationHistory(List<Donation> donationHistory) {
        this.donationHistory = donationHistory;
    }

    public void addDonation(Donation donation) {
        this.donationHistory.add(donation);
    }
    
    public double getTotalDonationAmount() {
        return donationHistory.stream()
               .mapToDouble(Donation::getAmount)
               .sum();
    }
}