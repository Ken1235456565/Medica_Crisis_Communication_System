package Model.Supplies;

import Model.Personnel.Donor;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of Donation objects.
 */
public class DonationCatalog {

    private List<Donation> donationList;

    public DonationCatalog() {
        this.donationList = new ArrayList<>();
    }

    public List<Donation> getDonationList() {
        return donationList;
    }

    public void setDonationList(List<Donation> donationList) {
        this.donationList = donationList;
    }

    public void addDonation(Donation donation) {
        this.donationList.add(donation);
    }

    public void removeDonation(Donation donation) {
        this.donationList.remove(donation);
    }

    public Donation findDonationById(String id) {
        for (Donation donation : donationList) {
            if (donation.getDonationId().equals(id)) {
                return donation;
            }
        }
        return null;
    }

    public List<Donation> findDonationsByDonor(Donor donor) {
        List<Donation> results = new ArrayList<>();
        for (Donation donation : donationList) {
            if (donation.getDonor().equals(donor)) {
                results.add(donation);
            }
        }
        return results;
    }
}