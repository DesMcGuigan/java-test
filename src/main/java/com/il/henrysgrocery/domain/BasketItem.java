package com.il.henrysgrocery.domain;

import java.util.Objects;

/**
 * A given quantity of products which can be added to a basket
 *
 */
public class BasketItem {

    private Product product;
    private int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
