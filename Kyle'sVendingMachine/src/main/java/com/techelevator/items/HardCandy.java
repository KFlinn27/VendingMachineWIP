package com.techelevator.items;

import java.math.BigDecimal;

public class HardCandy extends Item {
    final private String DESCRIPTION = "Hard Tack Confectionary";

    public HardCandy(String name, String wrappedChar, int quantity, BigDecimal price, String sku) {
        super(name, wrappedChar, quantity, price, sku);
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

}
