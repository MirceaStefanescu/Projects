package com.example.repository;

import com.example.singleton.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
This class BookDao is a data access object (DAO) that provides methods to interact with a
database table called books. It has a static method save that takes several parameters such as
callNo, name, author, publisher, and quantity.
 */
public class BookDao {
    public static int save(String callNo, String name, String author, String publisher,
            int quantity) {
        if (callNo.isEmpty() || name.isEmpty() || author.isEmpty() || publisher.isEmpty() ||
            quantity <= 0) {
            return -1; // Invalid input, return a custom status to indicate failure
        }

        try (Connection con = DB.getConnection();
             PreparedStatement checkPs = con.prepareStatement(
                     "SELECT COUNT(*) FROM books WHERE callNo = ?");
             PreparedStatement insertPs = con.prepareStatement(
                     "INSERT INTO books(callNo, name, author, publisher, quantity) VALUES (?, ?, " +
                     "?, ?, ?)")) {

            checkPs.setString(1, callNo);
            ResultSet resultSet = checkPs.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                System.out.println("Duplicate entry for callNo: " + callNo);
                return -2; // Set a custom status to indicate a duplicate entry
            }

            insertPs.setString(1, callNo);
            insertPs.setString(2, name);
            insertPs.setString(3, author);
            insertPs.setString(4, publisher);
            insertPs.setInt(5, quantity);

            return insertPs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
