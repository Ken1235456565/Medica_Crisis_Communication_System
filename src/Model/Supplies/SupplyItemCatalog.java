package Model.Supplies;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a catalog of SupplyItem objects with support for
 * adding, removing, searching by name, ID, or type, and inventory operations.
 */
public class SupplyItemCatalog {
    private List<SupplyItem> itemList;

    public SupplyItemCatalog() {
        this.itemList = new ArrayList<>();
    }

    public List<SupplyItem> getItemList() {
        return itemList;
    }
    
    

    // Basic Add - No Duplicates by Name
    public void add(SupplyItem item) {
        if (findItemByName(item.getName()) == null) {
            itemList.add(item);
        }
    }

    
    // Basic Remove
    public void remove(SupplyItem item) {
        itemList.remove(item);
    }

    // Clear entire catalog
    public void clear() {
        itemList.clear();
    }

    // Full List Access
    public List<SupplyItem> getAllItems() {
        return itemList;
    }

    // Count items
    public int size() {
        return itemList.size();
    }

    // ========== FINDING LOGIC ==========

    // Find by name (used for display & low-stock tracking)
    public SupplyItem findItemByName(String itemName) {
        for (SupplyItem item : itemList) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // Find by ID (used if IDs are assigned)
    public SupplyItem findItemById(String id) {
        for (SupplyItem item : itemList) {
            if (item.getSupplyId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    // Find by Type
    public List<SupplyItem> findItemsByType(String type) {
        List<SupplyItem> results = new ArrayList<>();
        for (SupplyItem item : itemList) {
            if (item.getType().equalsIgnoreCase(type)) {
                results.add(item);
            }
        }
        return results;
    }

    // ========== SETTERS / FOR BULK RELOAD (rarely used) ==========
    public void setItemList(List<SupplyItem> newList) {
        this.itemList = newList;
    }
}
