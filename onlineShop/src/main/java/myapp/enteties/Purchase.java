package myapp.enteties;

import java.io.Serializable;
import java.util.List;

/*
The Purchase interface represents a contract for a purchase object. It extends the Serializable
interface, indicating that implementations of this interface can be serialized and deserialized.
 */
public interface Purchase extends Serializable {

    /*
    This method takes a string userInput representing a credit card number as input and returns a
     boolean value indicating whether the credit card number is valid or not. The implementation
     of this method should perform the necessary validation checks on the credit card number and
     return true if it is valid, and false otherwise.
     */
    boolean isCreditCardNumberValid(String userInput);

    /*
    This method takes a string userInput representing a credit card number as input and sets it
    as the credit card number for the purchase. The implementation should update the credit card
    number property of the purchase object with the given input.
     */
    void setCreditCardNumber(String userInput);

    /*
     This method returns a list of Product objects representing the products associated with the
     purchase. It retrieves the list of products for the purchase.
     */
    List<Product> getProducts();

    /*
    This method takes a list of Product objects products as input and sets it as the products for
     the purchase. The implementation should update the products property of the purchase object
     with the given list of products.
     */
    void setProducts(List<Product> products);

    /*
    This method returns an integer representing the ID of the customer associated with the
    purchase. It retrieves the customer ID for the purchase.
     */
    int getCustomerId();

    /*
    This method takes an integer customerId as input and sets it as the customer ID for the
    purchase. The implementation should update the customer ID property of the purchase object
    with the given input.
     */
    void setCustomerId(int customerId);
}
