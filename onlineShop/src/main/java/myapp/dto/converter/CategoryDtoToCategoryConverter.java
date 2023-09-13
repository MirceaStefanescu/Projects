package myapp.dto.converter;

import myapp.dto.CategoryDto;

/*
    The CategoryDtoToCategoryConverter class is a converter class that converts a category name to a
    CategoryDto object with only the name set.
 */
public class CategoryDtoToCategoryConverter {

    /*
        Within the method, a new instance of CategoryDto is created. The categoryName parameter is
        then set as the value of the categoryName field in the CategoryDto object. Finally, the
        CategoryDto object is returned.
     */
    public CategoryDto convertCategoryNameToCategoryDtoWithOnlyName(String categoryName) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(categoryName);
        return categoryDto;
    }
}
