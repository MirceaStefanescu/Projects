package myapp.dao.imple;

import myapp.dao.ProductDao;
import myapp.dao.PurchaseDao;
import myapp.dao.UserDao;
import myapp.dto.ProductDto;
import myapp.dto.PurchaseDto;
import myapp.utils.DBUtils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
This class MySqlJdbcPurchaseDao is an implementation of the PurchaseDao interface, which provides
 methods for interacting with a MySQL database to perform operations related to purchases.
 */
public class MySqlJdbcPurchaseDao implements PurchaseDao {

    /*
    The class has two private fields userDao and productDao, which are instances of the UserDao
    and ProductDao interfaces respectively. These fields are used to interact with the user and
    product data.
     */
    private final UserDao userDao;
    private final ProductDao productDao;

    /*
    The constructor initializes the userDao and productDao fields with concrete implementations
    of their respective interfaces (MySqlJdbcUserDao and MySqlJdbcProductDao).
     */ {
        userDao = new MySqlJdbcUserDao();
        productDao = new MySqlJdbcProductDao();
    }

    /*
    The savePurchase method is responsible for saving a purchase to the database. It takes a
    PurchaseDto object as a parameter and uses prepared statements to insert the purchase data
    into the purchase and purchase_product tables. It also handles the generation of keys for the
     inserted records.
     */
    @Override public void savePurchase(PurchaseDto purchase) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "INSERT INTO purchase (fk_purchase_user) VALUES (?);",
                Statement.RETURN_GENERATED_KEYS); var psPurchaseProduct = conn.prepareStatement(
                "INSERT INTO purchase_product (purchase_id, product_id) VALUES (?, ?)")) {

            ps.setInt(1, purchase.getUserDto().getId());
            ps.executeUpdate();

            try (var generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    for (ProductDto product : purchase.getProductDtos()) {
                        psPurchaseProduct.setLong(1, generatedKeys.getLong(1));
                        psPurchaseProduct.setInt(2, product.getId());
                        psPurchaseProduct.addBatch();
                    }

                    psPurchaseProduct.executeBatch();
                } else {
                    throw new SQLException("Creating purchase failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    The getPurchasesByUserId method retrieves a list of purchases associated with a specific user
     ID. It uses a prepared statement to query the purchase table based on the provided user ID.
     It then retrieves the associated user and product data using the userDao and productDao
     instances.
     */
    @Override public List<PurchaseDto> getPurchasesByUserId(int userId) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "SELECT * FROM purchase WHERE fk_purchase_user = ?")) {
            ps.setInt(1, userId);

            List<PurchaseDto> purchases = new ArrayList<>();

            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    PurchaseDto purchase = new PurchaseDto();
                    purchase.setId(rs.getInt("id"));
                    purchase.setUserDto(userDao.getUserById(rs.getInt("fk_purchase_user")));

                    List<ProductDto> products = new ArrayList<>();
                    try (var psProducts = conn.prepareStatement(
                            "SELECT * FROM purchase_product WHERE purchase_id = " +
                            purchase.getId()); var rsProducts = psProducts.executeQuery()) {

                        while (rsProducts.next()) {
                            products.add(
                                    productDao.getProductById(rsProducts.getInt("product_id")));
                        }
                    }
                    purchase.setProductDtos(products);
                    purchases.add(purchase);
                }
            }

            return purchases;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /*
    The getPurchases method retrieves all purchases from the database. It uses a prepared
    statement to query the purchase table and retrieves the associated user and product data
    using the userDao and productDao instances.
     */
    @Override public List<PurchaseDto> getPurchases() {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "SELECT * FROM purchase")) {

            List<PurchaseDto> purchases = new ArrayList<>();

            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    PurchaseDto purchase = new PurchaseDto();
                    purchase.setId(rs.getInt("id"));
                    purchase.setUserDto(userDao.getUserById(rs.getInt("fk_purchase_user")));

                    List<ProductDto> products = new ArrayList<>();
                    try (var psProducts = conn.prepareStatement(
                            "SELECT * FROM purchase_product WHERE purchase_id = " +
                            purchase.getId()); var rsProducts = psProducts.executeQuery()) {

                        while (rs.next()) {
                            products.add(productDao.getProductById(rs.getInt("product_id")));
                        }
                    }
                    purchase.setProductDtos(products);
                    purchases.add(purchase);
                }
            }

            return purchases;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
