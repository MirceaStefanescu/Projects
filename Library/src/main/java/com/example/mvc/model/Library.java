package com.example.mvc.model;

import com.example.mvc.controller.AdminLogin;
import com.example.mvc.controller.LibrarianLogin;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/*
This class represents a Java Swing GUI application for a library management system. It extends
the JFrame class, which is a top-level container that represents the main window of the application.
 */
public class Library extends JFrame {

    /*
    The frame object is used to dispose of the current frame after login.
     */
    static Library frame;

    /*
    The class contains a constructor Library() that sets up the main frame of the application. It
     sets the default close operation, bounds, and creates a content pane for the frame. The
     content pane is a panel that holds the user interface components.
     */
    public Library() {
        /*
        The UI components include a label (JLabel) with the text "Library Management"
         */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblLibraryManagement = new JLabel("Library Management");
        lblLibraryManagement.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLibraryManagement.setForeground(Color.GRAY);

        /*
        Two buttons (JButton) for admin login and librarian login, and a layout manager
        The buttons have event listeners attached to them, so when clicked, they trigger the
        corresponding login functionality
         */
        JButton btnAdminLogin = new JButton("Admin Login");
        btnAdminLogin.addActionListener(e -> {
            AdminLogin.main(new String[]{});
            frame.dispose();
        });
        btnAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JButton btnLibrarianLogin = new JButton("Librarian Login");
        btnLibrarianLogin.addActionListener(arg0 -> LibrarianLogin.main(new String[]{}));
        btnLibrarianLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));

        /*
        (GroupLayout) that defines the arrangement of these components within the content pane.
         */
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addGroup(
                                                                                      gl_contentPane.createParallelGroup(
                                                                                                            Alignment.LEADING)
                                                                                                    .addGroup(
                                                                                                            gl_contentPane.createSequentialGroup()
                                                                                                                          .addGap(64)
                                                                                                                          .addComponent(
                                                                                                                                  lblLibraryManagement))
                                                                                                    .addGroup(
                                                                                                            gl_contentPane.createSequentialGroup()
                                                                                                                          .addGap(140)
                                                                                                                          .addGroup(
                                                                                                                                  gl_contentPane.createParallelGroup(
                                                                                                                                                        Alignment.TRAILING,
                                                                                                                                                        false)
                                                                                                                                                .addComponent(
                                                                                                                                                        btnLibrarianLogin,
                                                                                                                                                        Alignment.LEADING,
                                                                                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        Short.MAX_VALUE)
                                                                                                                                                .addComponent(
                                                                                                                                                        btnAdminLogin,
                                                                                                                                                        Alignment.LEADING,
                                                                                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        135,
                                                                                                                                                        Short.MAX_VALUE))))
                                                                              .addContainerGap(95,
                                                                                               Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                      .addGroup(
                                                              gl_contentPane.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(
                                                                                    lblLibraryManagement)
                                                                            .addGap(32)
                                                                            .addComponent(
                                                                                    btnAdminLogin,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    52,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(
                                                                                    ComponentPlacement.UNRELATED)
                                                                            .addComponent(
                                                                                    btnLibrarianLogin,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    53,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(70,
                                                                                             Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }

    /*
    The main() method is the entry point of the application. It invokes the Library constructor
    within the event dispatch thread to ensure proper GUI initialization and interaction.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new Library();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
