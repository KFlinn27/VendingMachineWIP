package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;

public class InventoryFileReaderTest {
    private InventoryFileReader inventoryFileReader;
    private InventoryFileReader salesFileReader;

    @Before
    public void setup() throws FileNotFoundException {
    inventoryFileReader = new InventoryFileReader("inventoryTest.txt");
    salesFileReader = new InventoryFileReader("testSales.txt");

    }
    // name , wrapped, quantity, price, sku
    @Test
    public void readFileVal1(){
        Map<String, Item> expected = inventoryFileReader.makeInventory();
        Assert.assertEquals("Flat Test", expected.get("C2").getName());
        Assert.assertEquals("C2", expected.get("C2").getSku());
        Assert.assertEquals(4, expected.size());
        Assert.assertEquals("Y", expected.get("C2").getIndividuallyWrapped());
        Assert.assertEquals(100, expected.get("C2").getQuantity());
        Assert.assertEquals(BigDecimal.valueOf(10.80), expected.get("C2").getPrice());

    }

    @Test
    public void makeInventoryVal2(){
        Map<String, Item> expected = inventoryFileReader.makeInventory();
        Assert.assertEquals("Dylan's Shoes", expected.get("S2").getName());
        Assert.assertEquals("S2", expected.get("S2").getSku());
        Assert.assertEquals(4, expected.size());
        Assert.assertEquals("N", expected.get("S2").getIndividuallyWrapped());
        Assert.assertEquals(100, expected.get("S2").getQuantity());
        Assert.assertEquals(BigDecimal.valueOf(2.70), expected.get("S2").getPrice());

    }
    @Test
    public void readFileVal3(){
        Map<String, Item> expected = inventoryFileReader.makeInventory();
        Assert.assertEquals("Bob's Burgers", expected.get("H1").getName());
        Assert.assertEquals("H1", expected.get("H1").getSku());
        Assert.assertEquals(4, expected.size());
        Assert.assertEquals("Y", expected.get("H1").getIndividuallyWrapped());
        Assert.assertEquals(100, expected.get("H1").getQuantity());
        Assert.assertEquals(BigDecimal.valueOf(2.35), expected.get("H1").getPrice());
    }

    @Test
    public void readFileVal4(){
        Map<String, Item> expected = inventoryFileReader.makeInventory();
        Assert.assertEquals("Dracula Piller", expected.get("L5").getName());
        Assert.assertEquals("L5", expected.get("L5").getSku());
        Assert.assertEquals(4, expected.size());
        Assert.assertEquals("N", expected.get("L5").getIndividuallyWrapped());
        Assert.assertEquals(100, expected.get("L5").getQuantity());
        Assert.assertEquals(BigDecimal.valueOf(1.11), expected.get("L5").getPrice());
    }

    //[0] = sku, [1] = name, [2] = quantity, [3] = totalSales
    @Test
    public void saleInventory1(){
        Map<String, Item> expected = salesFileReader.makeSalesInventory();
        Assert.assertEquals(4, expected.size());
        Assert.assertEquals("Blarg", expected.get("L5").getName());
        Assert.assertEquals("L5", expected.get("L5").getSku());
        Assert.assertEquals(65, expected.get("L5").getQuantity());
        Assert.assertEquals(BigDecimal.valueOf(300.00), expected.get("L5").getPrice());
    }

}
