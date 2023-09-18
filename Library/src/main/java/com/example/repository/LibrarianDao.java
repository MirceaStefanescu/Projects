package com.example.repository;

import com.example.singleton.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianDao {
    public static int save(String name, String password, String email, String address, String city,
            String contact) {
        int status = 0;
        if (name == null || password == null || email == null || address == null || city == null ||
            contact == null) {
            System.out.println("One or more parameters are null. Cannot execute the query.");
            return status;
        }

        try (Connection con = DB.getConnection()) {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO librarian (name, password, email, address, city, contact) " +
                        "VALUES (?, ?, ?, ?, ?, ?)");

                ps.setString(1, name);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setString(4, address);
                ps.setString(5, city);
                ps.setString(6, contact);

                status = ps.executeUpdate();
            } else {
                System.out.println("Connection is null. Cannot execute the query.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int delete(int id) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM librarian WHERE id = ?")) {

            ps.setInt(1, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean validate(String name, String password) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT COUNT(*) AS count FROM librarian WHERE name = ? AND password = ?")) {

            ps.setString(1, name);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
