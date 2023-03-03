package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.filereader.LogFileWriter;
import com.techelevator.filereader.SalesReport;
import com.techelevator.management.Customer;
import com.techelevator.management.MachineInventory;

import java.math.BigDecimal;

public class ApplicationCLI {
    private Menu menu;
    private LogFileWriter auditLogger;
    private SalesReport salesReport;
    private InventoryFileReader inventoryFileReader;
    private InventoryFileReader salesFileReader;
    private MachineInventory inventory;

    public static void main(String[] args) {
        ApplicationCLI cli = new ApplicationCLI();
        cli.run();
    }

    private ApplicationCLI() {
        this.menu = new Menu();
        //audit log actions to desired document
        this.auditLogger = new LogFileWriter("Log.txt");

        //read in an inventory for the machine, done once per run of the application
        inventoryFileReader = new InventoryFileReader("inventory.csv");
        inventory = new MachineInventory(inventoryFileReader.makeInventory());

        //read in an inventory that represents the sales report csv file
        salesFileReader = new InventoryFileReader("TotalSales.rpt");
        salesReport = new SalesReport(salesFileReader.makeSalesInventory(), "TotalSales.rpt");
    }

    public void run() {

        while (true) {
            int choice = menu.firstMenuMessage();
            if (choice == 1) {

                //Here we want to print the current available inventory of the machine. Accounting properly for
                //any purchases made by users
                showInventory();

            } else if (choice == 2) {

                //Instantiate a customer to use during this portion of the program
                Customer customer = new Customer(inventoryFileReader.makeInventory());

                //This while loop maintains the customer interaction, allowing them to deposit, purchase and checkout
                while (true) {

                    //Give user options, return their choice as an integer
                    int customerOperation = menu.makeSaleMessage(customer);

                    //If 1 is returned, provide them the method to attempt a deposit
                    if (customerOperation == 1) {
                        depositAction(customer);
                    }

                    //If 2 is returned, provide them the method to handle a purchase
                    else if (customerOperation == 2) {
                        makeSale(customer);
                    }

                    //Else we close their account, performing all actions involved with cashingout a customer
                    else {
                        closingOperations(customer);
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

    /*Helper method that prints the current inventory.*/
    public void showInventory() {
        menu.printInventory(inventory.getInventory());
    }

    /*This method handles the deposit action available to the customer.*/
    public void depositAction(Customer customer) {
        int deposit = menu.getDeposit();
        BigDecimal depositBD = new BigDecimal(deposit);
        //If customer deposit method accepted BigDecimal we can confirm action and log
        if (customer.deposit(depositBD)) {
            auditLogger.depositAudit(depositBD, customer.getBalance());
            menu.depositSuccessfulMessage();
        } else if (deposit != 0) {
            menu.depositUnsuccessfulMessage();
        } else {
            System.out.println();
        }
    }

    /*This method handle each purchase the customer attempts to make.*/
    private void makeSale(Customer customer) {
        showInventory();
        String chosenSKU = menu.getSKU();
        boolean hasProductFromChosenID = inventory.idExists(chosenSKU);
        if (hasProductFromChosenID) {
            validSKU(chosenSKU, customer);
        } else {
            menu.inValidIdMsg();
        }
    }

    /*This method handles validating the selected SKU*/
    public void validSKU(String chosenSKU, Customer customer) {

        int quantityChosen = menu.quantityChosen();
        boolean quantityAvailable = (inventory.quantityOfSku(chosenSKU) >= quantityChosen && quantityChosen > 0);
        BigDecimal costOfItems = BigDecimal.valueOf(quantityChosen).multiply(inventory.priceOfSku(chosenSKU));
        boolean customerCanAfford = customer.getBalance().compareTo(costOfItems) >= 0;
        boolean purchasePossible = quantityAvailable && customerCanAfford;

        if (purchasePossible) {
            customer.addItemToCart(inventory.getAccessibleInventory().get(chosenSKU), quantityChosen, costOfItems);
            inventory.removeItem(chosenSKU, quantityChosen);
            auditLogger.purchaseAudit(quantityChosen, inventory.nameOfSku(chosenSKU), chosenSKU, costOfItems, customer.getBalance());
            salesReport.saleReportAdd(quantityChosen, inventory.nameOfSku(chosenSKU), chosenSKU, costOfItems);
        } else if (!quantityAvailable) {
            menu.notEnoughQuantityAvailable();
        } else {
            menu.notEnoughBalanceToPurchase();
        }
    }

    /*This method reset the program as a use decides to finalize their transaction.*/
    public void closingOperations(Customer customer) {
        auditLogger.balanceAudit(customer.getBalance());
        menu.printReceipt(customer.getCustomerCart());
        menu.printChangeStream(customer.getBalance());
    }



}
