package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;

public class AppleDiscount extends AbstractDiscount {

    public Basket apply(Basket basket) {

        int apples = basket.getBasketItems().stream().filter(item -> item.getProduct().equals(Product.APPLES)).map(BasketItem::getQuantity).reduce(0,Integer::sum);

        if (apples >= 1) {

            basket.remove(Product.APPLES);

            BasketItem discountApples = new BasketItem(Product.DISCOUNTED_APPLES, apples);

            basket.add(discountApples);

        }
        return basket;
    }
}