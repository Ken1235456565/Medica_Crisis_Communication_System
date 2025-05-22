package Model.Supplies;

import Model.Personnel.Donor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a donation (either monetary or items).
 */
public class Donation {

    private String donationId;
    private Donor donor;      // Link to the Donor
    private Date donationDate;
    private double amount;    // For monetary donations
    private List<DonatedItem> donatedItems; // For item donations
    private String purpose;   // Why the donation was made
    private String notes;
    private String status;    // "Received", "Processed", "Distributed"

    private static int count = 1;

    public Donation() {
        this.donationId = "DON" + count++;
        this.donationDate = new Date();
        this.status = "Received";
    }

    // Constructor for monetary donation
    public Donation(Donor donor, double amount, String purpose) {
        this();
        this.donor = donor;
        this.amount = amount;
        this.purpose = purpose;
    }

    // Constructor for item donation
    public Donation(Donor donor, List<DonatedItem> donatedItems, String purpose) {
        this();
        this.donor = donor;
        this.donatedItems = donatedItems;
        this.purpose = purpose;
    }

    // Getters and setters

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<DonatedItem> getDonatedItems() {
        return donatedItems;
    }

    public void setDonatedItems(List<DonatedItem> donatedItems) {
        this.donatedItems = donatedItems;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Donation-specific methods

    public double getTotalValue() {
        if (amount > 0) {
            return amount;
        } else if (donatedItems != null) {
            double total = 0;
            for (DonatedItem item : donatedItems) {
                total += item.getQuantity() * item.getUnitPrice();
            }
            return total;
        } else {
            return 0;
        }
    }

    public void processDonation() {
        this.status = "Processed";
    }

    public void distributeDonation() {
        this.status = "Distributed";
    }

    public void sendAcknowledgement() {
        //  Send an acknowledgement to the donor (implementation depends on your system)
        System.out.println("Acknowledgement sent to " + donor.getName() + " for donation " + donationId);
    }

    @Override
    public String toString() {
        String donationInfo = (amount > 0) ? "Amount: $" + amount : "Items: " + donatedItems.size();
        return "Donation " + donationId + " from " + donor.getName() + " - " + donationInfo + " [" + status + "]";
    }

    // Inner class for donated items
    public static class DonatedItem {

        private String name;
        private int quantity;
        private double unitPrice;

        public DonatedItem(String name, int quantity, double unitPrice) {
            this.name = name;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getTotalItemValue() {
            return quantity * unitPrice;
        }

        @Override
        public String toString() {
            return name + " (Qty: " + quantity + ", Price: $" + unitPrice + ")";
        }
    }
}