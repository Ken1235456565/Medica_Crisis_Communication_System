/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Supplies;

/**
 *
 * @author tiankaining
 */
public class Vehicle {
    private String vehicleId;
    private String type; // e.g., "Ambulance", "Truck", "Car"
    private String status; // e.g., "Available", "In Use", "Under Maintenance"
    private String currentDestination;
    private String currentDriver;
    private double fuelLevel; // Percentage or liters

    private static int counter = 1;

    // Default constructor
    public Vehicle() {
        this.vehicleId = "VEH" + counter++;
        this.status = "Available"; // Default status
        this.fuelLevel = 100.0; // Default full tank
    }

    // Constructor with basic info
    public Vehicle(String type, String vehicleId) {
        this(); // Call default constructor for ID generation and defaults
        this.type = type;
        this.vehicleId = vehicleId; // Allow setting a specific ID if needed, otherwise default is used
    }

    // Constructor with more detailed info
    public Vehicle(String type, String vehicleId, String status, double fuelLevel) {
        this(type, vehicleId);
        this.status = status;
        this.fuelLevel = fuelLevel;
    }

    // Getters and setters
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentDestination() {
        return currentDestination;
    }

    public void setCurrentDestination(String currentDestination) {
        this.currentDestination = currentDestination;
    }

    public String getCurrentDriver() {
        return currentDriver;
    }

    public void setCurrentDriver(String currentDriver) {
        this.currentDriver = currentDriver;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    // Method to dispatch the vehicle
    public boolean dispatch(String destination, String driverName) {
        if (this.status.equals("Available") && this.fuelLevel > 10) { // Require some fuel to dispatch
            this.status = "In Use";
            this.currentDestination = destination;
            this.currentDriver = driverName;
            this.fuelLevel -= 10; // Simulate fuel consumption
            return true;
        }
        return false;
    }

    // Method to return the vehicle to base/new location
    public void returnVehicle(String newLocation, double remainingFuel) {
        this.status = "Available";
        this.currentDestination = newLocation;
        this.currentDriver = null; // Driver is no longer assigned to this vehicle
        this.fuelLevel = remainingFuel;
    }

    // Method for maintenance
    public void scheduleMaintenance() {
        this.status = "Under Maintenance";
    }

    @Override
    public String toString() {
        return "Vehicle ID: " + vehicleId + ", Type: " + type + ", Status: " + status + ", Fuel: " + fuelLevel + "%";
    }
}
