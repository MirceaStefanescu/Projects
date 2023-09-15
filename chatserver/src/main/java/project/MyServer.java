package project;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
The MyServer class in the given code is a simple server implementation that listens for client
connections and handles them using multiple threads.
The class imports the necessary packages, including java.net.ServerSocket and java.net.Socket, to
 work with network sockets.
 */
public class MyServer {

    /*
    It declares two constant variables: PORT, which represents the server's port number, and
    UPDATE_USERS and LOGOUT_MESSAGE, which are string constants used for communication with clients.
     */
    public static final int PORT = 10;
    public static final String UPDATE_USERS = "updateuserslist:";
    public static final String LOGOUT_MESSAGE = "@@logoutme@@:";

    /*
    It defines two ArrayList objects, al and users, which are used to store client connections
    and user information, respectively.
     */

    ArrayList al = new ArrayList();
    ArrayList users = new ArrayList();

    /*
    The class declares a ServerSocket object ss and a Socket object s for handling incoming
    connections.
     */

    ServerSocket ss;
    Socket s;

    /*
    The constructor MyServer() sets up the server by creating a ServerSocket instance on the
    specified port. It then enters an infinite loop where it accepts incoming client connections
    using ss.accept().
     */
    public MyServer() {
        try {
            ss = new ServerSocket(PORT);
            System.out.println("Server Started " + ss);
            while (true) {
                s = ss.accept();

                /*
                For each client connection received, a new MyThread object is created, passing in
                 the client socket (s) along with the ArrayList objects al and users. A new
                 Thread is then created with this MyThread instance and started to handle the
                 client's requests concurrently.
                 */
                Runnable r = new MyThread(s, al, users);
                Thread t = new Thread(r);
                t.start();
                System.out.println("Total alive clients : " + ss.toString());
            }
        } catch (Exception e) {
            System.err.println("Server constructor" + e);
        }
    }

    /*
    The main() method creates an instance of MyServer to start the server.
     */
    public static void main(String[] args) {
        new MyServer();
    }
}

