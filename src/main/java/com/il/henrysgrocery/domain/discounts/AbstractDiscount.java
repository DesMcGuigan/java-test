package com.il.henrysgrocery.domain.discounts;

import java.time.LocalDate;

public abstract class AbstractDiscount implements Discountable {

    LocalDate discountStart;
    LocalDate discountEnd;

    boolean isActive(LocalDate purchaseDate) {

        if ((purchaseDate.isEqual(discountStart) || purchaseDate.isAfter(discountStart)) &&
                (purchaseDate.isEqual(discountEnd) || purchaseDate.isBefore(discountEnd))) {
            return true;
        }

        return false;
    }
}
