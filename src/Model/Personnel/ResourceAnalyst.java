// Model/personnel/roles/ResourceAnalystRole.java
package Model.Personnel;

import Model.Personnel.Role;

public class ResourceAnalyst extends Role {
    private String analysisArea;

    public ResourceAnalyst() {
    }

    public ResourceAnalyst(String id, String name, boolean isAdmin, String description, String analysisArea) {
        super(id, name, isAdmin, description);
        this.analysisArea = analysisArea;
    }

    public String getAnalysisArea() {
        return analysisArea;
    }

    public void setAnalysisArea(String analysisArea) {
        this.analysisArea = analysisArea;
    }
}