package com.techelevator.items;

import java.math.BigDecimal;

public class Licorice extends Item {
    final private String DESCRIPTION = "Licorice and Jellies";


    public Licorice(String name, String wrappedChar, int quantity, BigDecimal price, String sku) {
        super(name, wrappedChar, quantity, price, sku);
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }
}
