package myapp.enteties;

import java.io.Serializable;
import java.util.List;

/*
The Order interface represents a contract for an order object. It extends the Serializable
interface, indicating that implementations of this interface can be serialized and deserialized.
 */
public interface Order extends Serializable {

    /*
    This method takes a string userInput representing a credit card number as input and returns a
     boolean value indicating whether the credit card number is valid or not. The implementation
     of this method should perform the necessary validation checks on the credit card number and
     return true if it is valid, and false otherwise.
     */
    boolean isCreditCardNumberValid(String userInput);

    /*
    This method takes a string userInput representing a credit card number as input and sets it
    as the credit card number for the order. The implementation should update the credit card
    number property of the order object with the given input.
     */
    void setCreditCardNumber(String userInput);

    /*
    This method takes a list of Product objects products as input and sets it as the products for
     the order. The implementation should update the products property of the order object with
     the given list of products.
     */
    void setProducts(List<Product> products);

    /*
    This method returns an integer representing the ID of the customer associated with the order.
     It retrieves the customer ID for the order.
     */
    int getCustomerId();

    /*
    This method takes an integer customerId as input and sets it as the customer ID for the order
    . The implementation should update the customer ID property of the order object with the
    given input.
     */
    void setCustomerId(int customerId);
}
