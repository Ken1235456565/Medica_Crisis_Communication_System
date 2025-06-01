package Model.WorkQueue;

import Model.Supplies.Donation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class DonationRequest extends WorkRequest {

    private List<Donation> donations;
    private String requestType;              // "Process", "Distribute", "Acknowledge"
    private String distributionTarget;       // Destination for distributed items
    private String acknowledgementMessage;   // Message for donors
    private String urgencyLevel;             // "High", "Medium", "Low"
    private String targetAmount;             // Target monetary or item quantity goal

    // Default constructor
    public DonationRequest() {
        this.setStatus("Pending");
        this.setMessage("Donation Request");
    }

    // Main constructor
    public DonationRequest(String requestType, List<Donation> donations) {
        this();
        this.requestType = requestType;
        this.donations = donations;
        this.setMessage("Request to " + requestType + " donations");
    }

    // Additional methods
    public void addDonation(Donation donation) {
        if (donations == null) {
            donations = new ArrayList<>();
        }
        donations.add(donation);
    }

    public void processRequest() {
        this.setStatus("Processed");
        this.setResolveDate(new Date());
    }

    public void distributeRequest(String target) {
        this.setStatus("Distributed");
        this.distributionTarget = target;
        this.setResolveDate(new Date());
    }

    public void acknowledgeDonors(String message) {
        this.setStatus("Acknowledged");
        this.acknowledgementMessage = message;
        this.setResolveDate(new Date());
    }

    // Getters and Setters
    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getDistributionTarget() {
        return distributionTarget;
    }

    public void setDistributionTarget(String distributionTarget) {
        this.distributionTarget = distributionTarget;
    }

    public String getAcknowledgementMessage() {
        return acknowledgementMessage;
    }

    public void setAcknowledgementMessage(String acknowledgementMessage) {
        this.acknowledgementMessage = acknowledgementMessage;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public String getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(String targetAmount) {
        this.targetAmount = targetAmount;
    }

    @Override
    public String toString() {
        return getMessage() + " (" + requestType + ") - " + getStatus();
    }
}
