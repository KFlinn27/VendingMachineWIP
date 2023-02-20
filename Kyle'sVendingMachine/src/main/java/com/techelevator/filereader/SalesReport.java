package com.techelevator.filereader;

import com.techelevator.items.Item;
import com.techelevator.items.Sours;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class SalesReport {
    private Map<String, Item> salesMap;
    private File fileToWrite;
    private BufferedWriter bufferedWriter;

    public SalesReport(Map<String, Item> salesMap, String path) {
        this.salesMap = salesMap;
        fileToWrite = new File(path);
    }


    public void salesReportWrite() {
        try (FileWriter writer = new FileWriter(fileToWrite, false); BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            for(Map.Entry<String, Item> item : salesMap.entrySet()) {
                String toWrite = item.getKey() + "|" + item.getValue().getName() + "|" + item.getValue().getQuantity() + "|" + currency.format(item.getValue().getPrice());
                bufferedWriter.write(toWrite);
                bufferedWriter.newLine();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saleReportAdd(int quantity, String name, String sku, BigDecimal subtotal) {
        Item itemCurrent = new Sours(name, "", quantity, subtotal, sku);
        if(salesMap.containsKey(sku)){
             BigDecimal updatedPrice= salesMap.get(sku).getPrice().add(subtotal);
             salesMap.get(sku).setPrice(updatedPrice);

             int updatedQuantity = salesMap.get(sku).getQuantity() + quantity;
             salesMap.get(sku).setQuantity(updatedQuantity);
        } else{
            salesMap.put(sku, itemCurrent);
        }

    }


}
