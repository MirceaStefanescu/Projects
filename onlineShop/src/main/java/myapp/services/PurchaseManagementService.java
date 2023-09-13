package myapp.services;

import myapp.enteties.Purchase;

import java.util.List;

public interface PurchaseManagementService {

    void addPurchase(Purchase purchase);

    List<Purchase> getPurchasesByUserId(int userId);

    List<Purchase> getPurchases();
}
