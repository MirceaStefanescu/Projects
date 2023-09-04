package org.menu.imple;

import org.enteties.User;
import org.menu.Menu;
import org.services.ResetPasswordService;
import org.services.UserManagementService;
import org.services.imple.DefaultResetPasswordService;
import org.services.imple.DefaultUserManagementService;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ResetPasswordMenu implements Menu {

    private final ResetPasswordService resetPasswordService;
    private final UserManagementService userManagementService;
    private final ResourceBundle rb;

    {
        resetPasswordService = new DefaultResetPasswordService();
        userManagementService = DefaultUserManagementService.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        System.out.println(rb.getString("pass.sent.to.email"));
        CompletableFuture.runAsync(() -> {
            User user = userManagementService.getUserByEmail(userInput);
            resetPasswordService.resetPasswordForUser(user);
        });
        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("reset.pass.header"));
        System.out.print(rb.getString("enter.your.email.msg"));
    }
}
