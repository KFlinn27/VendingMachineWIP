package com.techelevator;

import com.techelevator.management.Customer;

import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {
    private Scanner userInput = new Scanner(System.in);


    public Menu(){}


    public int firstMenuMessage(){
        System.out.println("----------------------------");
        System.out.println("What would you like to do?");
        System.out.println();
        int choice = 0;
        while(true) {
            System.out.println("(1) Show Inventory");
            System.out.println("(2) Make Sale");
            System.out.println("(3) Quit");
            System.out.print("Please enter choice: ");
            try{
                choice = Integer.parseInt(userInput.nextLine());
                if(choice == 1 || choice == 2 || choice == 3){
                    break;
                }
                else{
                    System.out.println();
                    System.out.println("---------------------------------");
                    System.out.println("You did not enter a valid choice.");
                    System.out.println("---------------------------------");
                    System.out.println();
                }
            } catch (NumberFormatException e){
                System.out.println();
                System.out.println("-----------------------------------------------");
                System.out.println("You did not enter a whole number, ie 1, 2 or 3.");
                System.out.println("-----------------------------------------------");
                System.out.println();
            }
        }
        return choice;
    }

    public String getID() {
        System.out.print("Please enter ID code of item you wish to purchase: ");
        String selection = userInput.nextLine();
        selection = selection.toUpperCase();
        return selection;
    }

    public int quantityChosen(){
        System.out.print("Please enter amount of item you wish to purchase: ");

        try{
        int quantityForPurchase = Integer.parseInt(userInput.nextLine());
            return quantityForPurchase;
        } catch (NumberFormatException e) {
            System.out.println("Input was not a whole number.");
        }
        return 101;
    }


    public int makeSaleMessage(Customer customer){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("What would you like to do?");
        System.out.println();
        int choice = 0;
        while(true) {
            System.out.println("(1) Deposit Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Complete Sale");
            System.out.println("Current Customer Balance: $" + customer.getBalance());
            System.out.println();
            System.out.print("Please enter choice: ");
            try{
                choice = Integer.parseInt(userInput.nextLine());
                if(choice == 1 || choice == 2 || choice == 3){
                    break;
                }
                else{
                    System.out.println();
                    System.out.println("---------------------------------");
                    System.out.println("You did not enter a valid choice.");
                    System.out.println("---------------------------------");
                    System.out.println();
                }
            } catch (NumberFormatException e){
                System.out.println();
                System.out.println("-----------------------------------------------");
                System.out.println("You did not enter a whole number, ie 1, 2 or 3.");
                System.out.println("-----------------------------------------------");
                System.out.println();
            }
        }
        return choice;
    }

    public int getDeposit(BigDecimal currentBalance){
        //returns deposit amount if the amount is a valid deposit
        int deposit = 0;
        try{
            while(true) {
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------");
                System.out.print("Please enter how much you would like to deposit, up to $100, in whole numbers: ");
                deposit = Integer.parseInt(userInput.nextLine());

                System.out.println();

                BigDecimal bdDeposit = BigDecimal.valueOf(deposit);
                BigDecimal limit = new BigDecimal(1000.00);
                BigDecimal balanceAfterDeposit = bdDeposit.add(currentBalance);
                //if canDeposit = -1 or 0, deposited amount is less than or equally limit, if 1 over limit
                int canDeposit = balanceAfterDeposit.compareTo(limit);
                if(deposit <= 100 && deposit > 0 && canDeposit < 1){
                    System.out.println("$" + deposit + " was added to your balance, thanks.");
                    System.out.println();
                    break;
                }
                else if(deposit > 0 && deposit <= 100){
                    BigDecimal moneyCanAdd = BigDecimal.valueOf(deposit);
                    if(limit.subtract(currentBalance).compareTo(BigDecimal.valueOf(100)) > 0){
                        moneyCanAdd = BigDecimal.valueOf(100);
                    }
                    else{
                        moneyCanAdd = limit.subtract(currentBalance);
                    }
                    //deposited too much
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------------" +
                            "--------------------------------------------------------------------------");
                    System.out.println("You attempted to deposit too much, your current balance can only accept $" +
                            moneyCanAdd + ". Please enter that amount or lower only.");
                    System.out.println("--------------------------------------------------------------------------" +
                            "--------------------------------------------------------------------------");
                    System.out.println();
                }
                else{
                    System.out.println("This is not a valid deposit amount, please enter whole number between 1-100.");
                }


            }
        } catch (NumberFormatException e){
            System.out.println("You did not enter a whole number.");
        }


        return deposit;
    }




}
