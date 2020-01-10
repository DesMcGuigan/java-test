package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BreadDiscountTest {

    private BreadDiscount discount = new BreadDiscount();

    @Test
    public void whenNoSoupInBasketThenBasketIsUnchanged() {
        Basket basket = new Basket(LocalDate.now());
        BasketItem breadItem = new BasketItem(Product.BREAD, 1);
        basket.add(breadItem);

        basket = discount.apply(basket);

        assertEquals(1, basket.getBasketItems().size());
        assertEquals(breadItem, basket.getBasketItems().get(0));
    }

    @Test
    public void whenTwoSoupInBasketThenBasketContainsDiscountItem() {
        Basket basket = new Basket(LocalDate.now());
        BasketItem soupItem = new BasketItem(Product.SOUP, 2);
        basket.add(soupItem);
        BasketItem breadItem = new BasketItem(Product.BREAD, 1);
        basket.add(breadItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.DISCOUNTED_BREAD, 1);

        basket = discount.apply(basket);

        assertEquals(2, basket.getBasketItems().size());
        assertTrue("Basket should contain discounted bread", basket.getBasketItems().contains(expectedDiscountItem));
    }

    @Test
    public void whenTwoSoupInBasketAndPurchaseDateFirstDayOfOfferThenBasketContainsDiscountItem() {
        Basket basket = new Basket(LocalDate.now().minusDays(1));
        BasketItem soupItem = new BasketItem(Product.SOUP, 2);
        basket.add(soupItem);
        BasketItem breadItem = new BasketItem(Product.BREAD, 1);
        basket.add(breadItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.DISCOUNTED_BREAD, 1);

        basket = discount.apply(basket);

        assertEquals(2, basket.getBasketItems().size());
        assertTrue("Basket should contain discounted bread", basket.getBasketItems().contains(expectedDiscountItem));
    }

    @Test
    public void whenTwoSoupInBasketAndPurchaseDateLastDayOfOfferThenBasketContainsDiscountItem() {
        Basket basket = new Basket(LocalDate.now().plusDays(6));
        BasketItem soupItem = new BasketItem(Product.SOUP, 2);
        basket.add(soupItem);
        BasketItem breadItem = new BasketItem(Product.BREAD, 1);
        basket.add(breadItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.DISCOUNTED_BREAD, 1);

        basket = discount.apply(basket);

        assertEquals(2, basket.getBasketItems().size());
        assertTrue("Basket should contain discounted bread", basket.getBasketItems().contains(expectedDiscountItem));
    }

    @Test
    public void whenTwoSoupInBasketAndPurchaseBeforeOfferStartsThenBasketIsUnchanged() {
        Basket basket = new Basket(LocalDate.now().minusDays(2));
        BasketItem soupItem = new BasketItem(Product.SOUP, 2);
        basket.add(soupItem);
        BasketItem breadItem = new BasketItem(Product.BREAD, 1);
        basket.add(breadItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.DISCOUNTED_BREAD, 1);

        basket = discount.apply(basket);

        assertEquals(2, basket.getBasketItems().size());
        assertTrue("Basket should contain bread", basket.getBasketItems().contains(breadItem));
        assertTrue("Basket should contain soup", basket.getBasketItems().contains(soupItem));
    }

    @Test
    public void whenTwoSoupInBasketAndPurchaseAfterOfferEndsThenBasketIsUnchanged() {
        Basket basket = new Basket(LocalDate.now().plusDays(7));
        BasketItem soupItem = new BasketItem(Product.SOUP, 2);
        basket.add(soupItem);
        BasketItem breadItem = new BasketItem(Product.BREAD, 1);
        basket.add(breadItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.DISCOUNTED_BREAD, 1);

        basket = discount.apply(basket);

        assertEquals(2, basket.getBasketItems().size());
        assertTrue("Basket should contain bread", basket.getBasketItems().contains(breadItem));
        assertTrue("Basket should contain soup", basket.getBasketItems().contains(soupItem));
    }
}
