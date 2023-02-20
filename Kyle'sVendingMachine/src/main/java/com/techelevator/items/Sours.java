package com.techelevator.items;

import java.math.BigDecimal;

public class Sours extends Item{
    final private String DESCRIPTION = "Sour Flavored Candies";

    public Sours(String name, String wrappedChar, int quantity, BigDecimal price, String sku) {
        super(name, wrappedChar, quantity, price, sku);
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }
}
