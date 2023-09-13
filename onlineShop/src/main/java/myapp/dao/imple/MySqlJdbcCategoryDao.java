package myapp.dao.imple;

import myapp.dao.CategoryDao;
import myapp.dto.CategoryDto;
import myapp.utils.DBUtils;

import java.sql.SQLException;

/*
This class, MySqlJdbcCategoryDao, is an implementation of the CategoryDao interface. It provides
functionality to retrieve a CategoryDto object from a MySQL database based on a given category ID.
 */
public class MySqlJdbcCategoryDao implements CategoryDao {

    /*
    The getCategoryByCategoryId method takes an integer parameter id, which represents the
    category ID to search for. It establishes a database connection using the DBUtils
    .getConnection() method. Then, it prepares a SQL statement to select all columns from the
    category table where the id column matches the given parameter.
     */
    @Override public CategoryDto getCategoryByCategoryId(int id) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "SELECT * FROM category WHERE id = ?")) {

            /*
            Next, it sets the id parameter in the prepared statement using ps.setInt(1, id). It
            then executes the query using ps.executeQuery() and retrieves the result set.
             */
            ps.setInt(1, id);

            try (var rs = ps.executeQuery()) {
                /*
                If the result set contains a row, it creates a new CategoryDto object, sets its
                properties
                using values from the result set, and returns the category object.
                 */
                if (rs.next()) {
                    CategoryDto category = new CategoryDto();
                    category.setId(rs.getInt("id"));
                    category.setCategoryName(rs.getString("category_name"));
                    return category;
                }
            }

            /*
            If any SQLException occurs during the process, it is printed to the console using e
            .printStackTrace(). If no category is found or an exception occurs, the method
            returns null.
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
