package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.Item;
import com.techelevator.management.Customer;
import com.techelevator.management.InventorySalesTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InventorySalesTrackerTests {

    private InventorySalesTracker tester;
    private InventoryFileReader testInven = new InventoryFileReader("inventoryTEST.csv");

    @Before
    public void setup(){
        tester = new InventorySalesTracker(testInven.makeInventory());
    }

    @Test
    public void constructorSize(){
        int expected = 7;
        Assert.assertEquals(expected, tester.getAccessibleInventory().size());
    }

    @Test
    public void removeItemTestAll(){
        int expected = 0;
        tester.removeItem("C1", 100);
        Assert.assertEquals(expected, tester.getAccessibleInventory().get("C1").getQuantity());
    }
    @Test
    public void removeItemHalf(){
        int expected = 50;
        tester.removeItem("C1", 50);
        Assert.assertEquals(expected, tester.getAccessibleInventory().get("C1").getQuantity());
    }
    @Test
    public void removeItemDouble(){
        int expected = 0;
        tester.removeItem("C1", 200);
        Assert.assertEquals(expected, tester.getAccessibleInventory().get("C1").getQuantity());
    }
    @Test
    public void removeItemTestZero(){
        int expected = 100;
        tester.removeItem("C1", 0);
        Assert.assertEquals(expected, tester.getAccessibleInventory().get("C1").getQuantity());
    }




}
