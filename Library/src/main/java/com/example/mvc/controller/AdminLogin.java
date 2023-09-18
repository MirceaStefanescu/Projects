package com.example.mvc.controller;

import com.example.factory.AdminSuccess;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
This class is a Java Swing-based controller for an admin login form. It extends the JFrame class
and provides a graphical user interface (GUI) for the admin login functionality.
 */
public class AdminLogin extends JFrame {
    static AdminLogin frame;
    private final JTextField textField;
    private final JPasswordField passwordField;


    public AdminLogin() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblAdminLoginForm = new JLabel("Admin Login Form");
        lblAdminLoginForm.setForeground(Color.GRAY);
        lblAdminLoginForm.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblEnterName = new JLabel("Enter Name:");

        JLabel lblEnterPassword = new JLabel("Enter Password:");

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnLogin = new JButton("Login");

        /*
        Defining an action listener for the "Login" button, which checks the entered name and
        password against predefined values. If the values match, it opens the AdminSuccess class
        and disposes of the current frame; otherwise, it displays an error message.
         */
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (name.equals("admin") && password.equals("admin123")) {
                    AdminSuccess.main(new String[]{});
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(AdminLogin.this,
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

    /*
    Defining the main method that creates an instance of AdminLogin and sets it visible on the
    Event Dispatch Thread
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new AdminLogin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
