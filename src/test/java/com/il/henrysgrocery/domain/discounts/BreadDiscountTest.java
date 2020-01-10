package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;
import org.junit.Assert;
import org.junit.Test;

public class BreadDiscountTest {

    private BreadDiscount discount = new BreadDiscount();

    @Test
    public void whenNoSoupInBasketThenBasketIsUnchanged() {
        Basket basket = new Basket();
        BasketItem breadItem = new BasketItem(Product.BREAD, 1);
        basket.add(breadItem);

        basket = discount.apply(basket);

        Assert.assertEquals(1, basket.getBasketItems().size());
        Assert.assertEquals(breadItem, basket.getBasketItems().get(0));
    }

    @Test
    public void whenTwoSoupInBasketThenBasketContainsDiscountItem() {
        Basket basket = new Basket();
        BasketItem soupItem = new BasketItem(Product.SOUP, 2);
        basket.add(soupItem);
        BasketItem breadItem = new BasketItem(Product.BREAD, 1);
        basket.add(breadItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.DISCOUNTED_BREAD, 1);

        basket = discount.apply(basket);

        Assert.assertEquals(2, basket.getBasketItems().size());
        Assert.assertTrue("", basket.getBasketItems().contains(expectedDiscountItem));
    }
}
