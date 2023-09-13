package myapp.enteties.imple;

import myapp.enteties.Product;
import myapp.enteties.Purchase;

import java.util.ArrayList;
import java.util.List;

/*
the DefaultPurchase class represents a purchase made by a customer. It includes information such
as the credit card number, the list of products, and the customer ID. It provides methods to
validate and set the credit card number, get and set the customer ID, get and set the list of
products, and generate a string representation of the purchase.
 */
public class DefaultPurchase implements Purchase {

    private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

    private String creditCardNumber;
    private List<Product> products;
    private int customerId;

    /*
    The isCreditCardNumberValid(String creditCardNumber) method checks if the provided credit
    card number is valid. It checks if the length of the credit card number is equal to 16 (the
    constant AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER), if it does not contain any spaces, and if
    it can be parsed as a positive long value. It returns true if the credit card number is
    valid, and false otherwise.
     */
    @Override public boolean isCreditCardNumberValid(String creditCardNumber) {
        return creditCardNumber.toCharArray().length == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER &&
               !creditCardNumber.contains(" ") && Long.parseLong(creditCardNumber) > 0;
    }

    /*
    The setCreditCardNumber(String creditCardNumber) method sets the creditCardNumber field to
    the provided value, but only if the value is not null.
     */
    @Override public void setCreditCardNumber(String creditCardNumber) {
        if (creditCardNumber == null) {
            return;
        }
        this.creditCardNumber = creditCardNumber;
    }

    @Override public int getCustomerId() {
        return this.customerId;
    }

    @Override public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /*
    The toString() method generates a string representation of the purchase. It combines the
    customer ID, credit card number, and products into a formatted string.
     */
    @Override public String toString() {
        return "Order: customer id - " + this.customerId + "\t" + "credit card number - " +
               this.creditCardNumber + "\t" + "products - " + this.products;
    }

    /*
    The getProducts() method returns a new ArrayList containing the products. This is done to
    prevent direct modification of the internal products list.
     */
    @Override public List<Product> getProducts() {
        return new ArrayList<>(this.products);
    }

    /*
    The setProducts(List<Product> products) method sets the products field to a new ArrayList
    containing the provided products. This is also done to prevent direct modification of the
    internal products list.
     */
    @Override public void setProducts(List<Product> products) {
        this.products = new ArrayList<>(products);
    }
}
