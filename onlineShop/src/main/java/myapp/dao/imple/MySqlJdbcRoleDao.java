package myapp.dao.imple;

import myapp.dao.RoleDao;
import myapp.dto.RoleDto;
import myapp.utils.DBUtils;

import java.sql.SQLException;

/*
This class, MySqlJdbcRoleDao, is an implementation of the RoleDao interface. It provides a method
 getRoleById(int id) to retrieve a RoleDto object from a MySQL database by its ID.
 */
public class MySqlJdbcRoleDao implements RoleDao {

    //The getRoleById(int id) method takes an integer id as input.
    @Override public RoleDto getRoleById(int id) {
        /*
        Inside the method, it tries to establish a connection to the MySQL database using DBUtils
        .getConnection(). The var keyword is used to infer the data type of the conn variable.
        It prepares a SQL statement to select all columns from the role table where the id
        matches the provided parameter.
         */
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement(
                "SELECT * FROM role WHERE id = ?")) {

            /*
            The ps.setInt(1, id) sets the value of the first parameter in the prepared statement
            to the provided id.
             */
            ps.setInt(1, id);

            /*
            It executes the query using ps.executeQuery() to retrieve the result set.
             */
            try (var rs = ps.executeQuery()) {

                /*
                If the result set has at least one row (rs.next()), it creates a new RoleDto
                object and sets its properties (id and roleName) using values from the result set.
                 */
                if (rs.next()) {
                    RoleDto role = new RoleDto();
                    role.setId(rs.getInt("id"));
                    role.setRoleName(rs.getString("role_name"));

                    /*
                    Finally, it returns the RoleDto object if found, otherwise it returns null.
                     */
                    return role;
                }
            }

            /*
            An exception handling block is present to catch any SQLException that may occur
            during the execution of the code.
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
