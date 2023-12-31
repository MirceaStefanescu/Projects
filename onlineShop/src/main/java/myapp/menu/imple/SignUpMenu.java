package myapp.menu.imple;

import myapp.configs.ApplicationContext;
import myapp.enteties.User;
import myapp.enteties.imple.DefaultUser;
import myapp.menu.Menu;
import myapp.services.UserManagementService;
import myapp.services.imple.DefaultUserManagementService;

import java.util.Scanner;

public class SignUpMenu implements Menu {

    private final UserManagementService userManagementService;
    private final ApplicationContext context;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override public void start() {
        printMenuHeader();

        Scanner sc = new Scanner(System.in);
        System.out.print("Please, enter your first name: ");
        String firstName = sc.next();
        System.out.print("Please, enter your last name: ");
        String lastName = sc.next();
        System.out.print("Please, enter your password: ");
        String password = sc.next();
        System.out.print("Please, enter your email: ");

        sc = new Scanner(System.in);
        String email = sc.nextLine();

        // generation
        User user = new DefaultUser(firstName, lastName, password, email);

        String errorMessage = userManagementService.registerUser(user);
        if (errorMessage == null || errorMessage.isEmpty()) {
            context.setLoggedInUser(user);
            System.out.println("New user is created");
        } else {
            System.out.println(errorMessage);
        }

        context.getMainMenu().start();
    }

    @Override public void printMenuHeader() {
        System.out.println("***** SIGN UP *****");
    }
}
