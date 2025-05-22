package Model.Supplies;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of Equipments objects.
 */
public class EquipmentsCatalog {

    private List<Equipments> equipmentsList;

    public EquipmentsCatalog() {
        this.equipmentsList = new ArrayList<>();
    }

    public List<Equipments> getEquipmentsList() {
        return equipmentsList;
    }

    public void setEquipmentsList(List<Equipments> equipmentsList) {
        this.equipmentsList = equipmentsList;
    }

    public void addEquipment(Equipments equipment) {
        this.equipmentsList.add(equipment);
    }

    public void removeEquipment(Equipments equipment) {
        this.equipmentsList.remove(equipment);
    }

    public Equipments findEquipmentById(String id) {
        for (Equipments equipment : equipmentsList) {
            if (equipment.getEquipmentId().equals(id)) {
                return equipment;
            }
        }
        return null;
    }

    public List<Equipments> findEquipmentsByType(String type) {  // Or by department, etc.
        List<Equipments> results = new ArrayList<>();
        for (Equipments equipment : equipmentsList) {
            if (equipment.getType().equals(type)) {
                results.add(equipment);
            }
        }
        return results;
    }
}