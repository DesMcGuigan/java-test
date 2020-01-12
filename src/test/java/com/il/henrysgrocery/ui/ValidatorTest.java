package com.il.henrysgrocery.ui;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    private final Validator validator = new Validator();

    @Test
    public void whenBasicDateThenIsValidDateReturnsTrue() {
        String s = "20-01-12";
        assertTrue(validator.isValidDate(s));
    }

    @Test
    public void whenLeapYearThenIsValidDateReturnsTrue() {
        String s = "20-02-29";
        assertTrue(validator.isValidDate(s));
    }

    @Test
    public void whenInvalidMonthThenIsValidDateReturnsFalse() {
        String s = "20-13-12";
        assertFalse(validator.isValidDate(s));
    }

    @Test
    public void whenInvalidDayThenIsValidDateReturnsFalse() {
        String s = "20-01-32";
        assertFalse(validator.isValidDate(s));
    }

    @Test
    public void whenWrongFormatThenIsValidDateReturnsFalse() {
        String s = "20/01/12";
        assertFalse(validator.isValidDate(s));
    }

    @Test
    public void whenEmptyStringThenIsValidDateReturnsFalse() {
        String s = "";
        assertFalse(validator.isValidDate(s));
    }

    @Test
    public void whenBasicBasketThenIsValidBasketReturnsTrue() {
        String s = "BREAD:1,MILK:2,SOUP:5,APPLES:1";
        assertTrue(validator.isValidBasket(s));
    }

    @Test
    public void whenBasketHasSpacesBetweenItemsThenIsValidBasketReturnsTrue() {
        String s = "BREAD:1, MILK:2, SOUP:5,   APPLES:1";
        assertTrue(validator.isValidBasket(s));
    }

    @Test
    public void whenBasketHasSpacesBetweenItemsAndQuantitiesThenIsValidBasketReturnsTrue() {
        String s = "BREAD: 1, MILK:  2, SOUP: 5,   APPLES:   1";
        assertTrue(validator.isValidBasket(s));
    }

    @Test
    public void whenProductIsLowerCaseThenIsValidBasketReturnsTrue() {
        String s = "Bread:1";
        assertTrue(validator.isValidBasket(s));
    }

    @Test
    public void whenUnknownProductThenIsValidBasketReturnsFalse() {
        String s = "Maltesers:1";
        assertFalse(validator.isValidBasket(s));
    }

    @Test
    public void whenEmptyStringThenIsValidBasketReturnsFalse() {
        String s = "";
        assertFalse(validator.isValidBasket(s));
    }

    @Test
    public void whenQuantityIsZeroThenIsValidBasketReturnsFalse() {
        String s = "Bread:0";
        assertFalse(validator.isValidBasket(s));
    }

    @Test
    public void whenNoQuantityPresentThenIsValidBasketReturnsFalse() {
        String s = "Bread:, Milk:";
        assertFalse(validator.isValidBasket(s));
    }

    @Test
    public void whenNoQuantitySeparatorPresentThenIsValidBasketReturnsFalse() {
        String s = "Bread, Milk";
        assertFalse(validator.isValidBasket(s));
    }
}
