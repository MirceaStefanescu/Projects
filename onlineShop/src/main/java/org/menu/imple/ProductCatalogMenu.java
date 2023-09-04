package org.menu.imple;

import org.configs.ApplicationContext;
import org.enteties.Cart;
import org.enteties.Product;
import org.menu.Menu;
import org.services.ProductManagementService;
import org.services.imple.DefaultProductManagementService;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

    private static final String CHECKOUT_COMMAND = "checkout";
    private final ApplicationContext context;
    private final ProductManagementService productManagementService;
    private final ResourceBundle rb;

    {
        context = ApplicationContext.getInstance();
        productManagementService = DefaultProductManagementService.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        Menu menuToNavigate = null;
        while (true) {
            printMenuHeader();
            printProductsToConsole();

            String userInput = readUserInput();

            if (context.getLoggedInUser() == null) {
                menuToNavigate = new MainMenu();
                System.out.println(rb.getString("not.logged.in.msg"));
                break;
            }

            if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
                menuToNavigate = new MainMenu();
                break;
            }

            if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
                Cart sessionCart = context.getSessionCart();
                if (sessionCart == null || sessionCart.isEmpty()) {
                    System.out.println(rb.getString("empty.cart.err.msg"));
                } else {
                    menuToNavigate = new CheckoutMenu();
                    break;
                }
            } else {
                Product productToAddToCart = fetchProduct(userInput);

                if (productToAddToCart == null) {
                    System.out.println(rb.getString("enter.product.id"));
                    continue;
                }

                processAddToCart(productToAddToCart);
            }
        }

        menuToNavigate.start();
    }

    private String readUserInput() {
        System.out.print(rb.getString("proceed.to.checkout"));
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        return userInput;
    }

    private void printProductsToConsole() {
        List<Product> products = productManagementService.getProducts();
        if (products != null) {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private Product fetchProduct(String userInput) {
        int productIdToAddToCart = Integer.parseInt(userInput);
        Product productToAddToCart = productManagementService.getProductById(productIdToAddToCart);
        return productToAddToCart;
    }

    private void processAddToCart(Product productToAddToCart) {
        context.getSessionCart().addProduct(productToAddToCart);
        System.out.printf(rb.getString("product.added.to.cart"),
                productToAddToCart.getProductName());
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("product.catalog.header"));
        System.out.println(rb.getString("catalog.cta"));
    }
}
