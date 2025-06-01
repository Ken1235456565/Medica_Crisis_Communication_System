/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Patient;


public class CarePlanData {
    private String planId;
    private Patient patient;
    private String startDate;
    private String responsibleNurse;
    private String riskLevel;
    private String dietaryNeeds;
    private String hygieneNeeds;
    private static int counter = 1;
    
    public CarePlanData(Patient patient, String startDate, String responsibleNurse, 
                       String riskLevel, String dietaryNeeds, String hygieneNeeds) {
        this.planId = "CP" + counter++;
        this.patient = patient;
        this.startDate = startDate;
        this.responsibleNurse = responsibleNurse;
        this.riskLevel = riskLevel;
        this.dietaryNeeds = dietaryNeeds;
        this.hygieneNeeds = hygieneNeeds;
    }
    
    // Getters and setters
    public String getPlanId() { return planId; }
    public Patient getPatient() { return patient; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getResponsibleNurse() { return responsibleNurse; }
    public void setResponsibleNurse(String responsibleNurse) { this.responsibleNurse = responsibleNurse; }
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
    public String getDietaryNeeds() { return dietaryNeeds; }
    public void setDietaryNeeds(String dietaryNeeds) { this.dietaryNeeds = dietaryNeeds; }
    public String getHygieneNeeds() { return hygieneNeeds; }
    public void setHygieneNeeds(String hygieneNeeds) { this.hygieneNeeds = hygieneNeeds; }
}
