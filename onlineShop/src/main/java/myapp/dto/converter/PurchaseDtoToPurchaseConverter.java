package myapp.dto.converter;

import myapp.dto.PurchaseDto;
import myapp.enteties.Purchase;
import myapp.enteties.imple.DefaultPurchase;

import java.util.ArrayList;
import java.util.List;

/*
 The PurchaseDtoToPurchaseConverter class is a converter class responsible for converting between
 PurchaseDto objects and Purchase objects. It provides methods to convert a single PurchaseDto to
 a Purchase, a single Purchase to a PurchaseDto, and a list of PurchaseDto to a list of Purchase.
 */
public class PurchaseDtoToPurchaseConverter {

    private final ProductDtoToProductConverter productConverter;
    private final UserDtoToUserConverter userConverter;

    /*
    In the instance initializer block, the class initializes the productConverter and
    userConverter fields by creating new instances of their respective converter classes.
     */ {
        productConverter = new ProductDtoToProductConverter();
        userConverter = new UserDtoToUserConverter();
    }

    /*
     the convertPurchaseDtoToPurchase method takes a PurchaseDto object as input and converts it to
     a Purchase object.
     */
    public Purchase convertPurchaseDtoToPurchase(PurchaseDto purchaseDto) {

        /*
        It creates a new instance of DefaultPurchase and sets the credit card
        number and customer ID based on the corresponding fields in the PurchaseDto.
         */
        Purchase purchase = new DefaultPurchase();
        purchase.setCreditCardNumber(purchaseDto.getUserDto().getCreditCard());
        purchase.setCustomerId(purchaseDto.getUserDto().getId());

        /*
        It also converts the list of product DTOs in the PurchaseDto to a list of Product objects
        using the productConverter and sets it in the Purchase object.
         */
        purchase.setProducts(
                productConverter.convertProductDtosToProducts(purchaseDto.getProductDtos()));

        return purchase;
    }

    /*
    The convertPurchaseToPurchaseDto method takes a Purchase object as input and converts it to a
     PurchaseDto object.
     */
    public PurchaseDto convertPurchaseToPurchaseDto(Purchase purchase) {

        /*
        It creates a new instance of PurchaseDto and sets the list of product DTOs based on the
        converted list of Product objects in the Purchase. It also converts the customer ID in
        the Purchase to a UserDto object using the userConverter and sets it in the PurchaseDto.
         */
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setProductDtos(
                productConverter.convertProductsToProductDtos(purchase.getProducts()));
        purchaseDto.setUserDto(
                userConverter.convertUserIdToUserDtoWithOnlyId(purchase.getCustomerId()));

        return purchaseDto;
    }

    /*
    The convertPurchaseDtosToPurchases method takes a list of PurchaseDto objects as input and
    converts each PurchaseDto to a Purchase object using the convertPurchaseDtoToPurchase method.
    It returns a list of converted Purchase objects.
     */
    public List<Purchase> convertPurchaseDtosToPurchases(List<PurchaseDto> purchasesDtos) {
        List<Purchase> purchases = new ArrayList<>();
        if (purchasesDtos != null) {
            for (PurchaseDto purchaseDto : purchasesDtos) {
                purchases.add(convertPurchaseDtoToPurchase(purchaseDto));
            }
        }
        return purchases;
    }
}
