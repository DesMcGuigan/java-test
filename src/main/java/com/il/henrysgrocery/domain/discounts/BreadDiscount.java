package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;

import java.time.LocalDate;

public class BreadDiscount extends AbstractDiscount {

    public BreadDiscount() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        discountStart = yesterday;
        discountEnd = yesterday.plusDays(7);
    }

    public Basket apply(Basket basket) {

        if (isActive(basket.getPurchaseDate())) {
            int soupTins = basket.countItemsByProduct(Product.SOUP);
            int breadLoaves = basket.countItemsByProduct(Product.BREAD);
            if (soupTins > 1 && breadLoaves >= 1) {

                int discountsToApply = soupTins / 2;

                int actualDiscounts = Math.min(breadLoaves, discountsToApply);

                BasketItem discountBread = new BasketItem(Product.BREAD_DISCOUNT, actualDiscounts);
                basket.add(discountBread);
            }
        }
        return basket;
    }

}
