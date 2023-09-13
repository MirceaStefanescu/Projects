package myapp.dao.imple;

import myapp.dao.CategoryDao;
import myapp.dao.ProductDao;
import myapp.dto.ProductDto;
import myapp.utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
This class, MySqlJdbcProductDao, is an implementation of the ProductDao interface. It provides
functionality to retrieve a list of ProductDto objects from a MySQL database and to retrieve a
single ProductDto object by its ID.
 */
public class MySqlJdbcProductDao implements ProductDao {

    /*
    The class has a private field categoryDao of type CategoryDao, which represents a dependency
    on the CategoryDao interface. In the instance initializer block, it instantiates a
    MySqlJdbcCategoryDao object and assigns it to the categoryDao field.
     */
    private final CategoryDao categoryDao;

    {
        categoryDao = new MySqlJdbcCategoryDao();
    }

    /*
    The getProducts method retrieves all products from the product table in the database. It
    establishes a database connection using DBUtils.getConnection() and prepares a SQL statement
    to select all columns from the product table. It then executes the query using ps
    .executeQuery() and retrieves the result set.
     */
    @Override public List<ProductDto> getProducts() {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "SELECT * FROM product"); var rs = ps.executeQuery()) {
            List<ProductDto> products = new ArrayList<>();

            /*
            For each row in the result set, it creates a new ProductDto object, sets its
            properties using values from the result set, and retrieves the associated category
            using categoryDao.getCategoryByCategoryId(rs.getInt("category_id")).
            The ProductDto object is then added to a list of products.
             */
            while (rs.next()) {
                ProductDto product = new ProductDto();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCategoryDto(
                        categoryDao.getCategoryByCategoryId(rs.getInt("category_id")));
                products.add(product);
            }
            //Finally, the method returns the list of products.
            return products;

            /*
            If any SQLException occurs during
            the process, it is printed to the console using e.printStackTrace(). If no products
            are found or an exception occurs, the method returns null.
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    The getProductById method retrieves a single product from the product table based on the
    given product ID. It follows a similar process as getProducts, but with an additional WHERE
    clause in the SQL statement to filter the result set based on the product ID.
     */
    @Override public ProductDto getProductById(int productId) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "SELECT * FROM product WHERE id = ?")) {

            ps.setInt(1, productId);
            try (var rs = ps.executeQuery()) {

                /*
                If a product is found, a new ProductDto object is created, its properties are set
                using values from the result set, and the associated category is retrieved using
                categoryDao.getCategoryByCategoryId(rs.getInt("category_id")).
                 */
                if (rs.next()) {
                    ProductDto product = new ProductDto();
                    product.setId(rs.getInt("id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setCategoryDto(
                            categoryDao.getCategoryByCategoryId(rs.getInt("category_id")));

                    //The method then returns the product object.
                    return product;
                }
            }
            //If no product is found or an exception occurs, the method returns null.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
