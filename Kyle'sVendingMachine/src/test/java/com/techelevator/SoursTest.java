package com.techelevator;

import com.techelevator.items.Sours;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class SoursTest {
    private Sours sours;

    @Before
    public void setup() {
        sours = new Sours("name", "T", 1, BigDecimal.valueOf(1), "sku");
    }

    @Test
    public void getDescriptionTest() {
        String result = sours.getDESCRIPTION();
        String expected = "Sour Flavored Candies";
        Assert.assertEquals(expected, result);
    }







}
