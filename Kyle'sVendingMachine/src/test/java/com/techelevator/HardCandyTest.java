package com.techelevator;

import com.techelevator.items.HardCandy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class HardCandyTest {
    private HardCandy hardCandy;


    @Before
    public void setup(){
        hardCandy = new HardCandy("name", "T", 1, BigDecimal.valueOf(1), "sku");
    }

    @Test
    public void getDescriptionTest(){
        String result = hardCandy.getDESCRIPTION();
        String expected = "Hard Tack Confectionary";
        Assert.assertEquals(expected, result);
    }
}
