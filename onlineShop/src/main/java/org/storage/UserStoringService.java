package org.storage;

import org.enteties.User;

import java.util.List;

public interface UserStoringService {

    void saveUser(User user);

    List<User> loadUsers();
}
