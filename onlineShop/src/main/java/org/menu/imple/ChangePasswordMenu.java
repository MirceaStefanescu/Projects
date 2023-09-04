package org.menu.imple;

import org.configs.ApplicationContext;
import org.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;

public class ChangePasswordMenu implements Menu {

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
        context.getLoggedInUser().setPassword(userInput);
        System.out.println(rb.getString("change.password.msg"));
        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("change.password.header"));
        System.out.print(rb.getString("enter.new.pass.cta"));
    }
}
