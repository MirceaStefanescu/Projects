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

    /*
    The class has a private member variable called context of type ApplicationContext. It is
    marked as final, meaning its value cannot be changed once initialized.
     */
    private final ApplicationContext context;

    /*
    The class has an instance initializer block (denoted by {}) that initializes the context
    variable by calling the static method ApplicationContext.getInstance(). This ensures that the
     context variable is assigned an instance of the ApplicationContext class.*/ {
        context = ApplicationContext.getInstance();
    }

    /*
    The class overrides the start() method from the Menu interface. Inside the start() method,
     */
    @Override public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        context.getLoggedInUser().setEmail(userInput);
        System.out.println("Your email has been successfully changed");
        new MainMenu().start();
    }

    /*
    The class also overrides the printMenuHeader() method from the Menu interface. Inside the
    printMenuHeader() method, it simply prints a predefined menu header and prompts the user to
    enter a new email.
     */
    @Override public void printMenuHeader() {
        System.out.println("***** CHANGE EMAIL *****");
        System.out.print("Enter new email: ");
    }

}