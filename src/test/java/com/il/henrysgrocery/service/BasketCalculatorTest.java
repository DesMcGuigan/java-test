package com.il.henrysgrocery.service;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BasketCalculatorTest {

    private Basket basket;

    private BasketCalculator calculator;

    @Before
    public void setup() {
        basket = new Basket();
        calculator = new BasketCalculator();
    }

    @Test
    public void calculatesTotalCorrectly() {

        BigDecimal expectedTotal = new BigDecimal("4.20");

        BasketItem item1 = new BasketItem(Product.BREAD, 2);
        BasketItem item2 = new BasketItem(Product.MILK, 2);

        basket.add(item1);
        basket.add(item2);

        assertEquals(expectedTotal, calculator.calculateBasket(basket));
    }

    @Test
    public void calculatesTotalCorrectlyWithOneItem() {

        BigDecimal expectedTotal = new BigDecimal("1.30");

        BasketItem item1 = new BasketItem(Product.MILK, 1);

        basket.add(item1);

        assertEquals(expectedTotal, calculator.calculateBasket(basket));
    }

    @Test
    public void calculatesTotalCorrectlyWhenBucketIsEmpty() {

        BigDecimal expectedTotal = new BigDecimal("0");

        assertEquals(expectedTotal, calculator.calculateBasket(basket));
    }

    @Test
    public void calculatesBasketTotalCorrectlyWithSoupAndBread() {

        BigDecimal expectedTotal = new BigDecimal("3.55");

        BasketItem item1 = new BasketItem(Product.SOUP, 3);
        BasketItem item2 = new BasketItem(Product.BREAD, 2);

        basket.add(item1);
        basket.add(item2);

        assertEquals(expectedTotal, calculator.calculateBasket(basket));
    }

    @Test
    public void calculatesBasketTotalCorrectlyWithApplesAndMilk() {

        BigDecimal expectedTotal = new BigDecimal("1.90");

        BasketItem item1 = new BasketItem(Product.APPLES, 6);
        BasketItem item2 = new BasketItem(Product.MILK, 1);

        basket.add(item1);
        basket.add(item2);

        assertEquals(expectedTotal, calculator.calculateBasket(basket));
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

        assertEquals(expectedTotal, calculator.calculateBasket(basket));
    }
}
