package com.il.henrysgrocery;

import com.il.henrysgrocery.domain.Basket;
import com.il.henrysgrocery.domain.BasketItem;
import com.il.henrysgrocery.domain.Product;
import com.il.henrysgrocery.domain.discounts.AppleDiscount;
import com.il.henrysgrocery.domain.discounts.BreadDiscount;
import com.il.henrysgrocery.domain.discounts.Discountable;
import com.il.henrysgrocery.service.BasketCalculator;
import com.il.henrysgrocery.ui.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {


    private final BasketCalculator calculator;

    private final Validator validator;

    public Application() {
        this.validator = new Validator();
        List<Discountable> currentDiscounts = new ArrayList<>();
        currentDiscounts.add(new AppleDiscount());
        currentDiscounts.add(new BreadDiscount());
        this.calculator = new BasketCalculator(currentDiscounts);
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.displayMenu();

        Scanner scanner = new Scanner(System.in);

        String dateInput = application.readBasketDate(scanner, application.validator);

        LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("yy-MM-dd").parse(dateInput));

        String basketInput = application.readBasketItems(scanner, application.validator);
        Basket basket = application.createBasket(basketInput, date);

        application.displayBasketTotal(application.calculator, basket);

    }

    private void displayMenu() {
        System.out.println("================================");
        System.out.println("Welcome to Henry's Grocery Store");
        System.out.println("================================");
        System.out.println();
        System.out.println("We currently have the following products:");
        System.out.println("Bread | Milk | Soup | Apples\n");

    }

    private Basket createBasket(String input, LocalDate purchaseDate) {
        String[] productChoices = input.split(",");

        Basket basket = new Basket(purchaseDate);

        for(String s : productChoices) {
            BasketItem item = createBasketItem(s);
            basket.add(item);
        }

        return basket;
    }

    private BasketItem createBasketItem(String input) {
            String[] tokens = input.split(":");
            return new BasketItem(Product.valueOf(tokens[0].trim().toUpperCase()), Integer.valueOf(tokens[1].trim()));
    }

    private String readBasketDate(Scanner scanner, Validator validator) {

        System.out.println("Please enter the purchase date of your basket in form yy-MM-dd:");

        String dateInput = scanner.nextLine();
        if (!validator.isValidDate(dateInput)) {

            do {
                System.out.println("Error. Purchase date is invalid. Please enter a valid date in the format yy-MM-dd:");
                dateInput = scanner.nextLine();
            } while (!validator.isValidDate(dateInput));

        }

        return dateInput;
    }

    private String readBasketItems(Scanner scanner, Validator validator) {

        System.out.println("Please enter each product in your basket followed by a colon and then a numeric quantity. Separate each item with a comma.");
        System.out.println("When you are finished, press the enter key:");

        String basketInput = scanner.nextLine();

        if (!validator.isValidBasket(basketInput)) {
            do {
                System.out.println("Error. Product items are invalid. Please enter each product followed by a colon and the quantity you wish to purchase. Separate each product with a comma.");
                System.out.println("For Example: Enter <Bread:1, Milk:1>");
                basketInput = scanner.nextLine();
            } while (!validator.isValidBasket(basketInput));

        }

        return basketInput;
    }

    private void displayBasketTotal(BasketCalculator calculator, Basket basket) {
        System.out.println("\nBasket Total is: " + calculator.calculateBasket(basket));
    }
}
