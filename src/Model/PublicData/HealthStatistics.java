/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author tiankaining
 */
package Model.PublicData;

import java.util.List;
import java.util.Date;

public class HealthStatistics {
    private List<RegionData> regionData;
    private String timeSpan;
    private Date lastUpdated;

    public HealthStatistics(List<RegionData> regionData, String timeSpan, Date lastUpdated) {
        this.regionData = regionData;
        this.timeSpan = timeSpan;
        this.lastUpdated = lastUpdated;
    }

    public RegionData getDataByRegion(String region) {
        for (RegionData data : regionData) {
            if (data.getRegionName().equalsIgnoreCase(region)) {
                return data;
            }
        }
        return null; // or throw new IllegalArgumentException("Region not found");
    }

    public int getTotalPatients() {
        int total = 0;
        for (RegionData data : regionData) {
            total += data.getPatientCount();
        }
        return total;
    }

    public int getDischargedCount() {
        int total = 0;
        for (RegionData data : regionData) {
            total += data.getDischargedCount();
        }
        return total;
    }

    public int getHospitalizedCount() {
        int total = 0;
        for (RegionData data : regionData) {
            total += data.getHospitalizedCount();
        }
        return total;
    }

    public int getDeathsCount() {
        int total = 0;
        for (RegionData data : regionData) {
            total += data.getDeathsCount();
        }
        return total;
    }

    public double getAverageLengthOfStay() {
        int totalPatients = 0;
        double totalDays = 0.0;
        for (RegionData data : regionData) {
            totalPatients += data.getPatientCount();
            totalDays += data.getTotalLengthOfStay();
        }
        return totalPatients == 0 ? 0 : totalDays / totalPatients;
    }

    // Getters and setters
    public List<RegionData> getRegionData() {
        return regionData;
    }

    public void setRegionData(List<RegionData> regionData) {
        this.regionData = regionData;
    }

    public String getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

