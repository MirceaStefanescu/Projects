package com.example.repository;

import com.example.singleton.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

/*
This class BookDao is a data access object (DAO) that provides methods to interact with a
database table called books. It has a static method save that takes several parameters such as
callNo, name, author, publisher, and quantity.
 */
public class BookDao {
    public static int save(String callNo, String name, String author, String publisher,
            int quantity) {
        int status = 1;
        try {

            if (callNo.isEmpty() && name.isEmpty() && author.isEmpty() && publisher.isEmpty() &&
                quantity <= 0) {
                return -1;
            }
            /*
            Inside the save method, it establishes a database connection using the DB
            .getConnection() method from the com.example.singleton.DB class. Then, it prepares an
             SQL statement to insert a new record into the books table with the provided values.
             */
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "insert into books(callNo,name,author,publisher,quantity) values(?,?,?,?,?)");

            /*
            The method sets the values for each parameter in the prepared statement using the ps
            .setString and ps.setInt methods. After executing the update statement with ps
            .executeUpdate(), it returns the status of the execution.
             */
            ps.setString(1, callNo);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setInt(5, quantity);
            status = ps.executeUpdate();
            con.close();

            /*
            If any exception occurs during the database operations, it prints the stack trace
            using e.printStackTrace(). Finally, the method closes the database connection before
            returning the status.
             */
        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle the exception for duplicate entry of callNo
            System.out.println("Duplicate entry for callNo: " + callNo);
            e.printStackTrace();
            status = -2; // Set a custom status to indicate a duplicate entry
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
