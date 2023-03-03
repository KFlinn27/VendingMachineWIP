package com.techelevator;

import com.techelevator.filereader.SalesReport;
import com.techelevator.items.Chocolates;
import com.techelevator.items.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesReportTest {
    private SalesReport salesReport;
    private Scanner scanner;


    @Before
    public void setup() throws IOException {
        Item testItem = new Chocolates("Test Name", "Y", 50, BigDecimal.valueOf(9.5), "Z5");
        Map<String, Item> salesReportMap = new HashMap<>();
        salesReportMap.put("Z5", testItem);
        salesReport = new SalesReport(salesReportMap, "SalesReportTest.rpt");
        File file = new File("SalesReportTest.rpt");
        scanner = new Scanner(file);
    }

    @Test
    public void sales_report_write(){
        salesReport.salesReportWrite();
        String expected = scanner.nextLine();
        Assert.assertEquals("Z5|Test Name|50|$9.50", expected);
    }

    @Test
    public void sales_report_add(){
        salesReport.saleReportAdd(25, "Test Name", "Z5", BigDecimal.valueOf(10.00));
        salesReport.salesReportWrite();
        String expected = scanner.nextLine();
        Assert.assertEquals("Z5|Test Name|75|$19.50", expected);
    }



}
