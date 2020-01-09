package com.il.henrysgrocery.service;

import com.il.henrysgrocery.domain.Basket;

import java.math.BigDecimal;

public class BasketCalculator {

    public BigDecimal calculateBasket(Basket basket) {
        return basket.total();
    }
}
