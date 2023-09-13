package myapp.dto;

import java.math.BigDecimal;

/*
    the ProductDto class encapsulates product-related information and provides methods for
    accessing and modifying the product's attributes. It serves as a data transfer object for
    product data between different parts of the application or between applications.
 */
public class ProductDto {
    private Integer id;
    private String productName;
    private BigDecimal price;
    private CategoryDto categoryDto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
