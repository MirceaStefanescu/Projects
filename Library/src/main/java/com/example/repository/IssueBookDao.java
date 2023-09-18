package com.example.repository;

import com.example.singleton.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/*
This class IssueBookDao is another data access object (DAO) that provides methods related to
issuing books from a library.
 */
public class IssueBookDao {


    /*
    This method takes a bookCallNo parameter and checks if a book with that call number exists in
     the books table.
     */
    public static boolean checkBook(String bookCallNo) {
        boolean status = false;
        try {

            /*
            It establishes a database connection, prepares an SQL statement to select
             records from the books table where the callno matches the provided bookCallNo.
             */
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from books where callno=?");
            ps.setString(1, bookCallNo);

            /*
            It then executes the query and checks if any result is returned. If a result is
            found, it sets the status to true.
             */
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        /*
        Finally, it closes the database connection and returns the status.
         */
        return status;
    }

    public static int save(String bookCallNo, int studentId, String studentName,
            String studentcontact) {
        int status = 0;
        try {
            Connection con = DB.getConnection();

            status = updateBook(bookCallNo);

            if (status > 0) {
                PreparedStatement ps = con.prepareStatement(
                        "insert into issuebooks(bookCallNo,studentId,studentName,studentcontact) " +
                        "values(?,?,?,?)");
                ps.setString(1, bookCallNo);
                ps.setInt(2, studentId);
                ps.setString(3, studentName);
                ps.setString(4, studentcontact);
                status = ps.executeUpdate();
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int updateBook(String bookCallNo) {
        int status = 0;
        int quantity = 0;
        int issued = 0;
        try {
            Connection con = DB.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("select quantity,issued from books where callno=?");
            ps.setString(1, bookCallNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
                issued = rs.getInt("issued");
            }

            if (quantity > 0) {
                PreparedStatement ps2 =
                        con.prepareStatement("update books set quantity=?,issued=? where callno=?");
                ps2.setInt(1, quantity - 1);
                ps2.setInt(2, issued + 1);
                ps2.setString(3, bookCallNo);

                status = ps2.executeUpdate();
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
