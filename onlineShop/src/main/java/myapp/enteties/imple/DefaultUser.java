package myapp.enteties.imple;

import myapp.annotations.Validate;
import myapp.enteties.User;

//this class represents a default implementation of a user with basic user information and some
// additional functionality.
public class DefaultUser implements User {

    /*
    The class has a private static variable userCounter to keep track of the number of users
    created. This variable is incremented for each new user.
     */
    private static int userCounter = 0;

    //for storing user information
    private int id;

    /*
    With the @Validate(pattern = "[a-zA-Z]+") annotation, it ensures that the value assigned to
    firstName contains only alphabetic characters.
     */
    @Validate(pattern = "[a-zA-Z]+") private String firstName;

    /*
    Similarly, the @Validate(pattern = "[a-zA-Z]+") annotation ensures that the value assigned to
     lastName contains only alphabetic characters.
     */
    @Validate(pattern = "[a-zA-Z]+") private String lastName;

    //for storing user information
    private String password;

    /*
    The @Validate(pattern = ".+@.+") annotation is used to validate the email field. It specifies
     a regular expression pattern that the value assigned to email must match.
     The pattern ".+@.+" represents a basic regular expression for validating an email address.
     It verifies that there is at least one character before and after the @ symbol, helping to
     ensure that the assigned value resembles a valid email address.
     @ matches the literal @ symbol.
     */
    @Validate(pattern = ".+@.+") private String email;

    //for storing user information
    private String roleName;

    //for storing user information
    private double money;

    //for storing user information
    private String creditCard;

    /*
    The block of code inside the curly braces is an instance initializer, which is executed
    whenever an object of the class is created. In this case, it assigns a unique id to each user
     by incrementing the userCounter.
     */ {
        id = ++userCounter;
    }

    /*
    The class provides various constructors for creating user objects with different sets of
    parameters. These constructors initialize the instance variables with the provided values.
     */
    public DefaultUser() {
    }

    public DefaultUser(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public DefaultUser(int id, String firstName, String lastName, String password, String email) {
        this.id = id;
        userCounter--; // to keep sequential id
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public DefaultUser(String firstName,
                       String lastName,
                       String password,
                       String email,
                       String creditCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.creditCard = creditCard;
    }

    public static void setCounter(int updatedCount) {
        userCounter = updatedCount;
    }

    @Override public String getFirstName() {
        return this.firstName;
    }

    @Override public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override public String getLastName() {
        return this.lastName;
    }

    @Override public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override public String getPassword() {
        return this.password;
    }

    @Override public void setPassword(String password) {
        if (password == null) {
            return;
        }
        this.password = password;
    }

    @Override public String getEmail() {
        return this.email;
    }

    @Override public void setEmail(String newEmail) {
        if (newEmail == null) {
            return;
        }
        this.email = newEmail;
    }

    /*
    The toString() method is overridden to return a string representation of the user object,
    including the id, first name, last name, and email.
     */
    @Override public String toString() {
        return "ID: " + this.getId() + "\t\t" + "First Name: " + this.getFirstName() + "\t\t" +
               "Last Name: " + this.getLastName() + "\t\t" + "Email: " + this.getEmail();
    }

    @Override public int getId() {
        return this.id;
    }

    @Override public void setId(int id) {
        this.id = id;
    }

    /*
    this method is used to reset or clear the state of the class. It could be helpful in scenarios
    where you want to start with a clean slate or remove any existing data or settings.
     */
    void clearState() {
        userCounter = 0;
    }

    @Override public String getRoleName() {
        return this.roleName;
    }

    @Override public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
