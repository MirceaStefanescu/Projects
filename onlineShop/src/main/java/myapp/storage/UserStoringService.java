package myapp.storage;

import myapp.enteties.User;

import java.util.List;

public interface UserStoringService {

    void saveUser(User user);

    List<User> loadUsers();
}
