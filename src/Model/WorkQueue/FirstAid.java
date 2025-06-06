/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.WorkQueue;

/**
 *
 * @author tiankaining
 */



import Model.User.UserAccount;
import java.util.Date;

public class FirstAid extends WorkRequest {

    private String location;
    private String requesterName;
    private String contactPhone;
    private String emergencyContact;
    private String symptomDescription;
    private String suppliesRequired;
    private String attachedImagePath;
    private String type;
    UserAccount userAccount;

    public FirstAid() {
        super(); // 保证继承自 WorkRequest 的基础字段也能用
        setRequestDate(new Date());
        setStatus("Pending");
    }
    
public FirstAid(String type, String location, int urgencyLevel, String requesterName) {
    super(); // 如果 WorkRequest 有构造逻辑，保留它

    this.type = type; // 你得有这个字段
    this.location = location;
    this.requesterName = requesterName;

    setStatus("Pending");
    setRequestDate(new Date());
}

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getSymptomDescription() {
        return symptomDescription;
    }

    public void setSymptomDescription(String symptomDescription) {
        this.symptomDescription = symptomDescription;
    }

    public String getSuppliesRequired() {
        return suppliesRequired;
    }

    public void setSuppliesRequired(String suppliesRequired) {
        this.suppliesRequired = suppliesRequired;
    }

    public String getAttachedImagePath() {
        return attachedImagePath;
    }

    public void setAttachedImagePath(String attachedImagePath) {
        this.attachedImagePath = attachedImagePath;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


    
    
}

