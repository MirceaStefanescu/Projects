package myapp.enteties;

import java.util.List;

/*
The Cart interface defines a contract for managing a shopping cart. It declares several methods
to interact with the cart and its contents.
 */
public interface Cart {

    /*
    This method returns a boolean value indicating whether the cart is empty or not. It checks if
     there are any products in the cart and returns true if the cart is empty, false otherwise.
     */
    boolean isEmpty();

    /*
    This method adds a Product object to the cart. It takes a Product parameter representing the
    product to be added to the cart and adds it to the cart's contents.
     */
    void addProduct(Product productById);

    /*
    This method returns a list of Product objects representing the products currently in the cart
    . It retrieves the list of products stored in the cart and returns them as a collection.
     */
    List<Product> getProducts();

    /*
    This method clears the contents of the cart, removing all the products. It resets the cart to
     an empty state.
     */
    void clear();
}
