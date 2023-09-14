package myapp.storage.impl;

import myapp.enteties.User;
import myapp.enteties.imple.DefaultUser;
import myapp.storage.UserStoringService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultUserStoringService implements UserStoringService {
    private static final String USER_INFO_STORAGE = "src/main/resources/users.csv";

    private static final int USER_EMAIL_INDEX = 4;
    private static final int USER_PASSWORD_INDEX = 3;
    private static final int USER_LASTNAME_INDEX = 2;
    private static final int USER_FIRSTNAME_INDEX = 1;
    private static final int USER_ID_INDEX = 0;

    private static DefaultUserStoringService instance;

    public static DefaultUserStoringService getInstance() {
        if (instance == null) {
            instance = new DefaultUserStoringService();
        }
        return instance;
    }

    @Override public void saveUser(User user) {
        try {
            Files.writeString(Paths.get(USER_INFO_STORAGE),
                              System.lineSeparator() + convertToStorableString(user),
                              StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertToStorableString(User user) {
        return user.getId() + "," + user.getFirstName() + "," + user.getLastName() + "," +
               user.getPassword() + "," + user.getEmail();
    }

    @Override public List<User> loadUsers() {

        try (var stream = Files.lines(Paths.get(USER_INFO_STORAGE))) {
            return stream.filter(Objects::nonNull).filter(line -> !line.isEmpty()).map(line -> {
                String[] userElements = line.split(",");
                return new DefaultUser(Integer.valueOf(userElements[USER_ID_INDEX]),
                                       userElements[USER_FIRSTNAME_INDEX],
                                       userElements[USER_LASTNAME_INDEX],
                                       userElements[USER_PASSWORD_INDEX],
                                       userElements[USER_EMAIL_INDEX]);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
}
