package com.techelevator.items;

import java.math.BigDecimal;

public class Chocolates extends Item{
    final private String DESCRIPTION = "Chocolate Confectionary";


    public Chocolates(String name, String wrappedChar, int quantity, BigDecimal price, String sku) {
        super(name, wrappedChar, quantity, price, sku);
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }
}
