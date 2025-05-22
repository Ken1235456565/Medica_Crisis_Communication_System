// Model/donation/Donor.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Supplies.Donation;
import java.util.ArrayList;
import java.util.List;

public class Donor {
    private String donorId;
    private String name;
    private ContactInfo contactInfo;
    private List<Donation> donationHistory;

    public Donor() {
        this.donationHistory = new ArrayList<>();
    }

    public Donor(String donorId, String name, ContactInfo contactInfo) {
        this();
        this.donorId = donorId;
        this.name = name;
        this.contactInfo = contactInfo;
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

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
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