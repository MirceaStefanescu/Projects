package org.menu.imple;

import org.configs.ApplicationContext;
import org.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;

public class ChangeEmailMenu implements Menu {

    private final ApplicationContext context;
    private final ResourceBundle rb;

    {
        context = ApplicationContext.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        context.getLoggedInUser().setEmail(userInput);
        System.out.println(rb.getString("mail.changed.msg"));
        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("change.language.header"));
        System.out.print(rb.getString("enter.new.email.cta"));
    }
}
