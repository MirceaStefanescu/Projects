package com.example.mvc.views;

import com.example.factory.LibrarianSuccess;
import com.example.repository.IssueBookDao;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*
This class represents a form for issuing a book in a library management system. It is a part of
the MVC (Model-View-Controller) architecture.
The class extends the JFrame class, which provides a top-level container for the form. It
contains several components such as labels, text fields, and buttons for input and interaction.
 */
public class IssueBookForm extends JFrame {
    static IssueBookForm frame;
    private final JTextField textField_1;
    private final JTextField textField_2;
    private final JTextField textField_3;
    private final JTextField textField_4;


    public IssueBookForm() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 438, 414);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Issue Book ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setForeground(Color.GRAY);

        JLabel lblBookName = new JLabel("Book Callno:");

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setColumns(10);

        JLabel lblStudentId = new JLabel("Student Id:");

        JLabel lblStudentName = new JLabel("Student Name:");

        JLabel lblStudentContact = new JLabel("Student Contact:");

        JButton btnIssueBook = new JButton("Issue Book");
        btnIssueBook.addActionListener(e -> {

            String bookCallNo = textField_1.getText();
            int studentId = Integer.parseInt(textField_2.getText());
            String studentName = textField_3.getText();
            String studentContact = textField_4.getText();

            if (IssueBookDao.checkBook(bookCallNo)) {

                int i = IssueBookDao.save(bookCallNo, studentId, studentName, studentContact);

                /*
                It displays success or error messages using the JOptionPane class.
                 */
                if (i > 0) {
                    JOptionPane.showMessageDialog(IssueBookForm.this, "Book issued successfully!");
                    LibrarianSuccess.main(new String[]{});
                    frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(IssueBookForm.this, "Sorry, unable to issue!");
                }

            } else {
                JOptionPane.showMessageDialog(IssueBookForm.this, "Sorry, Callno doesn't exist!");
            }

        });

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            LibrarianSuccess.main(new String[]{});
            frame.dispose();
        });

        JLabel lblNewLabel_1 =
                new JLabel("Note: Please check Student ID Carefully before issuing book!");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setForeground(Color.RED);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addContainerGap(10,
                                                                                               Short.MAX_VALUE)
                                                                              .addGroup(
                                                                                      gl_contentPane.createParallelGroup(
                                                                                                            Alignment.LEADING)
                                                                                                    .addGroup(
                                                                                                            gl_contentPane.createSequentialGroup()
                                                                                                                          .addGroup(
                                                                                                                                  gl_contentPane.createParallelGroup(
                                                                                                                                                        Alignment.LEADING)
                                                                                                                                                .addComponent(
                                                                                                                                                        lblBookName)
                                                                                                                                                .addComponent(
                                                                                                                                                        lblStudentId)
                                                                                                                                                .addComponent(
                                                                                                                                                        lblStudentName,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                                                                        108,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(
                                                                                                                                                        lblStudentContact,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                                                                        100,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE))
                                                                                                                          .addGap(10)
                                                                                                                          .addGroup(
                                                                                                                                  gl_contentPane.createParallelGroup(
                                                                                                                                                        Alignment.LEADING)
                                                                                                                                                .addComponent(
                                                                                                                                                        textField_2,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                                                                        172,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(
                                                                                                                                                        textField_1,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                                                                        172,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(
                                                                                                                                                        textField_3,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                                                                        172,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(
                                                                                                                                                        textField_4,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                                                                        172,
                                                                                                                                                        GroupLayout.PREFERRED_SIZE))
                                                                                                                          .addGap(48))
                                                                                                    .addGroup(
                                                                                                            Alignment.TRAILING,
                                                                                                            gl_contentPane.createSequentialGroup()
                                                                                                                          .addGap(20)
                                                                                                                          .addGroup(
                                                                                                                                  gl_contentPane.createParallelGroup(
                                                                                                                                                        Alignment.TRAILING)
                                                                                                                                                .addComponent(
                                                                                                                                                        lblNewLabel_1)
                                                                                                                                                .addGroup(
                                                                                                                                                        gl_contentPane.createSequentialGroup()
                                                                                                                                                                      .addComponent(
                                                                                                                                                                              btnIssueBook,
                                                                                                                                                                              GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                              100,
                                                                                                                                                                              GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                      .addGap(47)
                                                                                                                                                                      .addComponent(
                                                                                                                                                                              btnBack)))
                                                                                                                          .addGap(100))))
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addGap(146)
                                                                              .addComponent(
                                                                                      lblNewLabel)
                                                                              .addContainerGap(235,
                                                                                               Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                      .addGroup(
                                                              gl_contentPane.createSequentialGroup()
                                                                            .addGap(37)
                                                                            .addComponent(
                                                                                    lblNewLabel)
                                                                            .addGap(43).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblBookName)
                                                                                                  .addComponent(
                                                                                                          textField_1,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(28).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblStudentId)
                                                                                                  .addComponent(
                                                                                                          textField_2,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(28).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblStudentName)
                                                                                                  .addComponent(
                                                                                                          textField_3,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(26).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          lblStudentContact)
                                                                                                  .addComponent(
                                                                                                          textField_4,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE))
                                                                            .addPreferredGap(
                                                                                    ComponentPlacement.UNRELATED)
                                                                            .addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.BASELINE)
                                                                                                  .addComponent(
                                                                                                          btnIssueBook,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          31,
                                                                                                          GroupLayout.PREFERRED_SIZE)
                                                                                                  .addComponent(
                                                                                                          btnBack))
                                                                            .addGap(18)
                                                                            .addComponent(
                                                                                    lblNewLabel_1)
                                                                            .addGap(25)));
        contentPane.setLayout(gl_contentPane);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new IssueBookForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
