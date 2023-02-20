package com.techelevator;

import com.techelevator.items.Chocolates;
import com.techelevator.items.HardCandy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ChocolatesTest {
    private Chocolates chocolates;


    @Before
    public void setup(){
        chocolates = new Chocolates("name", "T", 1, BigDecimal.valueOf(1), "sku");
    }

    @Test
    public void getDescriptionTest(){
        String result = chocolates.getDESCRIPTION();
        String expected = "Chocolate Confectionary";
        Assert.assertEquals(expected, result);
    }



}
