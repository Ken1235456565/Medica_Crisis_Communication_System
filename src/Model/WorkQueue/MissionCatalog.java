package Model.WorkQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Catalog to manage all MissionStats objects related to emergency response, donation, etc.
 */
public class MissionCatalog {

    private List<MissionStats> missionList;

    public MissionCatalog() {
        this.missionList = new ArrayList<>();
    }

    public List<MissionStats> getMissionList() {
        return missionList;
    }

    public void setMissionList(List<MissionStats> missionList) {
        this.missionList = missionList;
    }

    public void addMission(MissionStats missionStats) {
        missionList.add(missionStats);
    }

    public void removeMission(MissionStats missionStats) {
        missionList.remove(missionStats);
    }

    public MissionStats getMissionByName(String missionName) {
        for (MissionStats mission : missionList) {
            if (mission.getMissionName().equalsIgnoreCase(missionName)) {
                return mission;
            }
        }
        return null;
    }

    public List<MissionStats> getFinalizedMissions() {
        List<MissionStats> finalized = new ArrayList<>();
        for (MissionStats mission : missionList) {
            if ("Finalized".equalsIgnoreCase(mission.getStatus())) {
                finalized.add(mission);
            }
        }
        return finalized;
    }

    @Override
    public String toString() {
        return "MissionCatalog: " + missionList.size() + " missions";
    }
}

