// Model/personnel/roles/VisitorRole.java
package Model.Personnel;

import Model.Person.ContactInfo;
import Model.Person.Person;
import Model.User.UserAccount;

public class Visitor extends Person {
    private String visitPurpose;
    private String relatedTo;
    private UserAccount userAccount;

    public Visitor() {
        super();
    }

    public Visitor(String id, String name, String gender, int age, String dateOfBirth,
                   String visitPurpose, String relatedTo, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, contactInfo);
        this.visitPurpose = visitPurpose;
        this.relatedTo = relatedTo;
    }
    
    public Visitor(String id, String name, String gender, int age, String dateOfBirth,
                    String visitPurpose, String relatedTo, ContactInfo contactInfo, UserAccount userAccount) {
         this(id, name, gender, age, dateOfBirth, visitPurpose, relatedTo, contactInfo);
         this.userAccount = userAccount;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    public String getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(String relatedTo) {
        this.relatedTo = relatedTo;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return getName() + " visiting for: " + visitPurpose + (relatedTo != null ? " (Related to: " + relatedTo + ")" : "");
    }
}
