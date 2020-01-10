package com.il.henrysgrocery.domain;

import java.util.Objects;

/**
 * A given quantity of products which can be added to a basket
 *
 */
public class BasketItem {

    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = Objects.requireNonNull(product, "Product must not be null");
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("quantity must be greater than zero");
        }
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return quantity == that.quantity &&
                product == that.product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}
