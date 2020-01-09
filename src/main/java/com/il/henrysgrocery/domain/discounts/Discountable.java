package com.il.henrysgrocery.domain.discounts;

import com.il.henrysgrocery.domain.Basket;

public interface Discountable {

    Basket apply(Basket basket);
}
