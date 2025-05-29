/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import Model.PublicData.HealthStatistics;
import Model.Supplies.Donation;
import java.util.List;

/**
 *
 * @author tiankaining
 */
public class CSVExporter {

    public void exportDonations(List<Donation> donations, String filepath) {
        // TODO: write logic to export donations to CSV
    }

    public void exportPublicData(HealthStatistics stats, String filepath) {
        // TODO: write logic to export health statistics to CSV
    }

    public String createCSVHeader(String[] headers) {
        return String.join(",", headers);
    }

    public String formatCSVRow(Object[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i] != null ? data[i].toString() : "");
            if (i < data.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}