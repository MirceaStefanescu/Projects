package myapp.storage;

import myapp.enteties.Product;

import java.util.List;

public interface ProductStoringService {

    List<Product> loadProducts();
}
