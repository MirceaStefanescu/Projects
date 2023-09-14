package myapp.menu.imple;

import myapp.configs.ApplicationContext;
import myapp.menu.Menu;

import java.util.Scanner;

/*
the ChangeEmailMenu class represents a specific menu option for changing the email associated
with the currently logged-in user. It prompts the user to enter a new email, updates the user's
email, and displays a success message before returning to the main menu.
 */
public class ChangeEmailMenu implements Menu {

    private final ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        context.getLoggedInUser().setEmail(userInput);
        System.out.println("Your email has been successfully changed");
        new MainMenu().start();
    }

    @Override public void printMenuHeader() {
        System.out.println("***** CHANGE EMAIL *****");
        System.out.print("Enter new email: ");
    }

}