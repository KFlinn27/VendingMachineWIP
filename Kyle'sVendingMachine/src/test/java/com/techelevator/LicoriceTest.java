package com.techelevator;

import com.techelevator.items.Licorice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class LicoriceTest {
    private Licorice licorice;

    @Before
    public void setup(){
        licorice = new Licorice("name", "T", 1, BigDecimal.valueOf(1), "sku");
    }

    @Test
    public void getDescriptionTest(){
        String result = licorice.getDESCRIPTION();
        String expected = "Licorice and Jellies";
        Assert.assertEquals(expected, result);
    }
}
