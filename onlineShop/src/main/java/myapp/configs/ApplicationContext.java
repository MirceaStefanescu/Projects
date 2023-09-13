package myapp.configs;

import myapp.enteties.Cart;
import myapp.enteties.User;
import myapp.enteties.imple.DefaultCart;
import myapp.menu.Menu;

/**
 * serves as a central container for managing application-level configurations and data. It follows
 * the Singleton design pattern, ensuring that only one instance of ApplicationContext can exist.
 */
public class ApplicationContext {

    private static ApplicationContext instance;

    //stores the currently logged-in user.
    private User loggedInUser;

    //holds an instance of the Menu class, representing the main menu of the application.
    private Menu mainMenu;

    //represents the shopping cart for the current user session.
    private Cart sessionCart;

    /*
    The constructor of ApplicationContext is private, preventing direct instantiation of the
    class from outside.
     */
    private ApplicationContext() {
    }

    /*
    The class provides a getInstance method that returns the singleton
    instance of ApplicationContext.
     */
    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    /*
    when a new user is set as the logged-in user, the sessionCart is cleared to avoid conflicts
    with the previous user's cart.
     */
    public void setLoggedInUser(User user) {
        if (this.sessionCart != null) {
            this.sessionCart.clear(); // we have to clear session cart when new user is logged in
        }
        this.loggedInUser = user;
    }

    public Menu getMainMenu() {
        return this.mainMenu;
    }

    public void setMainMenu(Menu menu) {
        this.mainMenu = menu;
    }

    /*
     method ensures that a DefaultCart instance is created if sessionCart is null, providing a
     default cart implementation.
     */
    public Cart getSessionCart() {
        if (this.sessionCart == null) {
            this.sessionCart = new DefaultCart();
        }
        return this.sessionCart;
    }
}
