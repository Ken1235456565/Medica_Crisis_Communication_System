/*
 * Donor Class - Merged Version
 */
package Model.Personnel;

import Model.Person.Person;
import Model.Person.ContactInfo;
import Model.Supplies.Donation;
import Model.User.UserAccount;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a donor with donation history, type, and account association.
 * Supports recurring donor tracking.
 * @author tiank
 */
public class Donor extends Person {
    private String donorId;
    private UserAccount userAccount;
    private List<Donation> donationHistory;
    private double totalDonationAmount;
    private String donorType; // "Individual", "Corporate", "Foundation"
    private boolean isRecurringDonor;
    private static int counter = 1;

    // Default constructor
    public Donor() {
        super();
        this.donorId = "DNR" + counter++;
        this.donationHistory = new ArrayList<>();
        this.totalDonationAmount = 0.0;
        this.donorType = "Individual";
        this.isRecurringDonor = false;
    }

    // Constructor without userAccount
    public Donor(String name, String gender, int age, String dateOfBirth, ContactInfo contactInfo) {
        super(null, name, gender, age, dateOfBirth, contactInfo);
        this.donorId = "DNR" + counter++;
        this.donationHistory = new ArrayList<>();
        this.totalDonationAmount = 0.0;
        this.donorType = "Individual";
        this.isRecurringDonor = false;
    }

    // Constructor with userAccount
    public Donor(String name, String gender, int age, String dateOfBirth, ContactInfo contactInfo, UserAccount userAccount) {
        this(name, gender, age, dateOfBirth, contactInfo);
        this.userAccount = userAccount;
    }

    // Getters and setters
    public String getDonorId() { return donorId; }

    public List<Donation> getDonationHistory() { return donationHistory; }

    public double getTotalDonationAmount() { return totalDonationAmount; }

    public String getDonorType() { return donorType; }

    public void setDonorType(String donorType) { this.donorType = donorType; }

    public boolean isRecurringDonor() { return isRecurringDonor; }

    public void setRecurringDonor(boolean recurringDonor) { isRecurringDonor = recurringDonor; }

    public UserAccount getUserAccount() { return userAccount; }

    public void setUserAccount(UserAccount userAccount) { this.userAccount = userAccount; }

    // Add donation to history
    public void addDonation(Donation donation) {
        this.donationHistory.add(donation);
        this.totalDonationAmount += donation.getAmount();

        // Consider recurring if more than one donation
        if (donationHistory.size() > 1) {
            this.isRecurringDonor = true;
        }
    }

    @Override
    public String toString() {
        return "Donor: " + getName() + " (Total Donated: $" + totalDonationAmount + ")";
    }
}
