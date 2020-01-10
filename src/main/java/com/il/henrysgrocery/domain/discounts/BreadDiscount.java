package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;

public class BreadDiscount extends AbstractDiscount {

    public Basket apply(Basket basket) {

        int soupTins = basket.getBasketItems().stream().filter(item -> item.getProduct().equals(Product.SOUP)).map(BasketItem::getQuantity).reduce(0,Integer::sum);
        int breadLoaves = basket.getBasketItems().stream().filter(item -> item.getProduct().equals(Product.BREAD)).map(BasketItem::getQuantity).reduce(0, Integer::sum);
        if (soupTins > 1 && breadLoaves >= 1) {

            int discountsToApply = soupTins / 2;

            int actualDiscounts = Math.min(breadLoaves, discountsToApply);

            basket.remove(Product.BREAD);

            if (breadLoaves > actualDiscounts) {
                BasketItem bread = new BasketItem(Product.BREAD, breadLoaves - actualDiscounts);
                basket.add(bread);
            }

            BasketItem discountBread = new BasketItem(Product.DISCOUNTED_BREAD, actualDiscounts);
            basket.add(discountBread);

        }
        return basket;
    }
}
