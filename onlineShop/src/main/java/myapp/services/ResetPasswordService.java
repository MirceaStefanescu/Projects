package myapp.services;

import myapp.enteties.User;

public interface ResetPasswordService {
    void resetPasswordForUser(User user);
}
