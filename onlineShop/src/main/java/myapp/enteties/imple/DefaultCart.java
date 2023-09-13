package myapp.enteties.imple;

import myapp.enteties.Cart;
import myapp.enteties.Product;

import java.util.ArrayList;
import java.util.List;

/*
the DefaultCart class represents a shopping cart with a list of products. It provides methods to
check if the cart is empty, add products to the cart, retrieve the list of products in the cart,
and clear the cart by removing all products.
 */
public class DefaultCart implements Cart {

    private final List<Product> products;

    {
        products = new ArrayList<>();
    }

    @Override public boolean isEmpty() {
        return products.isEmpty();
    }

    /*
    The addProduct(Product product) method adds a product to the products list. If the input
    product is null, it simply returns without adding anything.
     */
    @Override public void addProduct(Product product) {
        if (product == null) {
            return;
        }
        products.add(product);
    }

    @Override public List<Product> getProducts() {
        return this.products;
    }

    @Override public void clear() {
        products.clear();
    }
}
