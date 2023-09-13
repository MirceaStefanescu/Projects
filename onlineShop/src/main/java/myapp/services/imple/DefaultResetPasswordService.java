package myapp.services.imple;

import myapp.enteties.User;
import myapp.services.ResetPasswordService;
import myapp.utils.mail.MailSender;

public class DefaultResetPasswordService implements ResetPasswordService {

    private final MailSender mailSender;

    {
        mailSender = DefaultMailSender.getInstance();
    }

    @Override public void resetPasswordForUser(User user) {
        mailSender.sendEmail(user.getEmail(),
                             "Please, use this password to login: " + user.getPassword());
    }
}
