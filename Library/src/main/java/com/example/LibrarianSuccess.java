package com.example;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LibrarianSuccess extends JFrame {
    static LibrarianSuccess frame;


    public LibrarianSuccess() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 433);
        JPanel contentPane = new JPanel();
        contentPane.setForeground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblLibrarianSection = new JLabel("Librarian Section - JavaTpoint");
        lblLibrarianSection.setFont(new Font("Tahoma", Font.PLAIN, 22));

        JButton btnNewButton = new JButton("Add Books");
        btnNewButton.addActionListener(e -> {
            BooksForm.main(new String[]{});
            frame.dispose();
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnViewBooks = new JButton("View Books");
        btnViewBooks.addActionListener(arg0 -> ViewBooks.main(new String[]{}));
        btnViewBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnIssueBook = new JButton("Issue Book");
        btnIssueBook.addActionListener(e -> {
            IssueBookForm.main(new String[]{});
            frame.dispose();
        });
        btnIssueBook.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnViewIssuedBooks = new JButton("View Issued Books");
        btnViewIssuedBooks.addActionListener(e -> ViewIssuedBooks.main(new String[]{}));
        btnViewIssuedBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.addActionListener(e -> {
            ReturnBook.main(new String[]{});
            frame.dispose();
        });
        btnReturnBook.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            Library.main(new String[]{});
            frame.dispose();
        });
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(Alignment.TRAILING,
                                                                  gl_contentPane.createSequentialGroup()
                                                                                .addContainerGap(81,
                                                                                                 Short.MAX_VALUE)
                                                                                .addComponent(
                                                                                        lblLibrarianSection)
                                                                                .addGap(54))
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addGap(132).addGroup(
                                                                                      gl_contentPane.createParallelGroup(
                                                                                                            Alignment.LEADING)
                                                                                                    .addComponent(
                                                                                                            btnLogout,
                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                            191,
                                                                                                            GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(
                                                                                                            btnReturnBook,
                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                            191,
                                                                                                            GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(
                                                                                                            btnViewIssuedBooks,
                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                            191,
                                                                                                            GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(
                                                                                                            btnIssueBook,
                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                            191,
                                                                                                            GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(
                                                                                                            btnViewBooks,
                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                            191,
                                                                                                            GroupLayout.PREFERRED_SIZE)
                                                                                                    .addComponent(
                                                                                                            btnNewButton,
                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                            191,
                                                                                                            GroupLayout.PREFERRED_SIZE))
                                                                              .addContainerGap(101,
                                                                                               Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                      .addGroup(
                                                              gl_contentPane.createSequentialGroup()
                                                                            .addContainerGap()
                                                                            .addComponent(
                                                                                    lblLibrarianSection)
                                                                            .addGap(18)
                                                                            .addComponent(
                                                                                    btnNewButton,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    37,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18)
                                                                            .addComponent(
                                                                                    btnViewBooks,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    37,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18)
                                                                            .addComponent(
                                                                                    btnIssueBook,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    37,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18)
                                                                            .addComponent(
                                                                                    btnViewIssuedBooks,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    37,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18)
                                                                            .addComponent(
                                                                                    btnReturnBook,
                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                    37,
                                                                                    GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(18)
                                                                            .addComponent(btnLogout,
                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                          37,
                                                                                          GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap(16,
                                                                                             Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new LibrarianSuccess();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
