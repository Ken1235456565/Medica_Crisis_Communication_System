package Model.Organization;

import Model.Employee.Employee;
import Model.Personnel.Admin;
import Model.Supplies.SupplyItem;
import Model.Supplies.Vehicle;
import Model.WorkQueue.DeliveryAssignmentRequest;
import Model.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Resource Dispatch Unit for managing logistics and distribution
 * @author tiankaining
 */
public class ResourceDispatchUnit extends Organization { // EXTENDS Organization
    private String unitName;
    private List<Vehicle> transportFleet;
    private List<SupplyItem> dispatchQueue; // 需要配送的物资
    private Map<String, Boolean> routeAvailability; // 路线通畅情况
    private List<WorkRequest> dispatchRequests;
    
    // Default constructor
    public ResourceDispatchUnit() {
        super("Resource Dispatch");
        this.unitName = "Resource Dispatch";
        this.transportFleet = new ArrayList<>();
        this.dispatchQueue = new ArrayList<>();
        this.routeAvailability = new HashMap<>();
        this.dispatchRequests = new ArrayList<>();
    }
    
    // Constructor with unit name
    public ResourceDispatchUnit(String unitName) {
        super(unitName);
        this.unitName = unitName;
        this.transportFleet = new ArrayList<>();
        this.dispatchQueue = new ArrayList<>();
        this.routeAvailability = new HashMap<>();
        this.dispatchRequests = new ArrayList<>();
    }
    
    // Constructor with detailed info
    public ResourceDispatchUnit(String unitName, Admin admin) {
        super(unitName, admin);
        this.unitName = unitName;
        this.transportFleet = new ArrayList<>();
        this.dispatchQueue = new ArrayList<>();
        this.routeAvailability = new HashMap<>();
        this.dispatchRequests = new ArrayList<>();
    }
    
    // Getters and setters
    @Override
    public String getOrganizationName() {
        return unitName;
    }
    
    @Override
    public void setOrganizationName(String unitName) {
        this.unitName = unitName;
        super.setOrganizationName(unitName);
    }
    
    public List<Vehicle> getTransportFleet() {
        return transportFleet;
    }
    
    public List<SupplyItem> getDispatchQueue() {
        return dispatchQueue;
    }
    
    public Map<String, Boolean> getRouteAvailability() {
        return routeAvailability;
    }
    
    public List<WorkRequest> getDispatchRequests() {
        return dispatchRequests;
    }
    
    // Add vehicle to fleet
    public void addVehicle(Vehicle vehicle) {
        transportFleet.add(vehicle);
    }
    
    // Remove vehicle from fleet
    public void removeVehicle(Vehicle vehicle) {
        transportFleet.remove(vehicle);
    }
    
    // Add supply item to dispatch queue
    public void addToDispatchQueue(SupplyItem item) {
        dispatchQueue.add(item);
    }
    
    // Remove supply item from dispatch queue
    public void removeFromDispatchQueue(SupplyItem item) {
        dispatchQueue.remove(item);
    }
    
    // Update route availability
    public void updateRouteAvailability(String route, boolean isAvailable) {
        routeAvailability.put(route, isAvailable);
    }
    
    // Add dispatch request
    public void addDispatchRequest(WorkRequest request) {
        dispatchRequests.add(request);
        addWorkRequest(request); // Also add to the general work queue
    }
    
    // Create a new dispatch request
    public DeliveryAssignmentRequest createDispatchRequest(String destination, List<SupplyItem> items, Employee requestedBy) {
        // You would typically create a Delivery object here and then a DeliveryAssignmentRequest
        // For simplicity, let's assume we're creating a generic DeliveryAssignmentRequest for now.
        // In a real scenario, you'd create a Delivery object with the items.
        
        // Example: Create a dummy delivery for the request
        Model.Supplies.Delivery dummyDelivery = new Model.Supplies.Delivery(destination, new Date());
        for (SupplyItem item : items) {
            dummyDelivery.addItem(item);
        }
        
        DeliveryAssignmentRequest request = new DeliveryAssignmentRequest(dummyDelivery);
        request.setSender(requestedBy.getUserAccount()); // Set who sent the request
        
        // Add all items to dispatch queue
        for (SupplyItem item : items) {
            addToDispatchQueue(item);
        }
        
        addDispatchRequest(request);
        return request;
    }
    
    // Find available vehicle by type
    public Vehicle findAvailableVehicleByType(String type) {
        for (Vehicle vehicle : transportFleet) {
            if (vehicle.getStatus().equals("Available") && 
                vehicle.getType().equals(type)) {
                return vehicle;
            }
        }
        return null;
    }
    
    // Assign vehicle to dispatch
    public boolean assignVehicleToDispatch(DeliveryAssignmentRequest request, Vehicle vehicle, Employee driver) {
        if (!vehicle.getStatus().equals("Available")) {
            return false;
        }
        
        String destination = request.getDelivery().getDestination();
        
        // Check if route is available
        if (routeAvailability.containsKey(destination) && 
            !routeAvailability.get(destination)) {
            return false; // Route not available
        }
        
        // Dispatch the vehicle
        boolean dispatched = vehicle.dispatch(destination, driver.getName());
        if (dispatched) {
            request.assignDelivery(driver, vehicle.getVehicleId());
            request.getDelivery().setVehicleUsed(vehicle.getVehicleId());
            request.getDelivery().setDriverName(driver.getName());
            request.getDelivery().startDelivery(vehicle.getVehicleId(), driver.getName());
        }
        
        return dispatched;
    }
    
    // Complete dispatch request
    public void completeDispatchRequest(DeliveryAssignmentRequest request, Vehicle vehicle, String newLocation, double remainingFuel) {
        request.completeAssignment("Delivery completed.");
        request.getDelivery().completeDelivery();
        vehicle.returnVehicle(newLocation, remainingFuel);
    }
    
    // Check dispatch status
    public List<WorkRequest> getActiveDispatchRequests() {
        List<WorkRequest> activeRequests = new ArrayList<>();
        for (WorkRequest request : dispatchRequests) {
            if (request.getStatus().equals("Pending") || request.getStatus().equals("Assigned") || request.getStatus().equals("In Transit")) {
                activeRequests.add(request);
            }
        }
        return activeRequests;
    }
    
    // Check vehicle status
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : transportFleet) {
            if (vehicle.getStatus().equals("Available")) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }
    
    @Override
    public String toString() {
        return unitName + " [Fleet: " + transportFleet.size() + 
               ", Dispatch Queue: " + dispatchQueue.size() + 
               ", Active Requests: " + getActiveDispatchRequests().size() + "]";
    }
}