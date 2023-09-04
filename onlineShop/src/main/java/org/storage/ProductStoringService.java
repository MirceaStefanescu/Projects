package org.storage;

import org.enteties.Product;

import java.util.List;

public interface ProductStoringService {

    List<Product> loadProducts();
}
