package myapp.enteties;

import java.io.Serializable;

/*
    the Product interface specifies the contract for a product and defines methods to access and
    modify its attributes. Implementing classes will provide the concrete implementation for these
    methods.
 */
public interface Product extends Serializable {

    /*
    This method returns an integer representing the ID of the product. It retrieves the unique
    identifier for the product.
     */
    int getId();

    /*
    This method takes an integer id as input and sets the ID of the product. It updates the ID of
     the product to the specified value.
     */
    void setId(int id);

    /*
    This method returns a string representing the name of the product. It retrieves the name of
    the product.
     */
    String getProductName();

    /*
    This method takes a string productName as input and sets the name of the product. It updates
    the name of the product to the specified value.
     */
    void setProductName(String productName);

    /*
    This method returns a string representing the name of the category to which the product
    belongs. It retrieves the category name of the product.
     */
    String getCategoryName();

    /*
    This method takes a string categoryName as input and sets the category name of the product.
    It updates the category name of the product to the specified value.
     */
    void setCategoryName(String categoryName);

    /*
    This method returns a double representing the price of the product. It retrieves the price of
     the product.
     */
    double getPrice();

    /*
    This method takes a double price as input and sets the price of the product. It updates the
    price of the product to the specified value.
     */
    void setPrice(double price);
}
