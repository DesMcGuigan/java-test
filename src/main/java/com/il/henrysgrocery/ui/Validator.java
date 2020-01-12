package com.il.henrysgrocery.ui;

import com.il.henrysgrocery.domain.Product;
import org.apache.commons.validator.GenericValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Validator {


    public boolean isValidDate(String inputDate) {

        if (GenericValidator.isDate(inputDate, "yy-MM-dd", true)) {
            return true;
        }

        return false;
    }


    public boolean isValidBasket(String str) {
        String input = Objects.requireNonNull(str, "Input string must not be null");
        List<String> items = Arrays.asList(input.split(","));
        if (items.size() < 1) {
            return false;
        }

        boolean validItem;
        for (String item : items) {
            validItem = isValidBasketItem(item);
            if (!validItem) {
                return false;
            }
        }

        return true;

    }

    private boolean isValidBasketItem(String str) {
        String input = Objects.requireNonNull(str, "Input string must not be null");
        String[] tokens = input.split(":");
        if (tokens.length != 2) {
            return false;
        }
        String productString = tokens[0];
        String quantity = tokens[1];

        if (!isNumeric(quantity.trim())) {
            return false;
        }

        try {
           Product.valueOf(productString.trim().toUpperCase());
        } catch (IllegalArgumentException iae) {
            return false;
        }

        return true;
    }

    private boolean isNumeric(String str) {
        String strNum =Objects.requireNonNull(str, "Input string must not be null");
        try {
            int i = Integer.parseInt(strNum);
            if (i <= 0) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
