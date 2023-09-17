package com.example.command;

import com.example.factory.AdminSuccess;
import com.example.repository.LibrarianDao;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*
 It provides a graphical user interface for deleting a librarian record.
 */
public class DeleteLibrarian extends JFrame {
    static DeleteLibrarian frame;


    /*
    A text field where the user can enter the ID of the librarian to be deleted.
     */
    private final JTextField textField;


    /*
    This method is a constructor for a class called DeleteLibrarian. It sets up the graphical
    user interface (GUI) for deleting a librarian record.
    This method creates a window with a text field to enter an ID, a "Delete" button to trigger
    the deletion process, and a "Back" button to navigate back to the previous screen.
     */
    public DeleteLibrarian() {
        /*
        Sets the default close operation for the window to EXIT_ON_CLOSE.
         */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /*
        Sets the bounds (position and size) of the window.
         */
        setBounds(100, 100, 450, 300);
        /*
        Creates a JPanel called contentPane and sets a border for it.
         */
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        /*
        Sets the content pane of the window to contentPane.
         */
        setContentPane(contentPane);

        /*
        A label that displays the text "Enter Id:".
         */
        JLabel lblEnterId = new JLabel("Enter Id:");

        textField = new JTextField();
        textField.setColumns(10);

        /*
         A button labeled "Delete" that triggers the deletion process when clicked.
         */
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            String sid = textField.getText();
            if (sid == null || sid.trim().equals("")) {
                JOptionPane.showMessageDialog(DeleteLibrarian.this, "Id can't be blank");
            } else {
                int id = Integer.parseInt(sid);
                int i = LibrarianDao.delete(id);
                if (i > 0) {
                    JOptionPane.showMessageDialog(DeleteLibrarian.this,
                                                  "Record deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(DeleteLibrarian.this,
                                                  "Unable to delete given id!");
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));

        /*
        A button labeled "Back" that takes the user back to the previous screen when clicked.
         */
        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(e -> {
            AdminSuccess.main(new String[]{});
            frame.dispose();
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(
                                                                gl_contentPane.createSequentialGroup()
                                                                              .addGap(39)
                                                                              .addComponent(
                                                                                      lblEnterId)
                                                                              .addGap(57)
                                                                              .addComponent(
                                                                                      textField,
                                                                                      GroupLayout.PREFERRED_SIZE,
                                                                                      178,
                                                                                      GroupLayout.PREFERRED_SIZE)
                                                                              .addContainerGap(107,
                                                                                               Short.MAX_VALUE))
                                                        .addGroup(Alignment.TRAILING,
                                                                  gl_contentPane.createSequentialGroup()
                                                                                .addContainerGap(
                                                                                        175,
                                                                                        Short.MAX_VALUE)
                                                                                .addComponent(
                                                                                        btnDelete,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        109,
                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(140))
                                                        .addGroup(Alignment.TRAILING,
                                                                  gl_contentPane.createSequentialGroup()
                                                                                .addContainerGap(
                                                                                        322,
                                                                                        Short.MAX_VALUE)
                                                                                .addComponent(
                                                                                        btnNewButton,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        92,
                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                .addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                      .addGroup(
                                                              gl_contentPane.createSequentialGroup()
                                                                            .addGap(19).addGroup(
                                                                                    gl_contentPane.createParallelGroup(
                                                                                                          Alignment.LEADING)
                                                                                                  .addComponent(
                                                                                                          textField,
                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                          GroupLayout.PREFERRED_SIZE)
                                                                                                  .addComponent(
                                                                                                          lblEnterId))
                                                                            .addGap(33)
                                                                            .addComponent(btnDelete,
                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                          33,
                                                                                          GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(43)
                                                                            .addComponent(
                                                                                    btnNewButton)
                                                                            .addContainerGap(78,
                                                                                             Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }

    /*
    The main method creates an instance of DeleteLibrarian and sets it to be visible when the
    program is executed.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new DeleteLibrarian();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
