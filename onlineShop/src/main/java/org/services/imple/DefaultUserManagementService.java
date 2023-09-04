package org.services.imple;

import org.enteties.User;
import org.enteties.imple.DefaultUser;
import org.services.UserManagementService;
import org.storage.impl.DefaultUserStoringService;

import java.util.List;

public class DefaultUserManagementService implements UserManagementService {

    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    private static final String NO_ERROR_MESSAGE = "";
    private static final DefaultUserStoringService defaultUserStoringService;
    private static DefaultUserManagementService instance;

    static {
        defaultUserStoringService = DefaultUserStoringService.getInstance();
    }

    private DefaultUserManagementService() {
    }

    public static UserManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultUserManagementService();
        }
        return instance;
    }

    @Override
    public String registerUser(User user) {
        if (user == null) {
            return NO_ERROR_MESSAGE;
        }

        String errorMessage = checkUniqueEmail(user.getEmail());
        if (errorMessage != null && !errorMessage.isEmpty()) {
            return errorMessage;
        }

        defaultUserStoringService.saveUser(user);
        return NO_ERROR_MESSAGE;
    }

    private String checkUniqueEmail(String email) {
        List<User> users = defaultUserStoringService.loadUsers();
        if (email == null || email.isEmpty()) {
            return EMPTY_EMAIL_ERROR_MESSAGE;
        }
        for (User user : users) {
            if (user != null && user.getEmail() != null && user.getEmail()
                    .equalsIgnoreCase(email)) {
                return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
            }
        }
        return NO_ERROR_MESSAGE;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = defaultUserStoringService.loadUsers();
        DefaultUser.setCounter(users.stream().mapToInt(user -> user.getId()).max().getAsInt());
        return users;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        for (User user : defaultUserStoringService.loadUsers()) {
            if (user != null && user.getEmail().equalsIgnoreCase(userEmail)) {
                return user;
            }
        }
        return null;
    }
}
