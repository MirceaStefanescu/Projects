package myapp.enteties.imple;

import myapp.enteties.Product;

/*
the DefaultProduct class represents a product with an ID, name, category, and price. It provides
methods to get and set the values of these fields, as well as a toString() method for generating
a string representation of the product.
 */
public class DefaultProduct implements Product {

    private int id;
    private String productName;
    private String categoryName;
    private double price;

    public DefaultProduct() {
    }

    public DefaultProduct(int id, String productName, String categoryName, double price) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
        this.price = price;
    }

    @Override public String toString() {
        return "Product id=" + id + ", product name=" + productName + ", category name=" +
               categoryName + ", price=" + price;
    }

    @Override public int getId() {
        return this.id;
    }

    @Override public void setId(int id) {
        this.id = id;
    }

    @Override public String getProductName() {
        return this.productName;
    }

    @Override public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override public String getCategoryName() {
        return this.categoryName;
    }

    @Override public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override public double getPrice() {
        return this.price;
    }

    @Override public void setPrice(double price) {
        this.price = price;
    }
}
