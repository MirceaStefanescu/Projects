package myapp.dto.converter;

import myapp.dto.ProductDto;
import myapp.enteties.Product;
import myapp.enteties.imple.DefaultProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
    The ProductDtoToProductConverter class is a converter class responsible for converting between
    ProductDto objects and Product objects. It provides methods to convert lists of ProductDto to
    lists of Product, and vice versa.
 */
public class ProductDtoToProductConverter {
    private final CategoryDtoToCategoryConverter categoryConverter;

    {
        categoryConverter = new CategoryDtoToCategoryConverter();
    }


    /*
    The convertProductDtosToProducts method takes a list of ProductDto objects as input and
    converts each ProductDto to a Product object using the convertProductDtoToProduct method.
     */
    public List<Product> convertProductDtosToProducts(List<ProductDto> productDtos) {
        List<Product> products = new ArrayList<>();

        for (ProductDto productDto : productDtos) {
            products.add(convertProductDtoToProduct(productDto));
        }

        //It returns a list of converted Product objects.
        return products;
    }

    /**
     * The convertProductDtoToProduct method takes a single ProductDto object and converts it to a
     * Product object.
     */
    public Product convertProductDtoToProduct(ProductDto productDto) {

        /*
        It creates a new instance of DefaultProduct, sets the ID, price, product name, and
        category name based on the corresponding fields in the ProductDto object.
         */
        Product product = new DefaultProduct();
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice().doubleValue());
        product.setProductName(productDto.getProductName());

        /*
        If the ProductDto has a non-null CategoryDto, it converts the category name to a
        CategoryDto object using the categoryConverter and sets it in the Product object.
         */
        if (productDto.getCategoryDto() != null)
            product.setCategoryName(productDto.getCategoryDto().getCategoryName());

        return product;
    }

    /*
        The convertProductsToProductDtos method takes a list of Product objects as input and
        converts each Product to a ProductDto object using the convertProductToProductDto method.
     */
    public List<ProductDto> convertProductsToProductDtos(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(convertProductToProductDto(product));
        }
        // It returns a list of converted ProductDto objects.
        return productDtos;
    }

    /*
        The convertProductToProductDto method takes a single Product object and converts it to a
        ProductDto object.
    */
    private ProductDto convertProductToProductDto(Product product) {

        /*
        It creates a new instance of ProductDto, sets the ID, price, product name,
        and category name based on the corresponding fields in the Product object. It converts the
        category name to a CategoryDto object using the categoryConverter and sets it in the
        ProductDto object.
         */
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(BigDecimal.valueOf(product.getPrice()));
        productDto.setCategoryDto(categoryConverter.convertCategoryNameToCategoryDtoWithOnlyName(
                product.getCategoryName()));
        productDto.setProductName(product.getProductName());
        return productDto;
    }
}
