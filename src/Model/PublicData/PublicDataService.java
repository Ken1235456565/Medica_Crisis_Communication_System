/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.PublicData;

/**
 *
 * @author tiankaining
 */

import Model.EcoSystem;
import Model.Personnel.PublicDataManager;
import Model.PublicData.HealthStatistics;
import Model.PublicData.RegionData;
import Model.PublicData.SymptomData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.List;

public class PublicDataService {
    private EcoSystem ecoSystem;
    private String currentTimeSpan;

    public PublicDataService() {
        this.ecoSystem = EcoSystem.getInstance();
        this.currentTimeSpan = "7 days";
    }

    public void filterDataByTimeSpan(String timeSpan) {
        this.currentTimeSpan = timeSpan;
    }

    public HealthStatistics generateHealthStatistics(String timeSpan) {
        filterDataByTimeSpan(timeSpan);
        
        PublicDataManager pdm = ecoSystem.getPublicDataManager();
        HealthStatistics stats = pdm.filterHealthStatisticsByTimeSpan(timeSpan);
        
        return stats;
    }

    public List<RegionData> getRegionStatistics() {
        HealthStatistics stats = generateHealthStatistics(currentTimeSpan);
        return stats != null ? stats.getRegionData() : new ArrayList<>();
    }

    public List<SymptomData> getSymptomSummary(String timeSpan) {
        HealthStatistics stats = generateHealthStatistics(timeSpan);
        List<SymptomData> result = new ArrayList<>();
        
        if (stats != null) {
            for (RegionData region : stats.getRegionData()) {
                result.add(region.getSymptomSummary());
            }
        }
        
        return result;
    }
}

