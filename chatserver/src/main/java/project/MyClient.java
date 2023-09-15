package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/*
The MyClient class is a part of a chat application. It represents the client-side of the chat
system.
It imports necessary classes from the javax.swing and java.awt packages for creating the
graphical user interface (GUI), handling events, and working with sockets.
The class implements the ActionListener interface to handle user actions.
 */
class MyClient implements ActionListener {

    /*
    It defines instance variables for the client's socket, input/output streams, buttons, chat
    window, text areas, and user list.
     */ Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    JButton sendButton, logoutButton, loginButton, exitButton;
    JFrame chatWindow;
    JTextArea txtBroadcast;
    JTextArea txtMessage;
    JList usersList;


    /*
    The constructor method MyClient() initializes the GUI by calling the displayGUI() method.
     */
    public MyClient() {
        displayGUI();
        //	clientChat();
    }

    //The main() method creates an instance of MyClient to start the chat client.
    public static void main(String[] args) {
        new MyClient();
    }

    //The displayGUI() method sets up the chat window, text areas, buttons, and event listeners.
    public void displayGUI() {
        chatWindow = new JFrame();
        txtBroadcast = new JTextArea(5, 30);
        txtBroadcast.setEditable(false);
        txtMessage = new JTextArea(2, 20);
        usersList = new JList();

        sendButton = new JButton("Send");
        logoutButton = new JButton("Log out");
        loginButton = new JButton("Log in");
        exitButton = new JButton("Exit");

        JPanel center1 = new JPanel();
        center1.setLayout(new BorderLayout());
        center1.add(new JLabel("Broad Cast messages from all online users", SwingConstants.CENTER),
                    "North");
        center1.add(new JScrollPane(txtBroadcast), "Center");

        JPanel south1 = new JPanel();
        south1.setLayout(new FlowLayout());
        south1.add(new JScrollPane(txtMessage));
        south1.add(sendButton);

        JPanel south2 = new JPanel();
        south2.setLayout(new FlowLayout());
        south2.add(loginButton);
        south2.add(logoutButton);
        south2.add(exitButton);

        JPanel south = new JPanel();
        south.setLayout(new GridLayout(2, 1));
        south.add(south1);
        south.add(south2);

        JPanel east = new JPanel();
        east.setLayout(new BorderLayout());
        east.add(new JLabel("Online Users", SwingConstants.CENTER), "East");
        east.add(new JScrollPane(usersList), "South");

        chatWindow.add(east, "East");

        chatWindow.add(center1, "Center");
        chatWindow.add(south, "South");

        chatWindow.pack();
        chatWindow.setTitle("Login for Chat");
        chatWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        chatWindow.setVisible(true);
        sendButton.addActionListener(this);
        logoutButton.addActionListener(this);
        loginButton.addActionListener(this);
        exitButton.addActionListener(this);
        logoutButton.setEnabled(false);
        loginButton.setEnabled(true);
        txtMessage.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent fe) {
                txtMessage.selectAll();
            }
        });

        chatWindow.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent ev) {
                if (s != null) {
                    JOptionPane.showMessageDialog(chatWindow, "u r logged out right now. ", "Exit",
                                                  JOptionPane.INFORMATION_MESSAGE);
                    logoutSession();
                }
                System.exit(0);
            }
        });
    }

    /*
    The actionPerformed() method handles button clicks and performs appropriate actions based on
    the clicked button.
     */
    public void actionPerformed(ActionEvent ev) {
        JButton temp = (JButton) ev.getSource();
        if (temp == sendButton) {
            if (s == null) {
                JOptionPane.showMessageDialog(chatWindow,
                                              "You are not logged in. Please login first");
                return;
            }
            try {
                dos.writeUTF(txtMessage.getText());
                txtMessage.setText("");
            } catch (Exception e) {
                txtBroadcast.append("\nSend button click :" + e);
            }
        }
        if (temp == loginButton) {
            String uname = JOptionPane.showInputDialog(chatWindow, "Enter your nick name: ");
            if (uname != null)
                clientChat(uname);
        }
        if (temp == logoutButton) {
            if (s != null)
                logoutSession();
        }
        if (temp == exitButton) {
            if (s != null) {
                JOptionPane.showMessageDialog(chatWindow, "You are logged out right now. ", "Exit",
                                              JOptionPane.INFORMATION_MESSAGE);
                logoutSession();
            }
            System.exit(0);
        }
    }

    /*
    The logoutSession() method is called when the user logs out, and it closes the socket
    connection and updates the GUI.
     */
    public void logoutSession() {
        if (s == null)
            return;
        try {
            dos.writeUTF(MyServer.LOGOUT_MESSAGE);
            Thread.sleep(500);
            s = null;
        } catch (Exception e) {
            txtBroadcast.append("\n inside logoutSession Method" + e);
        }

        logoutButton.setEnabled(false);
        loginButton.setEnabled(true);
        chatWindow.setTitle("Login for Chat");
    }

    /*
    The clientChat() method is called when the user logs in, and it establishes a socket
    connection, sets up input/output streams, and starts a separate thread for receiving messages
     from the server.
     */
    public void clientChat(String uname) {
        try {
            s = new Socket(InetAddress.getLocalHost(), MyServer.PORT);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            ClientThread ct = new ClientThread(dis, this);
            Thread t1 = new Thread(ct);
            t1.start();
            dos.writeUTF(uname);
            chatWindow.setTitle(uname + " Chat Window");
        } catch (Exception e) {
            txtBroadcast.append("\nClient Constructor " + e);
        }
        logoutButton.setEnabled(true);
        loginButton.setEnabled(false);
    }
}
