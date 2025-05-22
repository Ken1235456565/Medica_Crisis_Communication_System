package Model.WorkQueue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents statistics related to a mission or operation.
 * This could be used by the EmergencyResponseUnit, DonationManagementUnit, etc.
 */
public class MissionStats extends WorkRequest { // Extends WorkRequest

    private String missionName;
    private Date missionStart;
    private Date missionEnd;
    private String missionType;   // e.g., "Emergency Response", "Donation Drive"
    private String location;
    private Map<String, Object> stats; // Key-value pairs for different stats

    public MissionStats() {
        this.setStatus("Recorded");
        this.setMessage("Mission Statistics");
    }

    public MissionStats(String missionName, Date missionStart, Date missionEnd, String missionType, String location) {
        this();
        this.missionName = missionName;
        this.missionStart = missionStart;
        this.missionEnd = missionEnd;
        this.missionType = missionType;
        this.location = location;
        this.setMessage("Stats for " + missionName + " (" + missionType + ")");
    }

    // Getters and setters

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public Date getMissionStart() {
        return missionStart;
    }

    public void setMissionStart(Date missionStart) {
        this.missionStart = missionStart;
    }

    public Date getMissionEnd() {
        return missionEnd;
    }

    public void setMissionEnd(Date missionEnd) {
        this.missionEnd = missionEnd;
    }

    public String getMissionType() {
        return missionType;
    }

    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getStats() {
        return stats;
    }

    public void setStats(Map<String, Object> stats) {
        this.stats = stats;
    }

    // Methods to add statistics

    public void addStat(String key, Object value) {
        if (stats == null) {
            stats = new HashMap<>();
        }
        stats.put(key, value);
    }

    public void finalizeStats() {
        this.setStatus("Finalized");
        this.setResolveDate(new Date());
    }

    @Override
    public String toString() {
        return getMessage() + " at " + location + " - " + getStatus();
    }
}