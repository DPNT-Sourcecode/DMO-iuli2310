package io.accelerate.solutions.DMO;

import java.util.HashMap;
import java.util.Map;

public class DemoRound3Solution {

    // Using a map to store inventory items with SKU as the key
    private final Map<String, InventoryRecord> inventory = new HashMap<>();

    // Task 1: Add items to inventory and track quantity
    public int inventoryAdd(InventoryItem item, int quantity) {
        if (item == null || quantity <= 0) {
            throw new IllegalArgumentException("Item cannot be null and quantity must be greater than 0");
        }

        // Retrieve existing record or create a new one
        InventoryRecord record = inventory.computeIfAbsent(item.sku(), sku -> new InventoryRecord(item, 0));

        // Update the quantity for the item
        record.quantity += quantity;

        // Return the current quantity of the item after the operation
        return record.quantity;
    }

    // Task 2: Count the number of unique items in inventory
    public int inventorySize() {
        return inventory.size();
    }

    // Task 3: Retrieve an item by SKU
    public InventoryItem inventoryGet(String sku) {
        if (sku == null || sku.isEmpty()) {
            throw new IllegalArgumentException("SKU cannot be null or empty");
        }

        // Retrieve the record from the map
        InventoryRecord record = inventory.get(sku);

        // Return the InventoryItem if found, otherwise return null
        return (record != null) ? record.item : null;
    }

    // Helper class to store item and its quantity
    private static class InventoryRecord {
        private final InventoryItem item;
        private int quantity;

        InventoryRecord(InventoryItem item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }
    }
}