/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.PublicData;

public class SymptomData {
    private String regionName;
    private int feverCount;
    private int coughCount;
    private int chestTightnessCount;
    private int comaCount;

    public SymptomData(String regionName, int feverCount, int coughCount, int chestTightnessCount, int comaCount) {
        this.regionName = regionName;
        this.feverCount = feverCount;
        this.coughCount = coughCount;
        this.chestTightnessCount = chestTightnessCount;
        this.comaCount = comaCount;
    }

    public int getTotalSymptoms() {
        return feverCount + coughCount + chestTightnessCount + comaCount;
    }

    public double getSymptomPercentage(String symptom) {
        int total = getTotalSymptoms();
        if (total == 0) {
            return 0.0;
        }

        switch (symptom.toLowerCase()) {
            case "fever":
                return (double) feverCount / total * 100;
            case "cough":
                return (double) coughCount / total * 100;
            case "chesttightness":
                return (double) chestTightnessCount / total * 100;
            case "coma":
                return (double) comaCount / total * 100;
            default:
                throw new IllegalArgumentException("Unknown symptom type: " + symptom);
        }
    }

    // Getters and Setters
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getFeverCount() {
        return feverCount;
    }

    public void setFeverCount(int feverCount) {
        this.feverCount = feverCount;
    }

    public int getCoughCount() {
        return coughCount;
    }

    public void setCoughCount(int coughCount) {
        this.coughCount = coughCount;
    }

    public int getChestTightnessCount() {
        return chestTightnessCount;
    }

    public void setChestTightnessCount(int chestTightnessCount) {
        this.chestTightnessCount = chestTightnessCount;
    }

    public int getComaCount() {
        return comaCount;
    }

    public void setComaCount(int comaCount) {
        this.comaCount = comaCount;
    }

    @Override
    public String toString() {
        return "SymptomData{" +
                "regionName='" + regionName + '\'' +
                ", feverCount=" + feverCount +
                ", coughCount=" + coughCount +
                ", chestTightnessCount=" + chestTightnessCount +
                ", comaCount=" + comaCount +
                '}';
    }
}

