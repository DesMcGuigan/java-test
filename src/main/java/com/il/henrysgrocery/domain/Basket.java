package com.il.henrysgrocery.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Grocery Basket
 *
 */
public class Basket {

    List<BasketItem> basketItems = new ArrayList<>();

    LocalDate purchaseDate;

    public Basket(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Basket add(BasketItem basketItem) {
        basketItems.add(basketItem);
        return this;
    }

    public Basket remove(Product product) {
        basketItems.removeIf(item->item.getProduct().equals(product));
        return this;
    }

    public int countItemsByProduct(Product product) {
        return basketItems.stream().filter(item->item.getProduct().equals(product)).map(BasketItem::getQuantity).reduce(0, Integer::sum);
    }

    public BigDecimal total() {
        return basketItems.stream().map(item -> item.getProduct().getCost().multiply(new BigDecimal(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<BasketItem> getBasketItems() {
        return Collections.unmodifiableList(basketItems);
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

}
