package com.example.mvc.controller;

import com.example.factory.LibrarianSuccess;
import com.example.repository.LibrarianDao;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
This class is a Java Swing-based GUI application for the librarian login form. It extends the
JFrame class to create a window for the login form.
 */
public class LibrarianLogin extends JFrame {
    static LibrarianLogin frame;
    private final JTextField textField;
    private final JPasswordField passwordField;


    public LibrarianLogin() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblAdminLoginForm = new JLabel("Librarian Login Form");
        lblAdminLoginForm.setForeground(Color.GRAY);
        lblAdminLoginForm.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblEnterName = new JLabel("Enter Name:");

        JLabel lblEnterPassword = new JLabel("Enter Password:");

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnLogin = new JButton("Login");

        /*
        When the login button is clicked, an action listener is triggered. It retrieves the
        values entered in the name and password fields, and validates them using the LibrarianDao
        .validate() method. If the validation is successful, it opens the LibrarianSuccess window
         and closes the current login window. Otherwise, it displays an error message dialog.
         */
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (LibrarianDao.validate(name, password)) {
                    LibrarianSuccess.main(new String[]{});
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(LibrarianLogin.this,
                                                  "Sorry, Username or Password Error",
                                                  "Login Error!", JOptionPane.ERROR_MESSAGE);
                    textField.setText("");
                    passwordField.setText("");
                }
            }
        });

        passwordField = new JPasswordField();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addGroup(
                                                                                      gl_contentPane.createParallelGroup(
                                                                                                            Alignment.LEADING)
                                                                                                    .addGroup(
                                                                                                            gl_contentPane.createSequentialGroup()
                                                                                                                          .addGap(124)
                                                                                                                          .addComponent(
                                                                                                                                  lblAdminLoginForm))
                                                                                                    .addGroup(
                                                                                                            gl_contentPane.createSequentialGroup()
                                                                                                                          .addGap(19)
                                                                                                                          .addGroup(
                                                                                                                                  gl_contentPane.createParallelGroup(
                                                                                                                                                        Alignment.LEADING)
                                                                                                                                                .addComponent(
                                                                                                                                                        lblEnterName)
                                                                                                                                                .addComponent(
                                                                                                                                                        lblEnterPassword))
                                                                                                                          .addGap(47)
                                                                                                                          .addGroup(
                                                                                                                                  gl_contentPane.createParallelGroup(
                                                                                                                                                        Alignment.LEADING,
                                                                                                                                                        false)
                                                                                                                                                .addComponent(
                                                                                                                                                        passwordField)
                                                                                                                                                .addComponent(
                                                                                                                                                        textField,
                                                                                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        172,
                                                                                                                                                        Short.MAX_VALUE))))
                                                                              .addContainerGap(107,
                                                                                               Short.MAX_VALUE))
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addContainerGap(187,
                                                                                               Short.MAX_VALUE)
                                                                              .addComponent(
                                                                                      btnLogin,
                                                                                      GroupLayout.PREFERRED_SIZE,
                                                                                      86,
                                                                                      GroupLayout.PREFERRED_SIZE)
                                                                              .addGap(151)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                      .addGroup(
                                                              gl_contentPane.createSequentialGroup()
                                                                            .addComponent(
                                                                                    lblAdminLoginForm)
                                                                            .addGap(26).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblEnterName)
                                                                                                  .addComponent(
                                                                                                          textField,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(28).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblEnterPassword)
                                                                                                  .addComponent(
                                                                                                          passwordField,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(18)
                                                                            .addComponent(btnLogin,
                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                          37,
                                                                                          GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(80,
                                                                                             Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new LibrarianLogin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
