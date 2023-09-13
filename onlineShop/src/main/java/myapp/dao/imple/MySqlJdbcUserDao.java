package myapp.dao.imple;

import myapp.dao.RoleDao;
import myapp.dao.UserDao;
import myapp.dto.UserDto;
import myapp.utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
This class MySqlJdbcUserDao is an implementation of the UserDao interface. It provides methods to
interact with a MySQL database and perform some CRUD (Create, Read, Update, Delete) operations
on the user table.
 */
public class MySqlJdbcUserDao implements UserDao {

    private final RoleDao roleDao;

    {
        roleDao = new MySqlJdbcRoleDao();
    }

    /*
    Retrieves a user from the database based on the provided id. It executes a SELECT query to
    fetch the user details, including their role, from the user table. It uses the RoleDao
    instance to retrieve the role information by calling getRoleById method.
     */
    @Override public UserDto getUserById(int id) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "SELECT * FROM user WHERE id = ?")) {

            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserDto user = new UserDto();
                    user.setId(rs.getInt("id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    user.setRoleDto(roleDao.getRoleById(rs.getInt("fk_user_role")));
                    user.setMoney(rs.getBigDecimal("money"));
                    user.setCreditCard(rs.getString("credit_card"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    Retrieves a user from the database based on the provided email. Similar to getUserById, it
    executes a SELECT query to fetch the user details, including their role, from the user table.
     */
    @Override public UserDto getUserByEmail(String email) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "SELECT * FROM user WHERE email = ?")) {

            ps.setString(1, email);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserDto user = new UserDto();
                    user.setId(rs.getInt("id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    user.setRoleDto(roleDao.getRoleById(rs.getInt("fk_user_role")));
                    user.setMoney(rs.getBigDecimal("money"));
                    user.setCreditCard(rs.getString("credit_card"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    Saves a new user to the database. It executes an INSERT query to add a new row in the user
    table with the provided user details.
     */
    @Override public boolean saveUser(UserDto user) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "INSERT INTO user (first_name, last_name, email, fk_user_role, " +
                "money, credit_card, password) VALUES (?, ?, ?, ?, ?, ?, ?);")) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            if (user.getRoleDto() != null && user.getRoleDto().getId() != null) {
                ps.setInt(4, user.getRoleDto().getId());
            } else {
                ps.setNull(4, java.sql.Types.NULL);
            }
            ps.setBigDecimal(5, user.getMoney());
            ps.setString(6, user.getCreditCard());
            ps.setString(7, user.getPassword());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    Retrieves all users from the database. It executes a SELECT query to fetch all the user
    details, including their roles, from the user table. It uses the RoleDao instance to retrieve
    the role information for each user by calling getRoleById method.
     */
    @Override public List<UserDto> getUsers() {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "SELECT * FROM user"); var rs = ps.executeQuery()) {
            List<UserDto> users = new ArrayList<>();

            while (rs.next()) {
                UserDto user = new UserDto();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setRoleDto(roleDao.getRoleById(rs.getInt("fk_user_role")));
                user.setMoney(rs.getBigDecimal("money"));
                user.setCreditCard(rs.getString("credit_card"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
