package myapp;

import myapp.menu.Menu;
import myapp.menu.imple.MainMenu;

/**
 * This Main class is a Java class with a main method, which serves as the entry point of the
 * program.
 */
public class Main {

    public static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {

        /**
         * an instance of the Menu class is created and assigned to the variable mainMenu. The
         * MainMenu class is a subclass of Menu, and it starts by invoking the start method of the
         * mainMenu instance.
         */
        Menu mainMenu = new MainMenu();
        mainMenu.start();
    }
}
