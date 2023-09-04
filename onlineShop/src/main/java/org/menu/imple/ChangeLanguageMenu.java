package org.menu.imple;

import org.menu.Menu;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ChangeLanguageMenu implements Menu {

    private static final int ENGLISH_ID = 1;
    private static final int RUSSIAN_ID = 2;
    private final ResourceBundle rb;

    {
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);
        int languageId = sc.nextInt();
        switch (languageId) {
            case ENGLISH_ID -> Locale.setDefault(new Locale("en"));
            case RUSSIAN_ID -> Locale.setDefault(new Locale("ru"));
        }

        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("change.language.header"));
        System.out.print(rb.getString("select.language.cta"));
    }
}
