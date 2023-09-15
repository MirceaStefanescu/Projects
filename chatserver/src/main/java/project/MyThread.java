package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


/*
The MyThread class is a threaded class that handles client communication on the
 server side.
 The class imports the necessary packages, including java.io.DataInputStream, java.io
 .DataOutputStream, and java.net.Socket, to work with input/output streams and network sockets.
 It implements the Runnable interface, indicating that its instances can be executed as threads.
 */
class MyThread implements Runnable {

    /*
    The class defines instance variables s, al, users, and username. s represents the client
    socket, al and users are ArrayList objects storing client connections and user information,
    respectively, and username represents the username of the client associated with this thread.
     */

    Socket s;
    ArrayList al;
    ArrayList users;
    String username;


    /*
    The constructor MyThread(Socket s, ArrayList al, ArrayList users) initializes the instance
    variables and performs necessary setup tasks when a new client connection is established. It
    reads the username from the client's input stream, adds the client socket and username to the
     respective ArrayList objects, broadcasts the login message to all clients, and sends the
     updated user list to all clients.
     */
    MyThread(Socket s, ArrayList al, ArrayList users) {
        this.s = s;
        this.al = al;
        this.users = users;
        try {
            DataInputStream dis = new DataInputStream(s.getInputStream());
            username = dis.readUTF();
            al.add(s);
            users.add(username);
            tellEveryOne("****** " + username + " Logged in at " + (new Date()) + " ******");
            sendNewUserList();
        } catch (Exception e) {
            System.err.println("com.MyThread constructor  " + e);
        }
    }

    /*
    The run() method is the entry point for the thread's execution.
     */
    public void run() {
        String s1;
        try {
            /*
            It continuously reads messages from the client's input stream until the client sends
            a logout message.
             */
            DataInputStream dis = new DataInputStream(s.getInputStream());
            do {
                s1 = dis.readUTF();
                if (s1.equalsIgnoreCase(MyServer.LOGOUT_MESSAGE))
                    break;
                //	System.out.println("received from "+s.getPort());

                /*
                Each message received is broadcast to all clients by calling the tellEveryOne()
                method.
                 */
                tellEveryOne(username + " said: " + " : " + s1);
            } while (true);
            DataOutputStream tdos = new DataOutputStream(s.getOutputStream());

            /*
            After the client sends a logout message, the thread sends a logout message back to
            the client, removes the client's username from the users list, broadcasts the logout
            message to all clients, sends the updated user list to all clients, removes the
            client socket from the al list, and closes the client socket.
             */
            tdos.writeUTF(MyServer.LOGOUT_MESSAGE);
            tdos.flush();
            users.remove(username);
            tellEveryOne("****** " + username + " Logged out at " + (new Date()) + " ******");
            sendNewUserList();
            al.remove(s);
            s.close();

        } catch (Exception e) {
            System.out.println("com.MyThread Run" + e);
        }
    }


    /*
    The sendNewUserList() method sends the updated user list to all connected clients by calling
    the tellEveryOne() method with a special message MyServer.UPDATE_USERS followed by the
    serialized users list.
     */
    public void sendNewUserList() {
        tellEveryOne(MyServer.UPDATE_USERS + users.toString());

    }

    /*
    The tellEveryOne(String s1) method iterates through all client sockets stored in the al list
    and sends the provided message s1 to each client through its output stream.
     */
    public void tellEveryOne(String s1) {
        Iterator i = al.iterator();
        while (i.hasNext()) {
            try {
                Socket temp = (Socket) i.next();
                DataOutputStream dos = new DataOutputStream(temp.getOutputStream());
                dos.writeUTF(s1);
                dos.flush();
                //System.out.println("sent to : "+temp.getPort()+"  : "+ s1);
            } catch (Exception e) {
                System.err.println("TellEveryOne " + e);
            }
        }
    }
}
