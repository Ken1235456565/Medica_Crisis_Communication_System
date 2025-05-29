/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.PublicData;

/**
 *
 * @author tiankaining
 */

import Model.PublicData.HealthStatistics;
import Model.PublicData.RegionData;
import Model.PublicData.SymptomData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.List;

public class PublicDataService {
    private List<RegionData> regionDataList;
    private String currentTimeSpan;

    public PublicDataService() {
        this.regionDataList = new ArrayList<>();
        this.currentTimeSpan = "default";
        // TODO: load regionDataList from file/database/mock/etc.
    }

    public void filterDataByTimeSpan(String timeSpan) {
        // TODO: implement filtering logic
        this.currentTimeSpan = timeSpan;
        // You may update regionDataList to only contain data from timeSpan
    }

    public HealthStatistics generateHealthStatistics(String timeSpan) {
        filterDataByTimeSpan(timeSpan);
        return new HealthStatistics(regionDataList, timeSpan, new Date());
    }

    public List<RegionData> getRegionStatistics() {
        return regionDataList;
    }

    public List<SymptomData> getSymptomSummary(String timeSpan) {
        filterDataByTimeSpan(timeSpan);
        List<SymptomData> result = new ArrayList<>();
        for (RegionData region : regionDataList) {
            result.add(region.getSymptomSummary());
        }
        return result;
    }
}

