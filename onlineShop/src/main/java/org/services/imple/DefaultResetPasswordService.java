package org.services.imple;

import org.enteties.User;
import org.services.ResetPasswordService;
import org.utils.mail.MailSender;

public class DefaultResetPasswordService implements ResetPasswordService {

    private final MailSender mailSender;

    {
        mailSender = DefaultMailSender.getInstance();
    }

    @Override
    public void resetPasswordForUser(User user) {
        mailSender.sendEmail(user.getEmail(),
                "Please, use this password to login: " + user.getPassword());
    }
}
