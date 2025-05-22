package Model.Supplies;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of Supply objects.
 */
public class SupplyItemCatalog {

    private List<SupplyItem> supplyList;

    public SupplyItemCatalog() {
        this.supplyList = new ArrayList<>();
    }

    public List<SupplyItem> getSupplyList() {
        return supplyList;
    }

    public void setSupplyList(List<SupplyItem> supplyList) {
        this.supplyList = supplyList;
    }

    public void addSupply(SupplyItem supply) {
        this.supplyList.add(supply);
    }

    public void removeSupply(SupplyItem supply) {
        this.supplyList.remove(supply);
    }

    public SupplyItem findSupplyById(String id) {
        for (SupplyItem supply : supplyList) {
            if (supply.getSupplyId().equals(id)) {
                return supply;
            }
        }
        return null;
    }

    public List<SupplyItem> findSuppliesByType(String type) {
        List<SupplyItem> results = new ArrayList<>();
        for (SupplyItem supply : supplyList) {
            if (supply.getType().equals(type)) {
                results.add(supply);
            }
        }
        return results;
    }
}