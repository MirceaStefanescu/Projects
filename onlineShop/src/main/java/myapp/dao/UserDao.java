package myapp.dao;

import myapp.dto.UserDto;

import java.util.List;

/*
The UserDao interface represents a Data Access Object (DAO) responsible for performing operations
 related to user data. It declares several methods for manipulating user data.
 */
public interface UserDao {

    /*
    The saveUser method is used to save a UserDto object. It takes a UserDto parameter user and
    returns a boolean value indicating whether the save operation was successful or not.
     */
    boolean saveUser(UserDto user);

    /*
    The getUsers method retrieves a list of all UserDto objects. It returns a List<UserDto>
    containing all the user data.
     */
    List<UserDto> getUsers();

    /*
    The getUserByEmail method retrieves a UserDto object based on the provided email. It takes a
    String parameter userEmail representing the email address of the user and returns the
    corresponding UserDto object.
     */
    UserDto getUserByEmail(String userEmail);

    /*
    The getUserById method retrieves a UserDto object based on the provided user ID. It takes an
    int parameter id representing the user ID and returns the corresponding UserDto object.
     */
    UserDto getUserById(int id);
}
