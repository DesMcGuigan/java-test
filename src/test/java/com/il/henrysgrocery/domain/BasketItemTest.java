package com.il.henrysgrocery.domain;

import org.junit.Test;

public class BasketItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenQuantityLessThanZeroConstrcutorThrowsIllegalArumentException() {
        new BasketItem(Product.APPLES, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenQuantityZeroConstrcutorThrowsIllegalArumentException() {
        new BasketItem(Product.APPLES, 0);
    }
}
