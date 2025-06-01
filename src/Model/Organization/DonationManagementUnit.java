package Model.Organization;

import Model.Employee.Employee;
import Model.Personnel.Admin;
import Model.Personnel.Donor;
import Model.Supplies.DonatedItem;
import Model.Supplies.Donation;
import Model.Supplies.DonationCatalog;
import Model.User.UserAccount;
import Model.WorkQueue.DonationRequest;
import Model.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Donation Management Unit for handling donations
 * @author tiankaining
 */
public class DonationManagementUnit extends Organization {
    private DonationCatalog donationRecords;
    private List<Donor> donorList;
    private Map<String, Integer> donationInventory; // 物资名称 -> 数量
    private List<WorkRequest> donationRequests; // 来自组织的需求
    private Date lastDonationEventDate;
    private double totalFundsRaised;
    
    // Default constructor
    public DonationManagementUnit() {
        super("Donation Management");
        this.donationRecords = new DonationCatalog();  // ✅ FIXED
        this.donorList = new ArrayList<>();
        this.donationInventory = new HashMap<>();
        this.donationRequests = new ArrayList<>();
        this.totalFundsRaised = 0.0;
    }
    
    // Constructor with admin
    public DonationManagementUnit(Admin admin) {
        super("Donation Management", admin);
        this.donationRecords = new DonationCatalog();  // ✅ FIXED
        this.donorList = new ArrayList<>();
        this.donationInventory = new HashMap<>();
        this.donationRequests = new ArrayList<>();
        this.totalFundsRaised = 0.0;
    }
    
    // Getters and setters

    public DonationCatalog getDonationRecords() {
        return donationRecords;
    }

    public void setDonationRecords(DonationCatalog donationRecords) {
        this.donationRecords = donationRecords;
    }
    
    
    public List<Donor> getDonorList() {
        return donorList;
    }
    
    public Map<String, Integer> getDonationInventory() {
        return donationInventory;
    }
    
    public List<WorkRequest> getDonationRequests() {
        return donationRequests;
    }
    
    public Date getLastDonationEventDate() {
        return lastDonationEventDate;
    }
    
    public void setLastDonationEventDate(Date lastDonationEventDate) {
        this.lastDonationEventDate = lastDonationEventDate;
    }
    
    public double getTotalFundsRaised() {
        return totalFundsRaised;
    }
    
    
    // Add donor to list
    public void addDonor(Donor donor) {
        donorList.add(donor);
    }
    
    // Find donor by name
    public Donor findDonorByName(String name) {
        for (Donor donor : donorList) {
            if (donor.getName().equals(name)) {
                return donor;
            }
        }
        return null;
    }
    
    // Find donor by ID
    public Donor findDonorById(String donorId) {
        for (Donor donor : donorList) {
            if (donor.getDonorId().equals(donorId)) {
                return donor;
            }
        }
        return null;
    }
    
    // Record monetary donation
    public Donation recordMonetaryDonation(Donor donor, double amount, String purpose) {
        Donation donation = new Donation(donor, amount, purpose);
        donationRecords.addDonation(donation);
        
        // Update donor's donation history
        donor.addDonation(donation);
        
        // Update total funds raised
        totalFundsRaised += amount;
        
        return donation;
    }
    
    // Record item donation
    public Donation recordItemDonation(Donor donor, List<DonatedItem> items, String purpose) {
        Donation donation = new Donation(donor, items, purpose);
        donationRecords.addDonation(donation);
        
        // Update donor's donation history
        donor.addDonation(donation);
        
        // Update donation inventory
        for (DonatedItem item : items) {
            String itemName = item.getName();
            int quantity = item.getQuantity();
            
            // Add to inventory
            int currentQuantity = donationInventory.getOrDefault(itemName, 0);
            donationInventory.put(itemName, currentQuantity + quantity);
        }
        
        return donation;
    }
    
    // Process donations
    public void processDonations(List<Donation> donations) {
        for (Donation donation : donations) {
            donation.processDonation();
        }
    }
    
    // Distribute donations
    public void distributeDonations(List<Donation> donations) {
        for (Donation donation : donations) {
            donation.distributeDonation();
        }
    }
    
    // Get item quantity from inventory
    public int getItemQuantity(String itemName) {
        return donationInventory.getOrDefault(itemName, 0);
    }
    
    // Check if item is available in inventory
    public boolean isItemAvailable(String itemName, int requiredQuantity) {
        int available = getItemQuantity(itemName);
        return available >= requiredQuantity;
    }
    
    // Withdraw items from inventory
    public boolean withdrawItems(String itemName, int quantity) {
        if (!isItemAvailable(itemName, quantity)) {
            return false;
        }
        
        int currentQuantity = donationInventory.get(itemName);
        donationInventory.put(itemName, currentQuantity - quantity);
        
        return true;
    }
    
    // Add donation request
    public void addDonationRequest(WorkRequest request) {
        donationRequests.add(request);
        addWorkRequest(request); // Also add to general work queue
    }
    
    // Create donation request
    public DonationRequest createDonationRequest(String requestType, List<Donation> donations, UserAccount requestedBy) { // Updated parameters
    DonationRequest request = new DonationRequest(requestType, donations); // Correctly instantiating DonationRequest
    request.setSender(requestedBy); // Set who sent the request
    
    addDonationRequest(request); //
    return request; //
}
    
    // Get active donation requests
    public List<WorkRequest> getActiveDonationRequests() {
        List<WorkRequest> activeRequests = new ArrayList<>();
        for (WorkRequest request : donationRequests) {
            if (request.getStatus().equals("Pending") || request.getStatus().equals("In Progress")) {
                activeRequests.add(request);
            }
        }
        return activeRequests;
    }
    
    // Schedule donation event
    public void scheduleDonationEvent(Date eventDate, String eventDescription) {
    lastDonationEventDate = eventDate;

    // Create work request for the event using an anonymous inner class (concrete implementation of WorkRequest)
    WorkRequest eventRequest = new WorkRequest() { //
        @Override
        public String toString() {
            return getMessage();
        }
    };
    eventRequest.setMessage("Donation Event: " + eventDescription + " on " + eventDate); //
    eventRequest.setSender(admin.getUserAccount()); // Set admin as sender
    eventRequest.setStatus("Scheduled"); //

    addDonationRequest(eventRequest); //
}
    
    // Generate donor report
    public List<Donor> getTopDonors(int count) {
        // Sort donors by total donation amount
        List<Donor> sortedDonors = new ArrayList<>(donorList);
        sortedDonors.sort((d1, d2) -> 
            Double.compare(d2.getTotalDonationAmount(), d1.getTotalDonationAmount())
        );
        
        // Return top N donors
        int resultSize = Math.min(count, sortedDonors.size());
        return sortedDonors.subList(0, resultSize);
    }
    
    // Get recent donations
    public List<Donation> getRecentDonations(int count) {
        List<Donation> sorted = new ArrayList<>();
        sorted.sort((d1, d2) -> d2.getDonationDate().compareTo(d1.getDonationDate()));
        return sorted.subList(0, Math.min(count, sorted.size()));
    }
    
    // Send donation acknowledgements
    public void sendAcknowledgements(List<Donation> donations) {
        for (Donation donation : donations) {
            donation.sendAcknowledgement();
        }
    }
    
    @Override
    public String toString() {
        return "Donation Management [Donors: " + donorList.size() + 
               ", Donations: " + donationRecords.size() + 
               ", Total Funds: $" + String.format("%.2f", totalFundsRaised) + "]";
    }
}
