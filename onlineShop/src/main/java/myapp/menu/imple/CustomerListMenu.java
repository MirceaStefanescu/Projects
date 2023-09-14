package myapp.menu.imple;

import myapp.configs.ApplicationContext;
import myapp.enteties.User;
import myapp.menu.Menu;
import myapp.services.UserManagementService;
import myapp.services.imple.DefaultUserManagementService;

import java.util.List;

public class CustomerListMenu implements Menu {

    private final ApplicationContext context;
    private final UserManagementService userManagementService;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override public void start() {
        printMenuHeader();
        List<User> users = userManagementService.getUsers();

        if (users == null || users.size() <= 1) {
            System.out.println("Unfortunately, there are no additional customers.");
        } else {
            for (int i = 1; i < users.size(); i++) {
                User user = users.get(i);
                System.out.println(user);
            }
        }
        context.getMainMenu().start();
    }

    @Override public void printMenuHeader() {
        System.out.println("***** USERS *****");
    }

}
