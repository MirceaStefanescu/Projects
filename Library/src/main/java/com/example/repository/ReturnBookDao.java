package com.example.repository;

import com.example.singleton.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBookDao {
    public static int delete(String bookcallno, int studentid) {
        int status = 0; // Initialize status to indicate failure

        if (bookcallno == null || bookcallno.isEmpty() || studentid <= 0) {
            return -1; // Invalid input, return a custom status to indicate failure
        }

        try (Connection con = DB.getConnection()) {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM issuebooks WHERE bookcallno=? AND studentid=?");

                // Update the book status
                int updateStatus = updateBook(bookcallno);
                if (updateStatus <= 0) {
                    // Return failure if book update fails
                    return status;
                }

                // Set the values for the prepared statement
                ps.setString(1, bookcallno);
                ps.setInt(2, studentid);

                // Execute the delete query
                status = ps.executeUpdate();
            } else {
                System.out.println("Connection is null. Cannot execute the query.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int updateBook(String bookcallno) {
        int status = 0; // Initialize status to indicate failure
        int quantity = 0;
        int issued = 0;

        try (Connection con = DB.getConnection()) {
            if (con != null) {
                PreparedStatement selectPs =
                        con.prepareStatement("SELECT quantity, issued FROM books WHERE callno=?");
                PreparedStatement updatePs = con.prepareStatement(
                        "UPDATE books SET quantity=?, issued=? WHERE callno=?");

                // Set the value for the select query prepared statement
                selectPs.setString(1, bookcallno);

                // Execute the select query
                try (ResultSet rs = selectPs.executeQuery()) {
                    if (rs.next()) {
                        quantity = rs.getInt("quantity");
                        issued = rs.getInt("issued");
                    }
                }

                if (issued > 0) {
                    // Set the values for the update query prepared statement
                    updatePs.setInt(1, quantity + 1);
                    updatePs.setInt(2, issued - 1);
                    updatePs.setString(3, bookcallno);

                    // Execute the update query
                    status = updatePs.executeUpdate();
                }

            } else {
                System.out.println("Connection is null. Cannot execute the query.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
