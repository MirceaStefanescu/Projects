package org.enteties;

public interface User {

    String getFirstName();

    String getLastName();

    String getPassword();

    void setPassword(String newPassword);

    String getEmail();

    void setEmail(String newEmail);

    int getId();
}
