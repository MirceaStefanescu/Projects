package myapp.dto;

/*
    the class encapsulates category-related information and provides methods for
    accessing and modifying the category's attributes. It serves as a data transfer object for
    category data between different parts of the application or between applications.
 */
public class CategoryDto {

    private Integer id;
    private String categoryName;

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
