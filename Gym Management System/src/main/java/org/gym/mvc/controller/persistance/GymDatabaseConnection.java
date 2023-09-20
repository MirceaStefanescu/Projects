package org.gym.mvc.controller.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GymDatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    public static Connection connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to disconnect from the database: " + e.getMessage());
        }
    }
}
