package Model.Supplies;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of ICUbed objects.
 */
public class ICUbedCatalog {

    private List<ICUbed> icuBedList;

    public ICUbedCatalog() {
        this.icuBedList = new ArrayList<>();
    }

    public List<ICUbed> getIcuBedList() {
        return icuBedList;
    }

    public void setIcuBedList(List<ICUbed> icuBedList) {
        this.icuBedList = icuBedList;
    }

    public void addIcuBed(ICUbed icuBed) {
        this.icuBedList.add(icuBed);
    }

    public void removeIcuBed(ICUbed icuBed) {
        this.icuBedList.remove(icuBed);
    }

    public ICUbed findIcuBedById(String id) {
        for (ICUbed icuBed : icuBedList) {
            if (icuBed.getBedId().equals(id)) {
                return icuBed;
            }
        }
        return null;
    }

    public List<ICUbed> findAvailableIcuBeds() {
        List<ICUbed> availableBeds = new ArrayList<>();
        for (ICUbed icuBed : icuBedList) {
            if (!icuBed.isOccupied()) {
                availableBeds.add(icuBed);
            }
        }
        return availableBeds;
    }
}