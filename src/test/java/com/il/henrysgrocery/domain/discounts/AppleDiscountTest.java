package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppleDiscountTest {

    private AppleDiscount discount = new AppleDiscount();

    @Test
    public void whenNoApplesInBasketThenBasketIsUnchanged() {
        Basket basket = new Basket(LocalDate.now());
        BasketItem milkItem = new BasketItem(Product.MILK, 1);
        basket.add(milkItem);

        basket = discount.apply(basket);

        assertEquals(1, basket.getBasketItems().size());
        assertEquals(milkItem, basket.getBasketItems().get(0));
    }

    @Test
    public void whenOneApplesInBasketBeforeOfferStartsThenBasketIsUnchanged() {
        Basket basket = new Basket(LocalDate.now());
        BasketItem appleItem = new BasketItem(Product.APPLES, 1);
        basket.add(appleItem);

        basket = discount.apply(basket);

        assertEquals(1, basket.getBasketItems().size());
        assertEquals(appleItem, basket.getBasketItems().get(0));
    }

    @Test
    public void whenOneApplesInBasketAfterOfferFinishesThenBasketIsUnchanged() {
        Basket basket = new Basket(LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).plusDays(1));
        BasketItem appleItem = new BasketItem(Product.APPLES, 1);
        basket.add(appleItem);

        basket = discount.apply(basket);

        assertEquals(1, basket.getBasketItems().size());
        assertEquals(appleItem, basket.getBasketItems().get(0));
    }

    @Test
    public void whenOneApplesInBasketThenBasketContainsDiscountItem() {
        Basket basket = new Basket(LocalDate.now().plusMonths(1));
        BasketItem appleItem = new BasketItem(Product.APPLES, 1);
        basket.add(appleItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.APPLES_DISCOUNT, 1);

        basket = discount.apply(basket);

        assertEquals(2, basket.getBasketItems().size());
        assertTrue("Basket should contain apples", basket.getBasketItems().contains(appleItem));
        assertTrue("Basket should contain an apples discount", basket.getBasketItems().contains(expectedDiscountItem));

    }

    @Test
    public void whenOneApplesInBasketAndPurchaseDateFirstDayOfOfferThenBasketContainsDiscountItem() {
        Basket basket = new Basket(LocalDate.now().plusDays(3));
        BasketItem appleItem = new BasketItem(Product.APPLES, 1);
        basket.add(appleItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.APPLES_DISCOUNT, 1);

        basket = discount.apply(basket);

        assertEquals(2, basket.getBasketItems().size());
        assertTrue("Basket should contain apples", basket.getBasketItems().contains(appleItem));
        assertTrue("Basket should contain an apples discount", basket.getBasketItems().contains(expectedDiscountItem));

    }

    @Test
    public void whenOneApplesInBasketAndPurchaseDateLastDayOfOfferThenBasketContainsDiscountItem() {
        Basket basket = new Basket(LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));

        BasketItem appleItem = new BasketItem(Product.APPLES, 1);
        basket.add(appleItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.APPLES_DISCOUNT, 1);

        basket = discount.apply(basket);

        assertEquals(2, basket.getBasketItems().size());
        assertTrue("Basket should contain apples", basket.getBasketItems().contains(appleItem));
        assertTrue("Basket should contain an apples discount", basket.getBasketItems().contains(expectedDiscountItem));
    }
}
