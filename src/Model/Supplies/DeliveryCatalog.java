package Model.Supplies;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of Delivery objects.
 */
public class DeliveryCatalog {

    private List<Delivery> deliveryList;

    public DeliveryCatalog() {
        this.deliveryList = new ArrayList<>();
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public void addDelivery(Delivery delivery) {
        this.deliveryList.add(delivery);
    }

    public void removeDelivery(Delivery delivery) {
        this.deliveryList.remove(delivery);
    }

    public Delivery findDeliveryById(String id) {
        for (Delivery delivery : deliveryList) {
            if (delivery.getDeliveryId().equals(id)) {
                return delivery;
            }
        }
        return null;
    }

    public List<Delivery> findDeliveriesByStatus(String status) {
        List<Delivery> results = new ArrayList<>();
        for (Delivery delivery : deliveryList) {
            if (delivery.getStatus().equals(status)) {
                results.add(delivery);
            }
        }
        return results;
    }
public List<Delivery> getDeliveredTasksForUser(String username) {
    List<Delivery> results = new ArrayList<>();
    for (Delivery delivery : deliveryList) {
        if (delivery.getDriverName() != null &&
            delivery.getDriverName().equals(username) &&
            delivery.getStatus().equalsIgnoreCase("Delivered")) {
            results.add(delivery);
        }
    }
    return results;
}


}