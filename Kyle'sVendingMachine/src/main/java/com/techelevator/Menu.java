package com.techelevator;

import com.techelevator.items.Item;
import com.techelevator.management.Customer;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {
    private Scanner userInput = new Scanner(System.in);
    private NumberFormat currency = NumberFormat.getCurrencyInstance();
    private final String DASH_BREAK = "-----------------------------------------------------------------------------";


    public Menu() {
    }


    public int firstMenuMessage() {
        System.out.println(DASH_BREAK);
        System.out.println("What would you like to do?");
        System.out.println();
        int choice = 0;
        while (true) {
            System.out.println("(1) Show Inventory");
            System.out.println("(2) Make Sale");
            System.out.println("(3) Quit");
            System.out.print("Please enter choice: ");
            try {
                choice = Integer.parseInt(userInput.nextLine());
                if (choice == 1 || choice == 2 || choice == 3) {
                    break;
                } else {
                    System.out.println();
                    System.out.println(DASH_BREAK);
                    System.out.println("You did not enter a valid choice.");
                    System.out.println(DASH_BREAK);
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println(DASH_BREAK);
                System.out.println("You did not enter a whole number, ie 1, 2 or 3.");
                System.out.println(DASH_BREAK);
                System.out.println();
            }
        }
        return choice;
    }

    public int makeSaleMessage(Customer customer) {
        System.out.println(DASH_BREAK);
        System.out.println("What would you like to do?");
        System.out.println();
        int choice = 0;
        while (true) {
            System.out.println("(1) Deposit Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Complete Sale");
            System.out.println("Current Customer Balance: "+ currency.format(customer.getBalance()));
            System.out.println();
            System.out.print("Please enter choice: ");
            try {
                choice = Integer.parseInt(userInput.nextLine());
                if (choice == 1 || choice == 2 || choice == 3) {
                    break;
                } else {
                    System.out.println();
                    System.out.println(DASH_BREAK);
                    System.out.println("You did not enter a valid choice.");
                    System.out.println(DASH_BREAK);
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println(DASH_BREAK);
                System.out.println("You did not enter a whole number, ie 1, 2 or 3.");
                System.out.println(DASH_BREAK);
                System.out.println();
            }
        }
        return choice;
    }

    public int getDeposit() {
        //returns deposit amount if the amount is a valid deposit
        int deposit = 0;
        try {

            System.out.println();
            System.out.println(DASH_BREAK);
            System.out.println();
            System.out.println("You may deposit any whole number between $1 and $100, to a maximum balance of $1,000.");
            System.out.print("Please enter your deposit here(Enter 0 to avoid depositing):");
            deposit = Integer.parseInt(userInput.nextLine());

        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("You did not enter a whole number.");
        }


        return deposit;
    }

    public String getSKU() {
        System.out.println();
        System.out.print("Please enter ID code of item you wish to purchase: ");
        String selection = userInput.nextLine();
        selection = selection.toUpperCase();
        return selection;
    }

    public int quantityChosen() {
        System.out.print("Please enter amount of item you wish to purchase: ");

        try {
            int quantityForPurchase = Integer.parseInt(userInput.nextLine());
            return quantityForPurchase;
        } catch (NumberFormatException e) {
            System.out.println("Input was not a whole number.");
        }
        return 101;
    }





    public void depositSuccessfulMessage() {
        System.out.println();
        System.out.println("You deposit was successful, thank you!");
        System.out.println();
    }

    public void depositUnsuccessfulMessage() {
        System.out.println();
        System.out.println("Your deposit was unsuccessful, you may only deposit up to $100 in whole numbers and no " +
                "more than $1,000 can be in your balance.");
        System.out.println();
    }

    public void printInventory(TreeMap<String, Item> inventory) {
        System.out.printf("%-6s %-22s %-10s %-10s PRICE\n", "ID", "NAME", "WRAPPER", "QUANTITY");
        for (Map.Entry<String, Item> current : inventory.entrySet()) {
            String quantity = String.valueOf(current.getValue().getQuantity());
            if (current.getValue().getQuantity() == 0) {
                quantity = "SOLD OUT";
            }
            System.out.printf("%-6s %-22s %-10s %-10s $%.2f\n", current.getKey(), current.getValue().getName(),
                    current.getValue().getIndividuallyWrapped(), quantity, current.getValue().getPrice());
        }
    }

    public void inValidIdMsg() {
        System.out.println("The ID provided is not valid.");
    }

    public void notEnoughBalanceToPurchase() {
        System.out.println("You did not have enough money to purchase the desired items.");
    }

    public void notEnoughQuantityAvailable() {
        System.out.println("There's not enough quantity.");
    }

    public void printReceipt(Map<String, Item> customerCart) {
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

    public void printChangeStream(BigDecimal balance) {
        System.out.println("Change: $" + balance);

        if (balance.compareTo(BigDecimal.valueOf(0)) == 0) {
            return;
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
        Arrays.stream(printChange)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));
    }
}
