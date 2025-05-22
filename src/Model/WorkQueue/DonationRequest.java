package Model.WorkQueue;

import Model.Supplies.Donation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a request for processing or distributing donations.
 * This would be used by the DonationManagementUnit.
 */
public class DonationRequest extends WorkRequest { // Extends WorkRequest

    private List<Donation> donations;
    private String requestType;  // "Process", "Distribute", "Acknowledge"
    private String distributionTarget; // Where donations should go (if applicable)
    private String acknowledgementMessage;

    public DonationRequest() {
        this.setStatus("Pending");
        this.setMessage("Donation Request");
    }

    public DonationRequest(String requestType, List<Donation> donations) {
        this();
        this.requestType = requestType;
        this.donations = donations;
        this.setMessage("Request to " + requestType + " donations");
    }

    // Getters and setters

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

    // Methods to manage the request

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

    @Override
    public String toString() {
        return getMessage() + " (" + requestType + ") - " + getStatus();
    }
}