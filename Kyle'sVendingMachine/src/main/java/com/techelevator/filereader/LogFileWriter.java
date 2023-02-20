package com.techelevator.filereader;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * This would be a GREAT place to have a public method that could take a formatted String and log it out to a file.
 */
public class LogFileWriter {

    private File fileToWrite;
    private BufferedWriter bufferedWriter;

    public LogFileWriter(String path) {
        fileToWrite = new File(path);

    }

    public void depositAudit(BigDecimal deposit, BigDecimal newBalance) {
        LocalDate datePurchased = LocalDate.now();
        int year = datePurchased.getYear();
        int month = datePurchased.getMonthValue();
        int day = datePurchased.getDayOfMonth();
        String dateToPrint = day + "/" + month + "/" + year;
        Date time = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss aa");
        String timeFormatted = formatTime.format(time);
        try (FileWriter writer = new FileWriter(fileToWrite, true); BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            String toWrite = dateToPrint + " " + timeFormatted + " MONEY RECEIVED: " + currency.format(deposit) + " " + currency.format(newBalance);
            bufferedWriter.append(toWrite);
            bufferedWriter.newLine();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found. Try again.");
        } catch (IOException e) {
            System.out.println("Error with creating writer.");
        }


    }

    public void purchaseAudit(int quantity, String name, String sku, BigDecimal subtotal, BigDecimal balance) {
        LocalDate datePurchased = LocalDate.now();
        int year = datePurchased.getYear();
        int month = datePurchased.getMonthValue();
        int day = datePurchased.getDayOfMonth();
        String dateToPrint = day + "/" + month + "/" + year;
        Date time = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss aa");
        String timeFormatted = formatTime.format(time);
        try (FileWriter writer = new FileWriter(fileToWrite, true); BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            String toWrite = dateToPrint + " " + timeFormatted + " " + quantity + " " + name + " " + sku + " " + currency.format(subtotal) + " " + currency.format(balance);
            bufferedWriter.append(toWrite);
            bufferedWriter.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void balanceAudit(BigDecimal balance) {
        LocalDate datePurchased = LocalDate.now();
        int year = datePurchased.getYear();
        int month = datePurchased.getMonthValue();
        int day = datePurchased.getDayOfMonth();
        String dateToPrint = day + "/" + month + "/" + year;

        Date time = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss aa");

        String timeFormatted = formatTime.format(time);
        String resetBalance = "$0.00";

        try (FileWriter writer = new FileWriter(fileToWrite, true); BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            String toWrite = dateToPrint + " " + timeFormatted + " CHANGE GIVEN: " + currency.format(balance) + " " + resetBalance;
            bufferedWriter.append(toWrite);
            bufferedWriter.newLine();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found. Try again.");
        } catch (IOException e) {
            System.out.println("Error with creating writer.");
        }
    }
}

