package com.techelevator.management;

import com.techelevator.items.Item;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Customer {
    private Map<String, Item> customerCart;
    private BigDecimal balance = new BigDecimal(0);

    //constructor
    public Customer(Map<String, Item> thisMap) {
        customerCart = thisMap;
        for (Map.Entry<String, Item> entry : customerCart.entrySet()) {
            entry.getValue().setQuantity(0);
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

    public void printReceipt() {
        System.out.println();
        System.out.println("-------------------------------RECEIPT-----------------------------------------");
        System.out.printf("%-6s %-25s %-25s %-10s %-10s\n", "QTY", "NAME", "DESCRIPTION", "PRICE PER", "TOTAL PER");
        BigDecimal total = BigDecimal.valueOf(0);
        for (Map.Entry<String, Item> item : customerCart.entrySet()) {
            if (item.getValue().getQuantity() > 0) {
                BigDecimal totalPer = BigDecimal.valueOf(item.getValue().getQuantity()).multiply(item.getValue().getPrice());
                System.out.printf("%-6s %-25s %-25s $%.2f        $%.2f        \n", item.getValue().getQuantity(), item.getValue().getName(),
                        item.getValue().getDESCRIPTION(), item.getValue().getPrice(), totalPer);
                total = total.add(totalPer);
            }
        }
        System.out.println();
        System.out.println("Total: $" + total);
    }

    public String printChangeStream() {
        System.out.println("Change: $" + this.balance);

        if (this.balance.compareTo(BigDecimal.valueOf(0)) == 0) {
            return "No change required.";
        }

        LinkedHashMap<Double, String> changeMap = new LinkedHashMap<>();
        BigDecimal zero = BigDecimal.valueOf(0);
        changeMap.put(20., "Twenties");
        changeMap.put(10., "Tens");
        changeMap.put(5., "Fives");
        changeMap.put(1., "Ones");
        changeMap.put(.25, "Quarters");
        changeMap.put(.10, "Dimes");
        changeMap.put(.05, "Nickels");

        String[] printChange = new String[7];
        int indexCount = 0;
        int valCount = 0;

        for (Map.Entry<Double, String> item : changeMap.entrySet()) {
            while ((balance.subtract(BigDecimal.valueOf(item.getKey()))).compareTo(zero) >= 0) {
                valCount++;
                balance = balance.subtract(BigDecimal.valueOf(item.getKey()));
            }
            if (valCount > 0) {
                String changeString = "(" + valCount + ") " + item.getValue();
                printChange[indexCount] = changeString;
                indexCount++;
            }
            valCount = 0;
        }
        return Arrays.stream(printChange)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));
    }


    public String printChange(){

        /*Refactor this method using a linkedhashmap to order the values*/

        BigDecimal zero = BigDecimal.valueOf(0);
        int twenty = 0;
        int ten = 0;
        int five = 0;
        int one = 0;
        int quarter = 0;
        int dime = 0;
        int nickel = 0;
        System.out.println();
        System.out.println("Change: $" + this.balance);
        if(this.balance.compareTo(BigDecimal.valueOf(0)) == 0){
            return "No change required.";
        }

        String changeString = "";
        while((balance.subtract(BigDecimal.valueOf(20))).compareTo(zero)>= 0){
            twenty++;
            balance = balance.subtract(BigDecimal.valueOf(20));
        }
        if(twenty > 0){
            changeString = changeString.concat("(" + twenty + ") Twenties, ");
        }

        while((balance.subtract(BigDecimal.valueOf(10))).compareTo(zero)>= 0){
            ten++;
            balance = balance.subtract(BigDecimal.valueOf(10));
        }
        if(ten > 0){
            changeString = changeString.concat("(" + ten + ") Tens, ");
        }

        while((balance.subtract(BigDecimal.valueOf(5))).compareTo(zero)>= 0){
            five++;
            balance = balance.subtract(BigDecimal.valueOf(5));
        }
        if(five > 0){
            changeString = changeString.concat("(" + five + ") Fives, ");
        }

        while((balance.subtract(BigDecimal.valueOf(1))).compareTo(zero)>= 0){
            one++;
            balance = balance.subtract(BigDecimal.valueOf(1));
        }
        if(one > 0){
            changeString = changeString.concat("(" + one + ") Ones, ");
        }

        while((balance.subtract(BigDecimal.valueOf(.25))).compareTo(zero)>= 0){
            quarter++;
            balance = balance.subtract(BigDecimal.valueOf(.25));
        }
        if(quarter > 0){
            changeString = changeString.concat("(" + quarter + ") Quarters, ");
        }

        while((balance.subtract(BigDecimal.valueOf(.10))).compareTo(zero)>= 0){
            dime++;
            balance = balance.subtract(BigDecimal.valueOf(.10));
        }
        if(dime > 0){
            changeString = changeString.concat("(" + dime + ") Dimes, ");
        }

        while((balance.subtract(BigDecimal.valueOf(.05))).compareTo(zero)>= 0){
            nickel++;
            balance = balance.subtract(BigDecimal.valueOf(.05));
        }
        if(nickel > 0){
            changeString = changeString.concat("(" + nickel + ") Nickels");
        }


        return changeString;



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


    public String CanWeRefactor(){

        /*Refactor this method using a linkedhashmap to order the values*/
        LinkedHashMap<String, Integer> change = new LinkedHashMap<>();
        BigDecimal zero = BigDecimal.valueOf(0);
        change.put("Twenties", 0);
        change.put("Tens", 0);
        change.put("Fives", 0);
        change.put("Ones", 0);
        change.put("Quarters", 0);
        change.put("Dimes", 0);
        change.put("Nickels", 0);
        System.out.println();
        System.out.println("Change: $" + this.balance);
        if(this.balance.compareTo(BigDecimal.valueOf(0)) == 0){
            return "No change required.";
        }
        while((balance.subtract(BigDecimal.valueOf(20))).compareTo(zero)>= 0){
            change.put("Twenties", change.get("Twenties") + 1);
            balance = balance.subtract(BigDecimal.valueOf(20));
        }

        while((balance.subtract(BigDecimal.valueOf(10))).compareTo(zero)>= 0){
            change.put("Tens", change.get("Tens") + 1);
            balance = balance.subtract(BigDecimal.valueOf(10));
        }

        while((balance.subtract(BigDecimal.valueOf(5))).compareTo(zero)>= 0){
            change.put("Fives", change.get("Fives") + 1);
            balance = balance.subtract(BigDecimal.valueOf(5));
        }

        while((balance.subtract(BigDecimal.valueOf(1))).compareTo(zero)>= 0){
            change.put("Ones", change.get("Ones") + 1);
            balance = balance.subtract(BigDecimal.valueOf(1));
        }

        while((balance.subtract(BigDecimal.valueOf(.25))).compareTo(zero)>= 0){
            change.put("Quarters", change.get("Quarters") + 1);
            balance = balance.subtract(BigDecimal.valueOf(.25));
        }

        while((balance.subtract(BigDecimal.valueOf(.10))).compareTo(zero)>= 0){
            change.put("Dimes", change.get("Dimes") + 1);
            balance = balance.subtract(BigDecimal.valueOf(.10));
        }
        while((balance.subtract(BigDecimal.valueOf(.05))).compareTo(zero)>= 0){
            change.put("Nickels", change.get("Nickels") + 1);
            balance = balance.subtract(BigDecimal.valueOf(.05));
        }

        String printedChange = "";
        for(Map.Entry<String, Integer> current : change.entrySet()){
            if(current.getValue() > 0){
                printedChange = printedChange.concat("(" + current.getValue() + ") " + current.getKey() + ", ");
            }
        }
        printedChange = printedChange.substring(0, printedChange.length()-2);

        return printedChange;



    }


}
