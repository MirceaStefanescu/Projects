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
This class is a view component of an MVC (Model-View-Controller) architecture for
displaying data related to issued books.
The class extends the JFrame class, which represents a window in a Java Swing application.
 */
public class ViewIssuedBooks extends JFrame {

    /*
    In the constructor ViewIssuedBooks(), the window settings are configured. The default close
    operation is set to HIDE_ON_CLOSE, meaning the window is hidden but not terminated when
    closed. The window's size and position are also set using setBounds().
     */
    public ViewIssuedBooks() {

        /*
        the main content pane of the window is created and set with a BorderLayout and an empty
        border using JPanel and setContentPane().
         */
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        String[][] data = null;
        String[] column = null;
        try {

            /*
            A database connection is established using DB.getConnection(), assuming the existence
             of a DB singleton class.
             */
            Connection con = DB.getConnection();

            /*
            A SQL query is executed to retrieve data from the "issuebooks" table. The result set
            is obtained and its metadata is extracted using ResultSetMetaData.
             */
            PreparedStatement ps = con.prepareStatement("select * from issuebooks",
                                                        ResultSet.TYPE_SCROLL_SENSITIVE,
                                                        ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            /*
            The number of columns in the result set is determined using getColumnCount(), and the
             column names are stored in the column array.
             */
            int cols = rsmd.getColumnCount();
            column = new String[cols];
            for (int i = 1; i <= cols; i++) {
                column[i - 1] = rsmd.getColumnName(i);
            }

            /*
            The cursor is moved to the last row using rs.last(), and the total number of rows is
            obtained using getRow(). The cursor is then moved back to before the first row using
            rs.beforeFirst().
             */
            rs.last();
            int rows = rs.getRow();
            rs.beforeFirst();

            /*
            A two-dimensional array data is initialized with the appropriate size based on the
            number of rows and columns. The data from the result set is retrieved and stored in
            this array.
             */
            data = new String[rows][cols];
            int count = 0;
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    data[count][i - 1] = rs.getString(i);
                }
                count++;
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        /*
        A JTable is created using the data and column arrays. This table is then added to a
        JScrollPane to enable scrolling if the table exceeds the available space.
         */
        JTable table = new JTable(data, column);
        JScrollPane sp = new JScrollPane(table);

        /*
        The scroll pane is added to the content pane's center region using contentPane.add(sp,
        BorderLayout.CENTER).
         */
        contentPane.add(sp, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewIssuedBooks frame = new ViewIssuedBooks();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
