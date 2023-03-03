package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.management.MachineInventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MachineInventoryTests {

    private MachineInventory tester;
    private InventoryFileReader testInven = new InventoryFileReader("inventoryTEST.csv");

    @Before
    public void setup(){
        tester = new MachineInventory(testInven.makeInventory());
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
