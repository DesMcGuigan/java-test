package com.il.henrysgrocery.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BasketTest {

    private Basket basket;

    @Before
    public void setup() {
        basket = new Basket();
    }

    @Test
    public void calculatesBasketTotalCorrectly() {

        BigDecimal expectedTotal = new BigDecimal("4.20");

        BasketItem item1 = new BasketItem(Product.BREAD, 2);
        BasketItem item2 = new BasketItem(Product.MILK, 2);

        basket.add(item1);
        basket.add(item2);

        assertEquals(expectedTotal, basket.total());
    }

    @Test
    public void calculatesBasketTotalCorrectlyWithOneItem() {

        BigDecimal expectedTotal = new BigDecimal("1.30");

        BasketItem item1 = new BasketItem(Product.MILK, 1);

        basket.add(item1);

        assertEquals(expectedTotal, basket.total());
    }

    @Test
    public void calculatesBasketTotalCorrectlyWhenBasketIsEmpty() {

        BigDecimal expectedTotal = new BigDecimal("0");

        assertEquals(expectedTotal, basket.total());
    }

    @Test
    public void calculatesBasketTotalCorrectlyWithSoupAndBread() {

        BigDecimal expectedTotal = new BigDecimal("3.55");

        BasketItem item1 = new BasketItem(Product.SOUP, 3);
        BasketItem item2 = new BasketItem(Product.BREAD, 2);

        basket.add(item1);
        basket.add(item2);

        assertEquals(expectedTotal, basket.total());
    }

    @Test
    public void calculatesBasketTotalCorrectlyWithApplesAndMilk() {

        BigDecimal expectedTotal = new BigDecimal("1.90");

        BasketItem item1 = new BasketItem(Product.APPLES, 6);
        BasketItem item2 = new BasketItem(Product.MILK, 1);

        basket.add(item1);
        basket.add(item2);

        assertEquals(expectedTotal, basket.total());
    }

    @Test
    public void calculatesBasketTotalCorrectlyWithApplesSoupAndBread() {

        BigDecimal expectedTotal = new BigDecimal("2.40");

        BasketItem item1 = new BasketItem(Product.APPLES, 3);
        BasketItem item2 = new BasketItem(Product.SOUP, 2);
        BasketItem item3 = new BasketItem(Product.BREAD, 1);

        basket.add(item1);
        basket.add(item2);
        basket.add(item3);

        assertEquals(expectedTotal, basket.total());
    }
}
