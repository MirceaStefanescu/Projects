package org.enteties;

import java.io.Serializable;
import java.util.List;

public interface Order extends Serializable {

    boolean isCreditCardNumberValid(String userInput);

    void setCreditCardNumber(String userInput);

    void setProducts(List<Product> products);

    int getCustomerId();

    void setCustomerId(int customerId);
}
