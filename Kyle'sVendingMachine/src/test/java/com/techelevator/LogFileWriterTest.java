package com.techelevator;

import com.techelevator.filereader.LogFileWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

public class LogFileWriterTest {
    private LogFileWriter logFileWriter;
    private Scanner scanner;
    private PrintWriter printWriter;

    @Before
    public void setup() throws FileNotFoundException {
        logFileWriter = new LogFileWriter("LogFileWriterTest.txt");
        File file = new File("LogFileWriterTest.txt");
        scanner = new Scanner(file);
        printWriter = new PrintWriter(file);
    }

    @Test
    public void deposit_audit_test(){
        logFileWriter.depositAudit(BigDecimal.valueOf(100), BigDecimal.valueOf(200));
        printWriter.println();
        String expected = scanner.nextLine().substring(19);
        Assert.assertEquals("PM MONEY RECEIVED: $100.00 $200.00", expected);
    }

    @Test
    public void purchase_audit_test(){
        logFileWriter.purchaseAudit(10,"Name", "L5", BigDecimal.valueOf(100), BigDecimal.valueOf(200));
        printWriter.println();
        String expected = scanner.nextLine().substring(19);
        Assert.assertEquals("PM 10 Name L5 $100.00 $200.00", expected);
    }

    @Test
    public void balance_audit_test(){
        logFileWriter.balanceAudit(BigDecimal.valueOf(100));
        printWriter.println();
        String expected = scanner.nextLine().substring(19);
        Assert.assertEquals("PM CHANGE GIVEN: $100.00 $0.00", expected);
    }




}
