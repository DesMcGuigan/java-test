package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;
import org.junit.Assert;
import org.junit.Test;

public class AppleDiscountTest {

    private AppleDiscount discount = new AppleDiscount();

    @Test
    public void whenNoApplesInBasketThenBasketIsUnchanged() {
        Basket basket = new Basket();
        BasketItem breadItem = new BasketItem(Product.BREAD, 1);
        basket.add(breadItem);

        basket = discount.apply(basket);

        Assert.assertEquals(1, basket.getBasketItems().size());
        Assert.assertEquals(breadItem, basket.getBasketItems().get(0));
    }

    @Test
    public void whenOneApplesInBasketThenBasketIsUnchanged() {
        Basket basket = new Basket();
        BasketItem appleItem = new BasketItem(Product.APPLES, 1);
        basket.add(appleItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.DISCOUNTED_APPLES, 1);

        basket = discount.apply(basket);

        Assert.assertEquals(1, basket.getBasketItems().size());
        Assert.assertEquals(expectedDiscountItem, basket.getBasketItems().get(0));
    }
}
