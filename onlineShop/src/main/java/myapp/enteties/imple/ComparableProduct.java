package myapp.enteties.imple;

import myapp.enteties.Product;

/*
the ComparableProduct class represents a product with its properties such as ID, name, category,
and price. It provides methods to access and modify these properties and implements the
Comparable<Product> interface to allow for comparison of products based on their IDs.
 */
public class ComparableProduct implements Product, Comparable<Product> {

    private int id;
    private String productName;
    private String categoryName;
    private double price;

    public ComparableProduct() {
    }

    public ComparableProduct(int id, String productName, String categoryName, double price) {
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

    /*
    The compareTo() method compares two products based on their IDs. It returns a negative value
    if the current product's ID is less than the other product's ID, a positive value if it is
    greater, and zero if they are equal.
     */
    @Override public int compareTo(Product otherProduct) {
        return this.id - otherProduct.getId();
    }
}
