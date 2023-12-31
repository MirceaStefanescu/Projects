package org.enteties.imple;

import org.annotations.Validate;
import org.enteties.User;

public class DefaultUser implements User {

    private static int userCounter = 0;

    private int id;

    @Validate(pattern = "[a-zA-Z]+")
    private String firstName;

    @Validate(pattern = "[a-zA-Z]+")
    private String lastName;

    private String password;

    @Validate(pattern = ".+@.+")
    private String email;

    {
        id = ++userCounter;
    }

    public DefaultUser() {
    }

    public DefaultUser(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public DefaultUser(int id, String firstName, String lastName, String password, String email) {
        this.id = id;
        userCounter--; // to keep sequantial id
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public static void setCounter(int updatedCount) {
        userCounter = updatedCount;
    }

    static void clearState() {
        userCounter = 0;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        if (password == null) {
            return;
        }
        this.password = password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String newEmail) {
        if (newEmail == null) {
            return;
        }
        this.email = newEmail;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\t\t" + "First Name: " + this.getFirstName() + "\t\t" + "Last Name: " + this.getLastName() + "\t\t" + "Email: " + this.getEmail();
    }

    @Override
    public int getId() {
        return this.id;
    }
}
