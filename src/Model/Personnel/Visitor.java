// Model/personnel/roles/VisitorRole.java
package Model.Personnel;

import Model.Personnel.Role;

public class Visitor extends Role {
    private String visitPurpose;
    private String relatedTo;

    public Visitor() {
    }

    public Visitor(String id, String name, boolean isAdmin, String description, String visitPurpose, String relatedTo) {
        super(id, name, isAdmin, description);
        this.visitPurpose = visitPurpose;
        this.relatedTo = relatedTo;
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
}