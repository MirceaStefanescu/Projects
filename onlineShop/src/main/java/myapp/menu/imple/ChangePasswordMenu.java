package myapp.menu.imple;

import myapp.configs.ApplicationContext;
import myapp.menu.Menu;

import java.util.Scanner;

/*
the ChangePasswordMenu class represents a specific menu option for changing the password
associated with the currently logged-in user. It prompts the user to enter a new password,
updates the user's password, and displays a success message before returning to the main menu.
 */
public class ChangePasswordMenu implements Menu {

    /*
    The class has a private member variable called context of type ApplicationContext. It is
    marked as final, meaning its value cannot be changed once initialized.
     */
    private final ApplicationContext context;

    /*
    The class has an instance initializer block (denoted by {}) that initializes the context
    variable by calling the static method ApplicationContext.getInstance(). This ensures that the
     context variable is assigned an instance of the ApplicationContext class.
     */ {
        context = ApplicationContext.getInstance();
    }

    @Override public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        context.getLoggedInUser().setPassword(userInput);
        System.out.println("Your password has been successfully changed");
        new MainMenu().start();
    }

    @Override public void printMenuHeader() {
        System.out.println("***** CHANGE PASSWORD *****");
        System.out.print("Enter new password: ");
    }
}
