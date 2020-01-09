package com.il.henrysgrocery.service;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.discounts.Discountable;

import java.math.BigDecimal;
import java.util.List;

public class BasketCalculator {

    private List<Discountable> discountList;

    public BasketCalculator(List<Discountable> discountList) {
        this.discountList = discountList;
    }

    public BigDecimal calculateBasket(Basket basket) {

        for (Discountable d : discountList) {
            basket = d.apply(basket);
        }
        return basket.total();
    }
}
