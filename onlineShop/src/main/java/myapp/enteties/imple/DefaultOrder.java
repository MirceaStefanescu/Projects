package myapp.enteties.imple;

import myapp.enteties.Order;
import myapp.enteties.Product;

import java.util.ArrayList;
import java.util.List;

/*
the DefaultOrder class represents an order with a credit card number, a list of products, and a
customer ID. It provides methods to validate and set the credit card number, set the list of
products, get the customer ID, and generate a string representation of the order.
 */
public class DefaultOrder implements Order {

    /*
    a constant integer representing the number of digits expected in a credit card number.
     */
    private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

    private String creditCardNumber;

    //products is a list of Product objects representing the products included in the order.
    private List<Product> products;
    private int customerId;

    /*
    The isCreditCardNumberValid(String creditCardNumber) method checks if the provided
    creditCardNumber is valid by verifying its length, absence of spaces, and ensuring it is a
    positive number.
     */
    @Override public boolean isCreditCardNumberValid(String creditCardNumber) {
        return creditCardNumber.toCharArray().length == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER &&
               !creditCardNumber.contains(" ") && Long.parseLong(creditCardNumber) > 0;
    }

    /*
    The setCreditCardNumber(String creditCardNumber) method sets the creditCardNumber field to
    the provided value, as long as it is not null.
     */
    @Override public void setCreditCardNumber(String creditCardNumber) {
        if (creditCardNumber == null) {
            return;
        }
        this.creditCardNumber = creditCardNumber;
    }

    /*
    The setProducts(List<Product> products) method sets the products field to a new ArrayList
    containing the same elements as the provided products list.
     */
    @Override public void setProducts(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    @Override public int getCustomerId() {
        return this.customerId;
    }

    @Override public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /*
    The class overrides the toString() method to provide a string representation of the order. It
     combines the customer ID, credit card number, and products into a formatted string.
     */
    @Override public String toString() {
        return "Order: customer id - " + this.customerId + "\t" + "credit card number - " +
               this.creditCardNumber + "\t" + "products - " + this.products;
    }
}
