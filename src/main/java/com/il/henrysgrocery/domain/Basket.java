package com.il.henrysgrocery.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Grocery Basket
 *
 */
public class Basket {

    private List<BasketItem> basketItems = new ArrayList<>();

    private LocalDate purchaseDate;

    public Basket(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Basket add(BasketItem basketItem) {
        basketItems.add(basketItem);
        return this;
    }

    public int countItemsByProduct(Product product) {
        return basketItems.stream().filter(item->item.getProduct().equals(product)).map(BasketItem::getQuantity).reduce(0, Integer::sum);
    }

    public int totalNumberOfItems() {
        return basketItems.stream().map(BasketItem::getQuantity).reduce(0, Integer::sum);
    }

    public BigDecimal total() {
        return basketItems.stream().map(item -> item.getProduct().getCost().multiply(new BigDecimal(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

}
