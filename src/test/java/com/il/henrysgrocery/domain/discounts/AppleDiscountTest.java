package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class AppleDiscountTest {

    private AppleDiscount discount = new AppleDiscount();

    @Test
    public void whenNoApplesInBasketThenBasketIsUnchanged() {
        Basket basket = new Basket(LocalDate.now());
        BasketItem milkItem = new BasketItem(Product.MILK, 1);
        basket.add(milkItem);

        basket = discount.apply(basket);

        Assert.assertEquals(1, basket.getBasketItems().size());
        Assert.assertEquals(milkItem, basket.getBasketItems().get(0));
    }

    @Test
    public void whenOneApplesInBasketThenBasketIsUnchanged() {
        Basket basket = new Basket(LocalDate.now());
        BasketItem appleItem = new BasketItem(Product.APPLES, 1);
        basket.add(appleItem);
        BasketItem expectedDiscountItem = new BasketItem(Product.DISCOUNTED_APPLES, 1);

        basket = discount.apply(basket);

        Assert.assertEquals(1, basket.getBasketItems().size());
        Assert.assertEquals(expectedDiscountItem, basket.getBasketItems().get(0));
    }
}
