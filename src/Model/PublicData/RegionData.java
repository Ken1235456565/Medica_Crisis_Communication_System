/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.PublicData;


public class RegionData {
    private String regionName;
    private int totalPatients;
    private int discharged;
    private int hospitalized;
    private int deaths;
    private double averageLengthOfStay;
    private SymptomData symptomSummary;

    public RegionData(String regionName, int totalPatients, int discharged, int hospitalized,
                      int deaths, double averageLengthOfStay, SymptomData symptomSummary) {
        this.regionName = regionName;
        this.totalPatients = totalPatients;
        this.discharged = discharged;
        this.hospitalized = hospitalized;
        this.deaths = deaths;
        this.averageLengthOfStay = averageLengthOfStay;
        this.symptomSummary = symptomSummary;
    }

    // Getters and Setters
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public int getDischargedCount() {
        return discharged;
    }

    public void setDischargedCount(int discharged) {
        this.discharged = discharged;
    }

    public int getHospitalizedCount() {
        return hospitalized;
    }

    public void setHospitalizedCount(int hospitalized) {
        this.hospitalized = hospitalized;
    }

    public int getDeathsCount() {
        return deaths;
    }

    public void setDeathsCount(int deaths) {
        this.deaths = deaths;
    }

    public double getAverageLengthOfStay() {
        return averageLengthOfStay;
    }

    public void setAverageLengthOfStay(double averageLengthOfStay) {
        this.averageLengthOfStay = averageLengthOfStay;
    }

    public SymptomData getSymptomSummary() {
        return symptomSummary;
    }

    public void setSymptomSummary(SymptomData symptomSummary) {
        this.symptomSummary = symptomSummary;
    }
    
    public int getPatientCount() {
    return totalPatients;
}

public double getTotalLengthOfStay() {
    return averageLengthOfStay * totalPatients;
}

    // Optionally: override toString() for debugging
    @Override
    public String toString() {
        return "RegionData{" +
                "regionName='" + regionName + '\'' +
                ", totalPatients=" + totalPatients +
                ", discharged=" + discharged +
                ", hospitalized=" + hospitalized +
                ", deaths=" + deaths +
                ", averageLengthOfStay=" + averageLengthOfStay +
                ", symptomSummary=" + symptomSummary +
                '}';
    }
}

