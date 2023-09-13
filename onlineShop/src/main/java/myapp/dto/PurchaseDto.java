package myapp.dto;

import java.util.List;

/*
    the class encapsulates purchase-related information and provides methods for accessing and
    modifying the purchase's attributes. It serves as a data transfer object for purchase data
    between different parts of the application or between applications.
 */
public class PurchaseDto {
    private Integer id;
    private UserDto userDto;
    private List<ProductDto> productDtos;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }
}
