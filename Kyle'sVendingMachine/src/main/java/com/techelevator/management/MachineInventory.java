package com.techelevator.management;

import com.techelevator.items.Item;

import java.math.BigDecimal;
import java.util.*;

public class MachineInventory {
    private TreeMap<String, Item> inventory;

    public TreeMap<String, Item> getInventory() {
        return inventory;
    }

    public MachineInventory(TreeMap<String, Item> inventoryRead){
        this.inventory = inventoryRead;
    }

    public void removeItem(String sku, int amountToRemove){
        //go into access and remove item, if available
        System.out.println();
        int availableQuantity = inventory.get(sku).getQuantity();
        int updatedQuantity = availableQuantity - amountToRemove;
        if(updatedQuantity < 0){
            updatedQuantity = 0;
        }
        inventory.get(sku).setQuantity(updatedQuantity);

    }

    public boolean idExists(String sku){
        return inventory.containsKey(sku);
    }

    public String nameOfSku(String sku) {
        return inventory.get(sku).getName();
    }
    public BigDecimal priceOfSku(String sku){
        return inventory.get(sku).getPrice();
    }

    public int quantityOfSku(String sku){
        return inventory.get(sku).getQuantity();
    }

    public Map<String, Item> getAccessibleInventory() {
        return inventory;
    }
}

