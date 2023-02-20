package com.techelevator.management;

import com.techelevator.items.Item;

import java.util.*;

public class InventorySalesTracker {

    private Map<String, Item> accessibleInventory;
    private List<String> sortedSKUs;

    public InventorySalesTracker(Map<String, Item> inventoryRead){
        this.accessibleInventory = inventoryRead;
        sortedSKUs = new ArrayList<>();
        for (Map.Entry<String, Item> item : accessibleInventory.entrySet()) {
            sortedSKUs.add(item.getKey());
        }
       Collections.sort(sortedSKUs);
    }

    public void removeItem(String sku, int amountToRemove){
        //go into access and remove item, if available
        System.out.println();
        int availableQuantity = accessibleInventory.get(sku).getQuantity();
        int updatedQuantity = availableQuantity - amountToRemove;
        if(updatedQuantity < 0){
            updatedQuantity = 0;
        }
        accessibleInventory.get(sku).setQuantity(updatedQuantity);

    }

    public void printInventory() {
        System.out.printf("%-6s %-22s %-10s %-10s PRICE\n", "ID", "NAME", "WRAPPER", "QUANTITY") ;
        for (String sku : sortedSKUs) {
            String quantity = String.valueOf(accessibleInventory.get(sku).getQuantity());
            if (accessibleInventory.get(sku).getQuantity() == 0) {
                quantity = "SOLD OUT";
            }
            System.out.printf("%-6s %-22s %-10s %-10s $%.2f\n", sku, accessibleInventory.get(sku).getName(),
                    accessibleInventory.get(sku).getIndividuallyWrapped(), quantity, accessibleInventory.get(sku).getPrice());
        }
    }

    public Map<String, Item> getAccessibleInventory() {
        return accessibleInventory;
    }
}

