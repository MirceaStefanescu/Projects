package myapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
The DBUtils class is a utility class that provides a method for establishing a database
connection using JDBC (Java Database Connectivity).
 */
public class DBUtils {

    private static final String JDBC_MYSQL_HOST = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "learn_it_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    /*
    The class has a private constructor, which ensures that instances of this class cannot be
    created. This class is intended to be used as a utility class with only static methods.
     */
    private DBUtils() {
    }

    /*
    The getConnection method is a public static method that returns a Connection object. It uses
    the DriverManager.getConnection method to establish a connection to the MySQL database using
    the provided connection URL, database name, username, and password.
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_MYSQL_HOST + DB_NAME, USERNAME, PASSWORD);

            /*
            If an exception occurs during the connection process, a RuntimeException is thrown.
             */
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
