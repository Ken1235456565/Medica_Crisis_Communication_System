/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Supplies;

/**
 *
 * @author tiankaining
 */
    public class DonatedItem {

        private String name;
        private int quantity;
        private double unitPrice;

        public DonatedItem(String name, int quantity, double unitPrice) {
            this.name = name;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getTotalItemValue() {
            return quantity * unitPrice;
        }

        @Override
        public String toString() {
            return name + " (Qty: " + quantity + ", Price: $" + unitPrice + ")";
        }
    }
