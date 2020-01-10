package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static com.il.henrysgrocery.domain.Product.*;
import static org.junit.Assert.assertEquals;

public class AppleDiscountTest {

    private AppleDiscount discount = new AppleDiscount();

    private Basket basket;

    @Test
    public void whenNoApplesInBasketThenBasketIsUnchanged() {
        basket = new Basket(LocalDate.now());
        BasketItem milkItem = new BasketItem(MILK, 1);
        basket.add(milkItem);

        basket = discount.apply(basket);

        assertEquals(1, basket.totalNumberOfItems());
        assertEquals(1, basket.countItemsByProduct(MILK));
    }

    @Test
    public void whenOneApplesInBasketBeforeOfferStartsThenBasketIsUnchanged() {
        basket = new Basket(LocalDate.now());
        addSingleAppleToBasket();

        basket = discount.apply(basket);

        assertEquals(1, basket.totalNumberOfItems());
        assertEquals(1, basket.countItemsByProduct(APPLES));
    }

    @Test
    public void whenOneApplesInBasketAfterOfferFinishesThenBasketIsUnchanged() {
        basket = new Basket(LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).plusDays(1));
        addSingleAppleToBasket();

        basket = discount.apply(basket);

        assertEquals(1, basket.totalNumberOfItems());
        assertEquals(1, basket.countItemsByProduct(APPLES));
    }

    @Test
    public void whenOneApplesInBasketThenBasketContainsDiscountItem() {
        basket = new Basket(LocalDate.now().plusMonths(1));
        addSingleAppleToBasket();

        basket = discount.apply(basket);

        assertEquals(2, basket.totalNumberOfItems());
        assertEquals(1, basket.countItemsByProduct(APPLES));
        assertEquals(1, basket.countItemsByProduct(APPLES_DISCOUNT));
    }

    @Test
    public void whenOneApplesInBasketAndPurchaseDateFirstDayOfOfferThenBasketContainsDiscountItem() {
        basket = new Basket(LocalDate.now().plusDays(3));
        addSingleAppleToBasket();

        basket = discount.apply(basket);

        assertEquals(2, basket.totalNumberOfItems());
        assertEquals(1, basket.countItemsByProduct(APPLES));
        assertEquals(1, basket.countItemsByProduct(APPLES_DISCOUNT));
    }

    @Test
    public void whenOneApplesInBasketAndPurchaseDateLastDayOfOfferThenBasketContainsDiscountItem() {
        basket = new Basket(LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
        addSingleAppleToBasket();

        basket = discount.apply(basket);

        assertEquals(2, basket.totalNumberOfItems());
        assertEquals(1, basket.countItemsByProduct(APPLES));
        assertEquals(1, basket.countItemsByProduct(APPLES_DISCOUNT));
    }

    @Test
    public void whenMultipleApplesInBasketThenBasketContainsMultipleDiscountItems() {
        basket = new Basket(LocalDate.now().plusMonths(1));
        addApplesToBasket(50000);

        basket = discount.apply(basket);

        assertEquals(100000, basket.totalNumberOfItems());
        assertEquals(50000, basket.countItemsByProduct(APPLES));
        assertEquals(50000, basket.countItemsByProduct(APPLES_DISCOUNT));
    }

    private void addSingleAppleToBasket() {
        BasketItem appleItem = new BasketItem(APPLES, 1);
        basket.add(appleItem);
    }

    private void addApplesToBasket(int number) {
        basket.add(new BasketItem(APPLES, number));
    }
}
