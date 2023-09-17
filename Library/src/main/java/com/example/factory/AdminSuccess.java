package com.example.factory;

import com.example.command.DeleteLibrarian;
import com.example.mvc.model.Library;
import com.example.mvc.views.LibrarianForm;
import com.example.mvc.views.ViewLibrarian;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*
This class extends the JFrame class, which is a part of the Swing
library in Java. It represents a GUI window for the admin section of a library management system.
 The main purpose of this class is to create and display the admin interface with various buttons
  for managing librarians.
 */
public class AdminSuccess extends JFrame {
    static AdminSuccess frame;

    public AdminSuccess() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 371);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblAdminSection = new JLabel("Admin Section");
        lblAdminSection.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblAdminSection.setForeground(Color.GRAY);

        JButton btnNewButton = new JButton("Add Librarian");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.addActionListener(e -> {
            LibrarianForm.main(new String[]{});
            frame.dispose();
        });

        JButton btnViewLibrarian = new JButton("View Librarian");
        btnViewLibrarian.addActionListener(arg0 -> ViewLibrarian.main(new String[]{}));
        btnViewLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JButton btnDeleteLibrarian = new JButton("Delete Librarian");
        btnDeleteLibrarian.addActionListener(e -> {
            DeleteLibrarian.main(new String[]{});
            frame.dispose();
        });
        btnDeleteLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(arg0 -> {
            Library.main(new String[]{});
            frame.dispose();
        });
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addContainerGap(150,
                                                                                               Short.MAX_VALUE)
                                                                              .addComponent(
                                                                                      lblAdminSection,
                                                                                      GroupLayout.PREFERRED_SIZE,
                                                                                      151,
                                                                                      GroupLayout.PREFERRED_SIZE)
                                                                              .addGap(123))
                                                        .addGroup(Alignment.LEADING,
                                                                  gl_contentPane.createSequentialGroup()
                                                                                .addGap(134)
                                                                                .addGroup(
                                                                                        gl_contentPane.createParallelGroup(
                                                                                                              Alignment.LEADING)
                                                                                                      .addComponent(
                                                                                                              btnLogout,
                                                                                                              GroupLayout.PREFERRED_SIZE,
                                                                                                              181,
                                                                                                              GroupLayout.PREFERRED_SIZE)
                                                                                                      .addComponent(
                                                                                                              btnDeleteLibrarian,
                                                                                                              GroupLayout.PREFERRED_SIZE,
                                                                                                              181,
                                                                                                              GroupLayout.PREFERRED_SIZE)
                                                                                                      .addComponent(
                                                                                                              btnViewLibrarian,
                                                                                                              GroupLayout.PREFERRED_SIZE,
                                                                                                              181,
                                                                                                              GroupLayout.PREFERRED_SIZE)
                                                                                                      .addComponent(
                                                                                                              btnNewButton,
                                                                                                              GroupLayout.PREFERRED_SIZE,
                                                                                                              181,
                                                                                                              GroupLayout.PREFERRED_SIZE))
                                                                                .addContainerGap(
                                                                                        109,
                                                                                        Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                      .addGroup(
                                                              gl_contentPane.createSequentialGroup()
                                                                            .addComponent(
                                                                                    lblAdminSection,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    40,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(11)
                                                                            .addComponent(
                                                                                    btnNewButton,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    49,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18)
                                                                            .addComponent(
                                                                                    btnViewLibrarian,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    49,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18)
                                                                            .addComponent(
                                                                                    btnDeleteLibrarian,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    49,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18)
                                                                            .addComponent(btnLogout,
                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                          49,
                                                                                          GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(21,
                                                                                             Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }


    /*
    The entry point of the program. Inside the main method, an instance of AdminSuccess is
    created and made visible on the screen.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new AdminSuccess();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
