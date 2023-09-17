package com.example.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String JDBC_MYSQL_HOST = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    /*
    By making it private, it prevents the class from being instantiated from outside the class
    itself.
     */
    private DB() {
    }

    /*
    This line declares a public, static method named getConnection() that returns a Connection
    object. This method is responsible for establishing a connection to the MySQL database.
     */
    public static Connection getConnection() {
        /*
         This line starts a try block to handle any potential exceptions that may occur within
         the following code block.
         */
        try {

            /*
            This line uses the DriverManager.getConnection() method to establish a connection to
            the MySQL database. It concatenates the JDBC_MYSQL_HOST and DB_NAME variables to form
             the complete database URL. The USERNAME and PASSWORD variables are also passed as
             arguments to establish the connection.
             */
            return DriverManager.getConnection(JDBC_MYSQL_HOST + DB_NAME, USERNAME, PASSWORD);

            /*
             This line catches any SQLException that may occur within the try block and assigns
             it to the variable e.
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
         This line is reached if an exception occurs during the connection process. It returns
         null to indicate that the connection could not be established successfully.
         */
        return null;
    }

}
