// Model/donation/Donor.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Person.Person;
import Model.Role.VisitorRole;
import Model.Supplies.Donation;
import Model.User.UserAccount;
import java.util.ArrayList;
import java.util.List;

public class Donor extends Person {
    private String donorId;
    private UserAccount userAccount;
    private List<Donation> donationHistory = new ArrayList<>();

    public Donor(String id, String name, String gender, int age, String dateOfBirth, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, contactInfo);
        this.donorId = "DON" + personIdCounter++;
    }
    
    public Donor(String id, String name, String gender, int age, String dateOfBirth,
                ContactInfo contactInfo, UserAccount userAccount) {
       this(id, name, gender, age, dateOfBirth, contactInfo);
       this.userAccount = userAccount;
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
    
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    
    public double getTotalDonationAmount() {
        return donationHistory.stream()
               .mapToDouble(Donation::getAmount)
               .sum();
    }
}