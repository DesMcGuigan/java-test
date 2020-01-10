package com.il.henrysgrocery.domain;

import java.math.BigDecimal;

/**
 * Product type available for sale in Henry's Grocery Store
 */
public enum Product {

    SOUP("tin", new BigDecimal("0.65")),
    BREAD("loaf", new BigDecimal("0.80")),
    MILK("bottle", new BigDecimal("1.30")),
    APPLES("single", new BigDecimal("0.10")),

    APPLES_DISCOUNT("single", new BigDecimal("-0.01")),
    BREAD_DISCOUNT("loaf", new BigDecimal("-0.40"));

    String unit;
    BigDecimal cost;

    Product(String unit, BigDecimal cost) {
        this. unit = unit;
        this.cost  =cost;
    }

    public String getUnit() {
        return unit;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
