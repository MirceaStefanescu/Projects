package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String JDBC_MYSQL_HOST = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DB() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_MYSQL_HOST + DB_NAME, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
