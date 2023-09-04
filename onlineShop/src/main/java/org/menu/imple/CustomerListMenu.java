package org.menu.imple;

import org.configs.ApplicationContext;
import org.enteties.User;
import org.menu.Menu;
import org.services.UserManagementService;
import org.services.imple.DefaultUserManagementService;

import java.util.List;
import java.util.ResourceBundle;

public class CustomerListMenu implements Menu {

    private final ApplicationContext context;
    private final UserManagementService userManagementService;
    private final ResourceBundle rb;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        printMenuHeader();
        List<User> users = userManagementService.getUsers();

        if (users == null || users.size() == 0) {
            System.out.println(rb.getString("no.users.msg"));
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("customer.list.header"));
    }

}
