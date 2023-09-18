package com.example.mvc.views;

import com.example.factory.AdminSuccess;
import com.example.repository.LibrarianDao;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/*
It extends the JFrame class, which is a top-level container that represents a window with a title
 bar and borders. This class is responsible for creating a graphical user interface (GUI) for
 adding a librarian.
 */
public class LibrarianForm extends JFrame {
    static LibrarianForm frame;
    private final JTextField textField;
    private final JTextField textField_1;
    private final JTextField textField_2;
    private final JTextField textField_3;
    private final JTextField textField_4;
    private final JPasswordField passwordField;


    /*
    The constructor method LibrarianForm() initializes the frame and sets its size and layout.
     */
    public LibrarianForm() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 450);

        /*
        It creates a panel (contentPane) and sets it as the content pane of the frame.
         */
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblAddLibrarian = new JLabel("Add Librarian");
        lblAddLibrarian.setForeground(Color.DARK_GRAY);
        lblAddLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 22));

        JLabel lblName = new JLabel("Name:");

        JLabel lblPassword = new JLabel("Password:");

        JLabel lblEmail = new JLabel("Email:");

        JLabel lblAddress = new JLabel("Address:");

        JLabel lblCity = new JLabel("City:");

        JLabel lblContactNo = new JLabel("Contact No:");

        textField = new JTextField();
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setColumns(10);

        passwordField = new JPasswordField();

        /*
        It adds event listeners to the "Add Librarian" button and the "Back" button to handle
        user actions.
         */
        JButton btnNewButton = new JButton("Add Librarian");
        btnNewButton.addActionListener(e -> {
            String name = textField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String email = textField_1.getText();
            String address = textField_2.getText();
            String city = textField_3.getText();
            String contact = textField_4.getText();

            int i = LibrarianDao.save(name, password, email, address, city, contact);
            if (i > 0) {
                JOptionPane.showMessageDialog(LibrarianForm.this, "Librarian added successfully!");
                AdminSuccess.main(new String[]{});
                frame.dispose();

            } else {
                JOptionPane.showMessageDialog(LibrarianForm.this, "Sorry, unable to save!");
            }
        });
        btnNewButton.setForeground(Color.DARK_GRAY);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            AdminSuccess.main(new String[]{});
            frame.dispose();
        });

        /*
        It creates and configures labels, text fields, password field, buttons, and adds them to
        the content pane using a layout manager (GroupLayout).
         */
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addGap(20).addGroup(
                                                                                      gl_contentPane.createParallelGroup(
                                                                                                            Alignment.LEADING,
                                                                                                            false).addComponent(
                                                                                                            lblPassword,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            62, Short.MAX_VALUE)
                                                                                                    .addComponent(
                                                                                                            lblName)
                                                                                                    .addComponent(
                                                                                                            lblEmail,
                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                            31,
                                                                                                            GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(
                                                                                                            lblAddress,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            Short.MAX_VALUE)
                                                                                                    .addComponent(
                                                                                                            lblCity,
                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                            31,
                                                                                                            GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(
                                                                                                            lblContactNo,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            Short.MAX_VALUE))
                                                                              .addGap(58).addGroup(
                                                                                      gl_contentPane.createParallelGroup(
                                                                                                            Alignment.LEADING,
                                                                                                            false).addComponent(
                                                                                                            textField_4,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            177,
                                                                                                            Short.MAX_VALUE)
                                                                                                    .addComponent(
                                                                                                            textField_3,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            177,
                                                                                                            Short.MAX_VALUE)
                                                                                                    .addComponent(
                                                                                                            textField_2,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            177,
                                                                                                            Short.MAX_VALUE)
                                                                                                    .addComponent(
                                                                                                            textField_1,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            177,
                                                                                                            Short.MAX_VALUE)
                                                                                                    .addComponent(
                                                                                                            textField,
                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                            177,
                                                                                                            Short.MAX_VALUE)
                                                                                                    .addComponent(
                                                                                                            passwordField))
                                                                              .addContainerGap(107,
                                                                                               Short.MAX_VALUE))
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addContainerGap(151,
                                                                                               Short.MAX_VALUE)
                                                                              .addComponent(
                                                                                      lblAddLibrarian)
                                                                              .addGap(144))
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addContainerGap(160,
                                                                                               Short.MAX_VALUE)
                                                                              .addComponent(
                                                                                      btnNewButton,
                                                                                      GroupLayout.PREFERRED_SIZE,
                                                                                      131,
                                                                                      GroupLayout.PREFERRED_SIZE)
                                                                              .addGap(133))
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addContainerGap(200,
                                                                                               Short.MAX_VALUE)
                                                                              .addComponent(btnBack)
                                                                              .addGap(169)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                      .addGroup(
                                                              gl_contentPane.createSequentialGroup()
                                                                            .addComponent(
                                                                                    lblAddLibrarian)
                                                                            .addGap(18).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.LEADING)
                                                                                                  .addGroup(
                                                                                                          gl_contentPane.createSequentialGroup()
                                                                                                                        .addComponent(
                                                                                                                                lblName)
                                                                                                                        .addGap(18)
                                                                                                                        .addComponent(
                                                                                                                                lblPassword))
                                                                                                  .addGroup(
                                                                                                          gl_contentPane.createSequentialGroup()
                                                                                                                        .addComponent(
                                                                                                                                textField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addPreferredGap(
                                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                                        .addComponent(
                                                                                                                                passwordField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                            .addGap(18).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblEmail)
                                                                                                  .addComponent(
                                                                                                          textField_1,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(18).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblAddress)
                                                                                                  .addComponent(
                                                                                                          textField_2,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(18).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblCity)
                                                                                                  .addComponent(
                                                                                                          textField_3,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(18).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblContactNo)
                                                                                                  .addComponent(
                                                                                                          textField_4,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(18)
                                                                            .addComponent(
                                                                                    btnNewButton,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    36,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(
                                                                                    ComponentPlacement.RELATED,
                                                                                    57,
                                                                                    Short.MAX_VALUE)
                                                                            .addComponent(btnBack)
                                                                            .addGap(19)));
        contentPane.setLayout(gl_contentPane);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new LibrarianForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
