package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import org.junit.Test;

import java.time.LocalDate;

import static com.il.henrysgrocery.domain.Product.*;
import static org.junit.Assert.assertEquals;

public class BreadDiscountTest {

    private BreadDiscount discount = new BreadDiscount();

    private Basket basket;

    @Test
    public void whenNoSoupInBasketThenBasketIsUnchanged() {
        basket = new Basket(LocalDate.now());
        BasketItem breadItem = new BasketItem(BREAD, 1);
        basket.add(breadItem);

        basket = discount.apply(basket);

        assertEquals(1, basket.totalNumberOfItems());
        assertEquals(1, basket.countItemsByProduct(BREAD));
    }

    @Test
    public void whenTwoSoupInBasketThenBasketContainsDiscountItem() {
        basket = new Basket(LocalDate.now());
        addTwoSoupsAndOneBreadToBasket();

        basket = discount.apply(basket);

        assertEquals(4, basket.totalNumberOfItems());
        assertEquals(2, basket.countItemsByProduct(SOUP));
        assertEquals(1, basket.countItemsByProduct(BREAD));
        assertEquals(1, basket.countItemsByProduct(BREAD_DISCOUNT));
    }

    @Test
    public void whenTwoSoupInBasketAndPurchaseDateFirstDayOfOfferThenBasketContainsDiscountItem() {
        basket = new Basket(LocalDate.now().minusDays(1));
        addTwoSoupsAndOneBreadToBasket();

        basket = discount.apply(basket);

        assertEquals(4, basket.totalNumberOfItems());
        assertEquals(2, basket.countItemsByProduct(SOUP));
        assertEquals(1, basket.countItemsByProduct(BREAD));
        assertEquals(1, basket.countItemsByProduct(BREAD_DISCOUNT));
    }

    @Test
    public void whenTwoSoupInBasketAndPurchaseDateLastDayOfOfferThenBasketContainsDiscountItem() {
        basket = new Basket(LocalDate.now().plusDays(6));
        addTwoSoupsAndOneBreadToBasket();

        basket = discount.apply(basket);

        assertEquals(4, basket.totalNumberOfItems());
        assertEquals(2, basket.countItemsByProduct(SOUP));
        assertEquals(1, basket.countItemsByProduct(BREAD));
        assertEquals(1, basket.countItemsByProduct(BREAD_DISCOUNT));
    }

    @Test
    public void whenTwoSoupInBasketAndPurchaseBeforeOfferStartsThenBasketIsUnchanged() {
        basket = new Basket(LocalDate.now().minusDays(2));
        addTwoSoupsAndOneBreadToBasket();

        basket = discount.apply(basket);

        assertEquals(3, basket.totalNumberOfItems());
        assertEquals(2, basket.countItemsByProduct(SOUP));
        assertEquals(1, basket.countItemsByProduct(BREAD));
    }

    @Test
    public void whenTwoSoupInBasketAndPurchaseAfterOfferEndsThenBasketIsUnchanged() {
        basket = new Basket(LocalDate.now().plusDays(7));
        addTwoSoupsAndOneBreadToBasket();

        basket = discount.apply(basket);

        assertEquals(3, basket.totalNumberOfItems());
        assertEquals(2, basket.countItemsByProduct(SOUP));
        assertEquals(1, basket.countItemsByProduct(BREAD));
    }

    @Test
    public void whenSixSoupInBasketThenBasketContainsDiscountItem() {
        basket = new Basket(LocalDate.now());
        addTwoSoupsAndOneBreadToBasket();
        addTwoSoupsAndOneBreadToBasket();
        addTwoSoupsAndOneBreadToBasket();

        basket = discount.apply(basket);

        assertEquals(12, basket.totalNumberOfItems());
        assertEquals(6, basket.countItemsByProduct(SOUP));
        assertEquals(3, basket.countItemsByProduct(BREAD));
        assertEquals(3, basket.countItemsByProduct(BREAD_DISCOUNT));
    }

    @Test
    public void whenThreeSoupInBasketThenBasketContainsDiscountItem() {
        basket = new Basket(LocalDate.now());
        addTwoSoupsAndOneBreadToBasket();
        BasketItem item = new BasketItem(SOUP, 1);
        basket.add(item);

        basket = discount.apply(basket);

        assertEquals(5, basket.totalNumberOfItems());
        assertEquals(3, basket.countItemsByProduct(SOUP));
        assertEquals(1, basket.countItemsByProduct(BREAD));
        assertEquals(1, basket.countItemsByProduct(BREAD_DISCOUNT));
    }

    @Test
    public void whenFourSoupInBasketAndOnlyOneBreadThenBasketContainsOneDiscountItem() {
        basket = new Basket(LocalDate.now());
        addTwoSoupsAndOneBreadToBasket();
        BasketItem item = new BasketItem(SOUP, 2);
        basket.add(item);

        basket = discount.apply(basket);

        assertEquals(6, basket.totalNumberOfItems());
        assertEquals(4, basket.countItemsByProduct(SOUP));
        assertEquals(1, basket.countItemsByProduct(BREAD));
        assertEquals(1, basket.countItemsByProduct(BREAD_DISCOUNT));
    }

    private void addTwoSoupsAndOneBreadToBasket() {
        BasketItem soupItem = new BasketItem(SOUP, 2);
        basket.add(soupItem);
        BasketItem breadItem = new BasketItem(BREAD, 1);
        basket.add(breadItem);
    }
}
