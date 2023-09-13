package myapp.dao;

import myapp.dto.CategoryDto;

/*
The CategoryDao interface defines a contract for accessing and manipulating category data. It
declares a single method getCategoryByCategoryId(int id) that retrieves a CategoryDto object
based on a given category ID.
 */
public interface CategoryDao {

    CategoryDto getCategoryByCategoryId(int id);
}
