package com.techelevator.items;

import java.math.BigDecimal;

public abstract class Item {
    private String name;
    private String individuallyWrapped;
    private int quantity;
    private BigDecimal price;
    private String sku;


    //constructor

    public Item(String name, String wrappedChar, int quantity, BigDecimal price, String sku) {
        this.name = name;
        if (wrappedChar.equalsIgnoreCase("T")) {
            individuallyWrapped = "Y";
        } else if(wrappedChar.equalsIgnoreCase("F")) {
            individuallyWrapped = "N";
        }
        this.quantity = quantity;
        this.price = price;
        this.sku = sku;
    }

    //Method

    public abstract String getDESCRIPTION();

    //Get & Set

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndividuallyWrapped() {
        return individuallyWrapped;
    }

    public void setIndividuallyWrapped(String individuallyWrapped) {
        this.individuallyWrapped = individuallyWrapped;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }
}
