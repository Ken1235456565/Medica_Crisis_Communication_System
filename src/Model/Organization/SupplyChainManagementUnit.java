package Model.Organization;


import Model.Supplies.DeliveryCatalog;
import Model.Supplies.SupplyItem;
import Model.Supplies.SupplyItemCatalog;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Inventory Manager for tracking and managing supplies
 * @author tiankaining
 */
public class SupplyChainManagementUnit extends Organization {
    private List<SupplyItem> inventoryList;
    private Map<String, Integer> itemQuantityMap; // 物资名 -> 库存数量
    private Date lastAuditDate;
    private String warehouseLocation;
    private Map<String, Double> itemThresholds; // Reorder thresholds
    private SupplyItemCatalog itemCatalog; // Items below threshold
    DeliveryCatalog deliveryCatalog;
    
    // Default constructor
    public SupplyChainManagementUnit() {
        super("Inventory Management");
        this.inventoryList = new ArrayList<>();
        this.itemQuantityMap = new HashMap<>();
        this.lastAuditDate = new Date();
        this.itemThresholds = new HashMap<>();
        this.itemCatalog = new SupplyItemCatalog();
    }
    
    // Constructor with warehouse location
    public SupplyChainManagementUnit(String warehouseLocation) {
        super("Inventory Management - " + warehouseLocation);
        this.inventoryList = new ArrayList<>();
        this.itemQuantityMap = new HashMap<>();
        this.lastAuditDate = new Date();
        this.warehouseLocation = warehouseLocation;
        this.itemThresholds = new HashMap<>();
        this.itemCatalog = new SupplyItemCatalog();
    }
    
public SupplyChainManagementUnit(String name, SupplyItemCatalog itemCatalog, DeliveryCatalog deliveryCatalog) {
    super(name);
    this.itemCatalog = itemCatalog != null ? itemCatalog : new SupplyItemCatalog();
    this.deliveryCatalog = deliveryCatalog != null ? deliveryCatalog : new DeliveryCatalog();
    this.inventoryList = new ArrayList<>();
    this.itemQuantityMap = new HashMap<>();
    this.lastAuditDate = new Date();
    this.itemThresholds = new HashMap<>();
    this.warehouseLocation = "Default Location"; // 可选设置默认值
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
    
    public SupplyItemCatalog getSupplyItemCatalog() {
        return itemCatalog;
    }

    public DeliveryCatalog getDeliveryCatalog() {
        return deliveryCatalog;
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
    SupplyItem item = findItemByName(itemName); // 从 catalog 查找具体物品
    
    if (item == null) {
        removeLowStockItem(itemName); // 更安全
        return;
    }

    if (itemThresholds.containsKey(itemName)) {
        double threshold = itemThresholds.get(itemName);
        if (item.getQuantity() <= threshold) {
            if (findLowStockItem(itemName) == null) {
                itemCatalog.add(item);
            }
        } else {
            removeLowStockItem(itemName);
        }
    }
}

    
    // Find item in low stock list
private SupplyItem findLowStockItem(String itemName) {
    for (SupplyItem item : itemCatalog.getAllItems()) {
        if (item.getName().equals(itemName)) {
            return item;
        }
    }
    return null;
}


private void removeLowStockItem(String itemName) {
    SupplyItem item = findLowStockItem(itemName);
    if (item != null) {
        itemCatalog.remove(item);
    }
}
    
    // Update all low stock statuses
public void updateAllLowStockStatuses() {
    itemCatalog.clear();
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
               ", Low Stock: " + itemCatalog.size() +
               ", Last Audit: " + lastAuditDate + "]";
    }
}
