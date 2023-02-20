package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.filereader.LogFileWriter;
import com.techelevator.filereader.SalesReport;
import com.techelevator.items.Item;
import com.techelevator.management.Customer;
import com.techelevator.management.InventorySalesTracker;

import java.math.BigDecimal;
import java.util.*;

public class ApplicationCLI {

    // probably should leave this method alone... and go do stuff in the run method....
    public static void main(String[] args) {
        ApplicationCLI cli = new ApplicationCLI();
        cli.run();
    }

    /**
     * This is the main method that controls the flow of the program.. Probably could look at the review code for ideas of what to put here...
     */
    public void run() {
        Menu menu = new Menu();
        InventoryFileReader inventoryFileReader = new InventoryFileReader("inventory.csv");
        LogFileWriter auditLogger = new LogFileWriter("Log.txt");
        InventorySalesTracker inventory = new InventorySalesTracker(inventoryFileReader.makeInventory());
        InventoryFileReader salesFileReader = new InventoryFileReader("TotalSales.rpt");
        SalesReport salesReport = new SalesReport(salesFileReader.makeSalesInventory(), "TotalSales.rpt");

        while (true) {
            int choice = menu.firstMenuMessage();
            if (choice == 1) {
                inventory.printInventory();
            } else if (choice == 2) {
                Customer customer = new Customer(inventoryFileReader.makeInventory());
                while (true) {
                    int customerOperation = menu.makeSaleMessage(customer);
                    if (customerOperation == 1) {
                        depositAction(menu, customer, auditLogger);
                    } else if (customerOperation == 2) {
                        inventory.printInventory();
                        System.out.println();
                        String chosenID = menu.getID();
                        boolean hasProductFromChosenID = inventory.getAccessibleInventory().containsKey(chosenID);
                        if (hasProductFromChosenID) {
                            validID(menu, inventory, chosenID, auditLogger, customer, salesReport);
                        } else {
                            System.out.println("That ID is not valid.");
                        }
                    } else {
                        closingOperations(auditLogger, customer);
                        break;
                    }
                }
            } else {

                System.out.println("Thanks for using our product.");
                break;
            }

        }
        salesReport.salesReportWrite();
    }

    public void closingOperations(LogFileWriter auditLogger, Customer customer) {
        auditLogger.balanceAudit(customer.getBalance());
        customer.printReceipt();

        //Refactored printChange, need to implement once both users push to a merged state
        System.out.println(customer.printChangeStream());
    }

    public void validID(Menu menu, InventorySalesTracker inventory, String chosenID, LogFileWriter auditLogger, Customer customer,
    SalesReport salesReport){

        int quantityChosen = menu.quantityChosen();
        boolean quantityAvailable = (inventory.getAccessibleInventory().get(chosenID).getQuantity() >= quantityChosen && quantityChosen > 0);
        BigDecimal costOfItems = BigDecimal.valueOf(quantityChosen).multiply(inventory.getAccessibleInventory().get(chosenID).getPrice());
        int hasEnoughFunds = customer.getBalance().compareTo(costOfItems);
        boolean hasFunds = hasEnoughFunds >= 0;

        if (quantityAvailable && hasFunds) {
            customer.addItemToCart(inventory.getAccessibleInventory().get(chosenID), quantityChosen, costOfItems);
            inventory.removeItem(chosenID, quantityChosen);
            //Do audit log
            auditLogger.purchaseAudit(quantityChosen, inventory.getAccessibleInventory().get(chosenID).getName(), chosenID, costOfItems, customer.getBalance());
            salesReport.saleReportAdd(quantityChosen, inventory.getAccessibleInventory().get(chosenID).getName(), chosenID, costOfItems);
        } else if (!quantityAvailable) {
            System.out.println("There's not enough quantity.");
        } else if (!hasFunds) {
            System.out.println("You're broke!!");
        } else {
            System.out.println("There was an error. Please try again.");
        }
    }

    public void depositAction(Menu menu, Customer customer, LogFileWriter auditLogger){
        int deposit = menu.getDeposit(customer.getBalance());
        BigDecimal depositBD = new BigDecimal(deposit);
        customer.addToBalance(depositBD);
        auditLogger.depositAudit(depositBD, customer.getBalance());
    }
}
