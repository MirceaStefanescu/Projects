package org.menu.imple;

import org.configs.ApplicationContext;
import org.enteties.Order;
import org.enteties.imple.DefaultOrder;
import org.menu.Menu;
import org.services.OrderManagementService;
import org.services.imple.DefaultOrderManagementService;

import java.util.ResourceBundle;
import java.util.Scanner;

public class CheckoutMenu implements Menu {

    private final ApplicationContext context;
    private final OrderManagementService orderManagementService;
    private final ResourceBundle rb;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    }

    @Override
    public void start() {
        while (true) {
            printMenuHeader();
            Scanner sc = new Scanner(System.in);
            String userInput = sc.next();

            if (!createOrder(userInput)) {
                continue;
            }
            context.getSessionCart().clear();
            break;
        }

        System.out.println(rb.getString("thank.you.msg"));
        new MainMenu().start();

    }

    private boolean createOrder(String creditCardNumber) {
        Order order = new DefaultOrder();
        if (!order.isCreditCardNumberValid(creditCardNumber)) {
            return false;
        }

        order.setCreditCardNumber(creditCardNumber);
        order.setProducts(context.getSessionCart().getProducts());
        order.setCustomerId(context.getLoggedInUser().getId());
        orderManagementService.addOrder(order);
        return true;
    }

    @Override
    public void printMenuHeader() {
        System.out.println(rb.getString("checkout.menu.header"));
        System.out.print(rb.getString("enter.credit.card.number.cta"));
    }
}
