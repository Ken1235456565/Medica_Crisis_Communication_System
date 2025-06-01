package Model.Supplies;

import Model.Personnel.Donor;
import java.util.Date;
import java.util.List;

/**
 * Represents a donation made by a donor, either monetary or item-based.
 * Now uses external DonatedItem class for modularity.
 */
public class Donation {

    private String donationId;
    private Donor donor;
    private Date donationDate;
    private double monetaryAmount;
    private List<DonatedItem> donatedItems;
    private String purpose;
    private String notes;
    private String status; // "Received", "Processed", "Distributed"

    private static int counter = 1;

    // ========== Constructors ==========

    public Donation(Donor donor, double monetaryAmount, String purpose) {
        this.donationId = "DON" + counter++;
        this.donor = donor;
        this.monetaryAmount = monetaryAmount;
        this.purpose = purpose;
        this.donationDate = new Date();
        this.status = "Received";
        this.donatedItems = null;
    }

    public Donation(Donor donor, List<DonatedItem> donatedItems, String purpose) {
        this.donationId = "DON" + counter++;
        this.donor = donor;
        this.donatedItems = donatedItems;
        this.purpose = purpose;
        this.donationDate = new Date();
        this.status = "Received";
        this.monetaryAmount = 0.0;
    }

    // ========== Getters & Setters ==========

    public String getDonationId() { return donationId; }

    public Donor getDonor() { return donor; }

    public void setDonor(Donor donor) { this.donor = donor; }

    public Date getDonationDate() { return donationDate; }

    public void setDonationDate(Date donationDate) { this.donationDate = donationDate; }

    public double getAmount() { return monetaryAmount; }

    public void setAmount(double monetaryAmount) { this.monetaryAmount = monetaryAmount; }

    public List<DonatedItem> getDonatedItems() { return donatedItems; }

    public void setDonatedItems(List<DonatedItem> donatedItems) { this.donatedItems = donatedItems; }

    public String getPurpose() { return purpose; }

    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    // ========== Business Logic ==========

    public double getTotalValue() {
        if (monetaryAmount > 0) {
            return monetaryAmount;
        } else if (donatedItems != null) {
            return donatedItems.stream()
                               .mapToDouble(DonatedItem::getTotalItemValue)
                               .sum();
        }
        return 0.0;
    }

    public void processDonation() {
        this.status = "Processed";
    }

    public void distributeDonation() {
        this.status = "Distributed";
    }

    public void sendAcknowledgement() {
        System.out.println("Acknowledgement sent to " + donor.getName() + " for donation " + donationId);
    }

    @Override
    public String toString() {
        String summary = (monetaryAmount > 0)
            ? "Monetary Amount: $" + monetaryAmount
            : (donatedItems == null ? "No Items" : "Items: " + donatedItems.size());
        return "Donation " + donationId + " from " + donor.getName() + " - " + summary + " [" + status + "]";
    }
}
