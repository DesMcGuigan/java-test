package com.il.henrysgrocery.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Grocery Basket
 *
 */
public class Basket {

    List<BasketItem> basketItems = new ArrayList<>();

    public Basket add(BasketItem basketItem) {
        basketItems.add(basketItem);
        return this;
    }

    public Basket remove(Product product) {
        basketItems.removeIf(item->item.getProduct().equals(product));
        return this;
    }

    public BigDecimal total() {
        return basketItems.stream().map(item -> item.getProduct().getCost().multiply(new BigDecimal(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }
}
