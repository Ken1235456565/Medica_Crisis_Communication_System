package Model.Organization;


import Model.Supplies.SupplyItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Inventory Manager for tracking and managing supplies
 * @author tiankaining
 */
public class InventoryManager extends Organization {
    private List<SupplyItem> inventoryList;
    private Map<String, Integer> itemQuantityMap; // 物资名 -> 库存数量
    private Date lastAuditDate;
    private String warehouseLocation;
    private Map<String, Double> itemThresholds; // Reorder thresholds
    private List<SupplyItem> lowStockItems; // Items below threshold
    
    // Default constructor
    public InventoryManager() {
        super("Inventory Management");
        this.inventoryList = new ArrayList<>();
        this.itemQuantityMap = new HashMap<>();
        this.lastAuditDate = new Date();
        this.itemThresholds = new HashMap<>();
        this.lowStockItems = new ArrayList<>();
    }
    
    // Constructor with warehouse location
    public InventoryManager(String warehouseLocation) {
        super("Inventory Management - " + warehouseLocation);
        this.inventoryList = new ArrayList<>();
        this.itemQuantityMap = new HashMap<>();
        this.lastAuditDate = new Date();
        this.warehouseLocation = warehouseLocation;
        this.itemThresholds = new HashMap<>();
        this.lowStockItems = new ArrayList<>();
    }
    
    // Getters and setters
    public List<SupplyItem> getInventoryList() {
        return inventoryList;
    }
    
    public Map<String, Integer> getItemQuantityMap() {
        return itemQuantityMap;
    }
    
    public Date getLastAuditDate() {
        return lastAuditDate;
    }
    
    public void setLastAuditDate(Date lastAuditDate) {
        this.lastAuditDate = lastAuditDate;
    }
    
    public String getWarehouseLocation() {
        return warehouseLocation;
    }
    
    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }
    
    public Map<String, Double> getItemThresholds() {
        return itemThresholds;
    }
    
    public List<SupplyItem> getLowStockItems() {
        return lowStockItems;
    }
    
    // Add item to inventory
    public void addItem(SupplyItem item) {
        // Check if the item already exists
        SupplyItem existingItem = findItemByName(item.getName());
        
        if (existingItem != null) {
            // Update existing item
            existingItem.addQuantity(item.getQuantity());
            itemQuantityMap.put(item.getName(), existingItem.getQuantity());
        } else {
            // Add new item
            inventoryList.add(item);
            itemQuantityMap.put(item.getName(), item.getQuantity());
        }
        
        // Check if item is below threshold
        updateLowStockStatus(item.getName());
    }
    
    // Remove item from inventory
    public boolean removeItem(String itemName, int quantity) {
        SupplyItem item = findItemByName(itemName);
        
        if (item == null) {
            return false;
        }
        
        if (item.getQuantity() < quantity) {
            return false; // Not enough in stock
        }
        
        // Deduct quantity
        item.deductQuantity(quantity);
        itemQuantityMap.put(itemName, item.getQuantity());
        
        // If quantity is zero, remove item completely
        if (item.getQuantity() == 0) {
            inventoryList.remove(item);
            itemQuantityMap.remove(itemName);
        }
        
        // Check if item is below threshold
        updateLowStockStatus(itemName);
        
        return true;
    }
    
    // Find item by name
    public SupplyItem findItemByName(String itemName) {
        for (SupplyItem item : inventoryList) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    // Find items by type
    public List<SupplyItem> findItemsByType(String type) {
        List<SupplyItem> result = new ArrayList<>();
        for (SupplyItem item : inventoryList) {
            if (item.getType().equals(type)) {
                result.add(item);
            }
        }
        return result;
    }
    
    // Get item quantity
    public int getItemQuantity(String itemName) {
        return itemQuantityMap.getOrDefault(itemName, 0);
    }
    
    // Set reorder threshold
    public void setReorderThreshold(String itemName, double threshold) {
        itemThresholds.put(itemName, threshold);
        updateLowStockStatus(itemName);
    }
    
    // Update low stock status
    private void updateLowStockStatus(String itemName) {
        SupplyItem item = findItemByName(itemName);
        
        if (item == null) {
            lowStockItems.remove(findLowStockItem(itemName));
            return;
        }
        
        // Check if item has a threshold
        if (itemThresholds.containsKey(itemName)) {
            double threshold = itemThresholds.get(itemName);
            
            // Check if item is below threshold
            if (item.getQuantity() <= threshold) {
                // Add to low stock list if not already there
                if (findLowStockItem(itemName) == null) {
                    lowStockItems.add(item);
                }
            } else {
                // Remove from low stock list
                lowStockItems.remove(findLowStockItem(itemName));
            }
        }
    }
    
    // Find item in low stock list
    private SupplyItem findLowStockItem(String itemName) {
        for (SupplyItem item : lowStockItems) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    // Update all low stock statuses
    public void updateAllLowStockStatuses() {
        lowStockItems.clear();
        
        for (SupplyItem item : inventoryList) {
            updateLowStockStatus(item.getName());
        }
    }
    
    // Perform inventory audit
    public void performAudit() {
        lastAuditDate = new Date();
        
        // Verify all quantities
        for (SupplyItem item : inventoryList) {
            itemQuantityMap.put(item.getName(), item.getQuantity());
        }
        
        // Check for expired items
        List<SupplyItem> expiredItems = new ArrayList<>();
        for (SupplyItem item : inventoryList) {
            if (item.isExpired()) {
                expiredItems.add(item);
            }
        }
        
        // Remove expired items
        for (SupplyItem item : expiredItems) {
            inventoryList.remove(item);
            itemQuantityMap.remove(item.getName());
        }
        
        // Update low stock statuses
        updateAllLowStockStatuses();
    }
    
    // Calculate total inventory value
    public double calculateTotalInventoryValue() {
        double total = 0;
        for (SupplyItem item : inventoryList) {
            total += item.getTotalValue();
        }
        return total;
    }
    
    // Check if item is available in sufficient quantity
    public boolean isItemAvailable(String itemName, int requiredQuantity) {
        int available = getItemQuantity(itemName);
        return available >= requiredQuantity;
    }
    
    
    public String toString() {
        return "Inventory Manager - " + warehouseLocation + 
               " [Items: " + inventoryList.size() + 
               ", Low Stock: " + lowStockItems.size() +
               ", Last Audit: " + lastAuditDate + "]";
    }
}
