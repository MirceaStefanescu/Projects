package com.example.mvc.views;

import com.example.singleton.DB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


/*
This class, ViewBooks, is a Java Swing class that extends the JFrame class. It is responsible for
 displaying a table of books using a JTable component.
 */
public class ViewBooks extends JFrame {

    /*
    The constructor of the class sets up the frame by configuring its properties, such as the
    default close operation and the size and position of the frame.
     */
    public ViewBooks() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        String[][] data = null;
        String[] column = null;

        /*
        Within a try-catch block, the class establishes a database connection using the DB
        .getConnection() method from the com.example.singleton.DB class. It then prepares and
        executes a SQL query to retrieve all records from the "books" table.
         */
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps =
                    con.prepareStatement("select * from books", ResultSet.TYPE_SCROLL_SENSITIVE,
                                         ResultSet.CONCUR_UPDATABLE);

            /*
            It retrieves the metadata from the ResultSet object to determine the number of
            columns and their names.
             */
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            column = new String[cols];
            for (int i = 1; i <= cols; i++) {
                column[i - 1] = rsmd.getColumnName(i);
            }

            /*
            rs.last(): This method moves the cursor to the last row of the ResultSet. By doing
            this, it positions the cursor at the end of the result set.
             */
            rs.last();

            /*
            int rows = rs.getRow(): The getRow() method returns the current row number of the
            cursor. Since the cursor is now at the last row, this will give us the total number
            of rows in the result set.
             */
            int rows = rs.getRow();

            /*
            rs.beforeFirst(): This method moves the cursor back to before the first row of the
            ResultSet. It ensures that subsequent iterations or operations start from the
            beginning of the result set.
             */
            rs.beforeFirst();

            /*
            It creates a 2D array called data to hold the retrieved data from the result set, and
             a 1D array called column to hold the column names.
             */
            data = new String[rows][cols];
            int count = 0;

            /*
            It iterates through the result set and stores the data in the data array.
             */
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    data[count][i - 1] = rs.getString(i);
                }
                count++;
            }

            /*
            It closes the database connection.
             */
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        /*
        It creates a JTable component using the data and column arrays.
         */
        JTable table = new JTable(data, column);

        /*
        It creates a JScrollPane that wraps the JTable to provide scrollable functionality.
         */
        JScrollPane sp = new JScrollPane(table);

        /*
        It adds the JScrollPane to the contentPane using the BorderLayout.CENTER constraint.
         */
        contentPane.add(sp, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewBooks frame = new ViewBooks();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
