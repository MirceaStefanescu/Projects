package org.gym.persistance.dao;

import org.gym.mvc.model.user.AnonymousUser;
import org.gym.persistance.GymDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnonymousUserDAO {
    public static void saveAnonymousUser(AnonymousUser user) {
        try (Connection conn = GymDatabaseConnection.connect()) {
            String query =
                    "INSERT INTO AnonymousUser (username, password, firstName, lastName, address," +
                    " " + "city, province, postalCode, age, sex) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            // presupunem ca Profile contine aceste metode
            statement.setString(1, user.getProfile().getUsername());
            statement.setString(2, user.getProfile().getPassword());
            statement.setString(3, user.getProfile().getFirstName());
            statement.setString(4, user.getProfile().getLastName());
            statement.setString(5, user.getProfile().getAddress());
            statement.setString(6, user.getProfile().getCity());
            statement.setString(7, user.getProfile().getProvince());
            statement.setString(8, user.getProfile().getPostalCode());
            statement.setInt(9, user.getProfile().getAge());
            statement.setString(10,
                    user.getProfile().getSex().toString()); // Convert enum to string
            statement.executeUpdate();
            System.out.println("AnonymousUser saved to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
