package myapp.enteties;

//The User interface represents a contract for a user object.
public interface User {

    /*
    This method returns a string representing the first name of the user.
     */
    String getFirstName();

    /*
    This method takes a string firstName as input and sets it as the first name of the user.
     */
    void setFirstName(String firstName);

    //This method returns a string representing the last name of the user.
    String getLastName();

    /*
    This method takes a string lastName as input and sets it as the last name of the user.
     */
    void setLastName(String lastName);

    /*
    This method returns a string representing the password of the user.
     */
    String getPassword();

    /*
    This method takes a string newPassword as input and sets it as the password of the user.
     */
    void setPassword(String newPassword);

    /*
    This method returns a string representing the email address of the user.
     */
    String getEmail();

    /*
     This method takes a string newEmail as input and sets it as the email address of the user.
     */
    void setEmail(String newEmail);

    //This method returns an integer representing the ID of the user.
    int getId();

    //This method takes an integer id as input and sets it as the ID of the user.
    void setId(int id);

    /*
    This method returns a string representing the role name of the user.
     */
    String getRoleName();

    //This method takes a string roleName as input and sets it as the role name of the user.
    void setRoleName(String roleName);

    //This method returns a double representing the amount of money the user has.
    double getMoney();

    //This method takes a double money as input and sets it as the amount of money the user has.
    void setMoney(double money);

    //This method returns a string representing the credit card number associated with the user.
    String getCreditCard();

    //This method takes a string creditCard as input and sets it as the credit card number
    // associated with the user.
    void setCreditCard(String creditCard);
}
