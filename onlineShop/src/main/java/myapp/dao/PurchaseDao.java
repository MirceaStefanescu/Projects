package myapp.dao;

import myapp.dto.PurchaseDto;

import java.util.List;

/*
The PurchaseDao interface defines a contract for accessing and manipulating purchase data
 */
public interface PurchaseDao {

    /*
    This method is responsible for saving a PurchaseDto object, representing a purchase order, to
     a data source. The implementation of this method should handle the necessary steps to
     persist the purchase order.
     */
    void savePurchase(PurchaseDto order);

    /*
    This method takes an integer userId as input and returns a list of PurchaseDto objects. The
    implementation of this method should retrieve all the purchase orders associated with the
    given user ID from a data source and return them as a list.
     */
    List<PurchaseDto> getPurchasesByUserId(int userId);

    /*
    This method returns a list of PurchaseDto objects, representing multiple purchase orders. The
     implementation of this method should retrieve all the purchase orders from a data source and
      return them as a list.
     */
    List<PurchaseDto> getPurchases();
}
