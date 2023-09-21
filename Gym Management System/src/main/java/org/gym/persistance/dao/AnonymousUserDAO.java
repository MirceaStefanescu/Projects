package org.gym.persistance.dao;

import org.gym.mvc.model.user.AnonymousUser;
import org.gym.persistance.GymDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnonymousUserDAO {

    public static void saveAnonymousUser(AnonymousUser user)
    {
        try (Connection conn = GymDatabaseConnection.connect()) {
            String query =
                    "INSERT INTO AnonymousUser (username, password, firstName, lastName, address," +
                    " city, province, postalCode, age, sex) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getCity());
            statement.setString(7, user.getProvince());
            statement.setInt(8, user.getPostalCode());
            statement.setInt(9, user.getAge());
            statement.setString(10, user.getSex().toString()); // Convert enum to string

            statement.executeUpdate();

            System.out.println("Anonymous User saved to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
