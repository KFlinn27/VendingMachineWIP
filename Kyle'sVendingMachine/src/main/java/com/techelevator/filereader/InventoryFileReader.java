package com.techelevator.filereader;

import com.techelevator.items.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This class would be a GREAT place to read in a file and return a data structure matching the one in
 * your Inventory class. (You probably saw something similiar in some review code we did....)
 */
public class InventoryFileReader {
    private File fileToRead;
    private Scanner fileScanner;


    public InventoryFileReader(String path) {
        this.fileToRead = new File(path);
    }

    public TreeMap<String, Item> makeInventory() {
        TreeMap<String, Item> itemMap = new TreeMap<>();
        try (Scanner fileScanner = new Scanner(fileToRead)) {

            while (fileScanner.hasNextLine()) {
                String[] itemArr = fileScanner.nextLine().split("\\|");
                Item current = null;
                //[0] = type, [1] = sku, [2] = name, [3] = price, [4] = wrapped
                if(itemArr.length == 5) {
                    if (itemArr[0].equalsIgnoreCase("ch")) {
                        current = new Chocolates(itemArr[2], itemArr[4], 100, BigDecimal.valueOf(Double.parseDouble(itemArr[3])), itemArr[1]);
                    } else if (itemArr[0].equalsIgnoreCase("sr")) {
                        current = new Sours(itemArr[2], itemArr[4], 100, BigDecimal.valueOf(Double.parseDouble(itemArr[3])), itemArr[1]);
                    } else if (itemArr[0].equalsIgnoreCase("hc")) {
                        current = new HardCandy(itemArr[2], itemArr[4], 100, BigDecimal.valueOf(Double.parseDouble(itemArr[3])), itemArr[1]);
                    } else if (itemArr[0].equalsIgnoreCase("li")) {
                        current = new Licorice(itemArr[2], itemArr[4], 100, BigDecimal.valueOf(Double.parseDouble(itemArr[3])), itemArr[1]);
                    } // name , wrapped, quantity, price, sku
                    if (!current.equals(null)) {
                        itemMap.put(current.getSku(), current);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return itemMap;
    }

    public Map<String, Item> makeSalesInventory() {
        Map<String, Item> itemMap = new HashMap<>();
        try (Scanner fileScanner = new Scanner(fileToRead)) {

            while (fileScanner.hasNextLine() && fileToRead.length() > 0) {

                String[] itemArr = fileScanner.nextLine().split("\\|");
                if(itemArr.length == 4) {
                    BigDecimal price = new BigDecimal(0);
                    // price = BigDecimal.valueOf(Long.parseLong(itemArr[3])).divide(BigDecimal.valueOf(Long.parseLong(itemArr[2])));
                    Item current = new Sours(itemArr[1], "", Integer.parseInt(itemArr[2]), BigDecimal.valueOf(Double.parseDouble(itemArr[3].substring(1))), itemArr[0]);
                    itemMap.put(itemArr[0], current);
                    //[0] = sku, [1] = name, [2] = quantity, [3] = totalSales
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return itemMap;
    }
}
