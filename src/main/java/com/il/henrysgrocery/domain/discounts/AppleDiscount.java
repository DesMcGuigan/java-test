package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class AppleDiscount extends AbstractDiscount {

    public AppleDiscount() {
        discountStart = LocalDate.now().plusDays(3);
        discountEnd = discountStart.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
    }

    public Basket apply(Basket basket) {

        if (isActive(basket.getPurchaseDate())) {
            int apples = basket.countItemsByProduct(Product.APPLES);

            if (apples >= 1) {
                BasketItem discountApples = new BasketItem(Product.APPLES_DISCOUNT, apples);
                basket.add(discountApples);
            }
        }
        return basket;
    }
}