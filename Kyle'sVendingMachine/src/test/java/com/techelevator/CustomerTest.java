package com.techelevator;

import com.techelevator.items.Item;
import com.techelevator.items.Sours;
import com.techelevator.management.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CustomerTest {
    Customer tester;

    @Before
    public void setup(){
        Map<String, Item> test = new HashMap<>();
        tester = new Customer(test);
    }


    @Test
    public void testAddBalancefirst100(){
        BigDecimal expected = BigDecimal.valueOf(100);
        tester.addToBalance(expected);

        Assert.assertEquals(expected, tester.getBalance());
    }

    @Test
    public void testAddBalance100to200(){
        BigDecimal expected = BigDecimal.valueOf(200);
        tester.addToBalance(BigDecimal.valueOf(100));
        tester.addToBalance(BigDecimal.valueOf(100));

        Assert.assertEquals(expected, tester.getBalance());
    }

    @Test public void addItemToCartEmpty() {
        Item testItem = new Sours("test", "T", 1, BigDecimal.valueOf(4.15), "C1");
        tester.addItemToCart(testItem, 10, BigDecimal.valueOf(50));
        tester.addToBalance(BigDecimal.valueOf(100));
        Assert.assertEquals(tester.getBalance(), BigDecimal.valueOf(50));
        Assert.assertEquals(tester.getCustomerCart().get("C1").getQuantity(), 10);
        Assert.assertEquals(tester.getCustomerCart().get("C1").getPrice(), BigDecimal.valueOf(4.15));
        Assert.assertEquals(tester.getCustomerCart().get("C1").getIndividuallyWrapped(), "Y");
        Assert.assertEquals(tester.getCustomerCart().get("C1").getSku(), "C1");
        Assert.assertEquals(tester.getCustomerCart().get("C1").getName(), "test");
    }

    @Test public void addItemToCartCopy() {
        Item testItem = new Sours("test", "T", 1, BigDecimal.valueOf(4.15), "C1");
        tester.addItemToCart(testItem, 10, BigDecimal.valueOf(50));
        tester.addItemToCart(testItem, 10, BigDecimal.valueOf(50));
        tester.addToBalance(BigDecimal.valueOf(100));
        Assert.assertEquals(tester.getBalance(), BigDecimal.valueOf(0));
        Assert.assertEquals(tester.getCustomerCart().get("C1").getQuantity(), 20);
        Assert.assertEquals(tester.getCustomerCart().get("C1").getPrice(), BigDecimal.valueOf(4.15));
        Assert.assertEquals(tester.getCustomerCart().get("C1").getIndividuallyWrapped(), "Y");
        Assert.assertEquals(tester.getCustomerCart().get("C1").getSku(), "C1");
        Assert.assertEquals(tester.getCustomerCart().get("C1").getName(), "test");
    }










    }

