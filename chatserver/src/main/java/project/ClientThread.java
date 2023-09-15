package project;

import java.io.DataInputStream;
import java.util.StringTokenizer;
import java.util.Vector;

/*
The ClientThread class implements the Runnable interface, indicating that it can be executed as a
 separate thread.
 */
class ClientThread implements Runnable {

    /*
    Represents the input stream used to receive data from the server.
     */

    DataInputStream dis;

    /*
    Represents the client object associated with this thread.*/

    MyClient client;

    ClientThread(DataInputStream dis, MyClient client) {
        this.dis = dis;
        this.client = client;
    }

    //This method is executed when the thread starts running.
    public void run() {

        /*
        It checks the content of the received string (s2) to determine the appropriate action:
         */
        String s2 = "";
        do {
            try {
                s2 = dis.readUTF();

                /*
                If the string starts with the value MyServer.UPDATE_USERS, it invokes the
                updateUsersList() method to update the list of users.
                 */
                if (s2.startsWith(MyServer.UPDATE_USERS))
                    updateUsersList(s2);

                /*
                If the string is equal to MyServer.LOGOUT_MESSAGE, it breaks out of the loop,
                ending the execution of the thread.
                 */
                else if (s2.equals(MyServer.LOGOUT_MESSAGE))
                    break;

                /*
                Otherwise, it appends the received string (s2) to the txtBroadcast component of
                the client object.
                 */
                else
                    client.txtBroadcast.append("\n" + s2);

                /*
                It then adjusts the caret position of the txtBroadcast component to display the
                latest message.
                 */
                int lineOffset = client.txtBroadcast.getLineStartOffset(
                        client.txtBroadcast.getLineCount() - 1);
                client.txtBroadcast.setCaretPosition(lineOffset);

                /*
                If an exception occurs during the execution of the loop, it appends an error
                message to the txtBroadcast component of the client object.
                 */
            } catch (Exception e) {
                client.txtBroadcast.append("\ncom.ClientThread run : " + e);
            }
        } while (true);
    }

    /*
    updateUsersList() method, which is responsible for updating the list of users in the client's
     user interface.
     It takes a string (userList) as a parameter, which represents the updated user list received
        from the server.
     */
    public void updateUsersList(String userList) {

        /*
        It initializes a Vector called users to store the individual user names.
         */
        Vector<String> users = new Vector<>();

        /*
            It performs some string manipulations to remove brackets and the MyServer
            .UPDATE_USERS prefix
            from the received string.
         */
        userList = userList.replace("[", "");
        userList = userList.replace("]", "");
        userList = userList.replace(MyServer.UPDATE_USERS, "");

        /*
        It uses a StringTokenizer to tokenize the remaining string, splitting it into individual
        user names.
         */
        StringTokenizer st = new StringTokenizer(userList, ",");

        /*
        It adds each user name to the users vector.
         */
        while (st.hasMoreTokens()) {
            String temp = st.nextToken();
            users.add(temp);
        }
        /*
        Finally, it sets the data of the usersList component of the client object to the users
        vector, updating the user interface with the updated user list.
         */
        client.usersList.setListData(users);
    }
}
