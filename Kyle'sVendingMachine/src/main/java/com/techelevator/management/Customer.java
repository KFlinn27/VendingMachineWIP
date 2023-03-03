package com.techelevator.management;

import com.techelevator.items.Item;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Customer {
    private final BigDecimal DEPOSIT_MIN = new BigDecimal(1);
    private final BigDecimal DEPOSIT_MAX = new BigDecimal(100);
    private final BigDecimal BALANCE_MAX = new BigDecimal(1000);
    private Map<String, Item> customerCart;
    private BigDecimal balance = new BigDecimal(0);

    //constructor
    public Customer(Map<String, Item> thisMap) {
        customerCart = thisMap;
        for (Map.Entry<String, Item> entry : customerCart.entrySet()) {
            entry.getValue().setQuantity(0);
        }
    }

    public boolean deposit(BigDecimal deposit){
        boolean depositOutsideMinMax = deposit.compareTo(DEPOSIT_MIN) < 0 || deposit.compareTo(DEPOSIT_MAX) > 0;
        boolean depositPlusBalanceLessThanMax = deposit.add(balance).compareTo(BALANCE_MAX) > 0;
        if(depositOutsideMinMax || depositPlusBalanceLessThanMax){
            return false;
        }
        else {
            balance = balance.add(deposit);
            return true;
        }
    }

    //Method
    public void addItemToCart(Item newItem, int amountToAdd, BigDecimal costOfItems) {
        //go into access and remove item, if available

        this.balance = this.balance.subtract(costOfItems);
        if (customerCart.containsKey(newItem.getSku())) {
            int currentQuantity = customerCart.get(newItem.getSku()).getQuantity();
            customerCart.get(newItem.getSku()).setQuantity(amountToAdd + currentQuantity);
        } else {
            customerCart.put(newItem.getSku(), newItem);
            customerCart.get(newItem.getSku()).setQuantity(amountToAdd);
        }
    }


    public void addToBalance(BigDecimal deposit) {
        this.balance = balance.add(deposit);
    }

    public Map<String, Item> getCustomerCart() {
        return customerCart;
    }

    //Get & Set
    public BigDecimal getBalance() {
        return balance;
    }


}
