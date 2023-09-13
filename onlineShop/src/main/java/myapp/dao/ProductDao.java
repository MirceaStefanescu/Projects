package myapp.dao;

import myapp.dto.ProductDto;

import java.util.List;

/*
The ProductDao interface defines a contract for accessing and manipulating product data
 */
public interface ProductDao {

    /*
    This method returns a list of ProductDto objects, representing multiple products. The
    implementation of this method is responsible for retrieving all the products from a data
    source and returning them as a list.
     */
    List<ProductDto> getProducts();

    /*
    This method takes an integer productId as input and returns a single ProductDto object. The
    implementation of this method should retrieve the product with the specified ID from a data
    source and return it.
     */
    ProductDto getProductById(int productId);
}
