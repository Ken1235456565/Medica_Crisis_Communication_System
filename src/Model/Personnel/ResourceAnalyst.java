// Model/personnel/roles/ResourceAnalystRole.java
package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.Role.ResourceAnalystRole;

public class ResourceAnalyst extends Employee {
    private String analysisArea;

    public ResourceAnalyst(String analysisArea,
                           String id, String name, String gender, int age, String dateOfBirth,
                           String position, String department, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, position, department, contactInfo);
        this.analysisArea = analysisArea;
    }

    public ResourceAnalyst() {
        super();
    }

    public String getAnalysisArea() {
        return analysisArea;
    }

    public void setAnalysisArea(String analysisArea) {
        this.analysisArea = analysisArea;
    }
}
