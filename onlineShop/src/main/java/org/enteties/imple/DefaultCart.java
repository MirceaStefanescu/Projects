package org.enteties.imple;

import org.enteties.Cart;
import org.enteties.Product;

import java.util.ArrayList;
import java.util.List;

public class DefaultCart implements Cart {

    private final List<Product> products;

    {
        products = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return products.isEmpty();
    }

    @Override
    public void addProduct(Product product) {
        if (product == null) {
            return;
        }
        products.add(product);
    }

    @Override
    public List<Product> getProducts() {
        return this.products;
    }

    @Override
    public void clear() {
        products.clear();
    }
}
