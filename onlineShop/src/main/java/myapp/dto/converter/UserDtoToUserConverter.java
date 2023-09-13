package myapp.dto.converter;

import myapp.dto.UserDto;
import myapp.enteties.User;
import myapp.enteties.imple.DefaultUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
The UserDtoToUserConverter class is a converter class responsible for converting between UserDto
and User objects.
 */
public class UserDtoToUserConverter {

    //is used to convert role names between RoleDto and Role objects.
    private final RoleDtoToRoleConverter roleConverter;

    {
        roleConverter = new RoleDtoToRoleConverter();
    }

    /*
    The convertUserIdToUserDtoWithOnlyId method takes an int parameter customerId and creates a
    new instance of UserDto. It sets the customerId parameter as the id of the UserDto object and
     returns it.
     */
    public UserDto convertUserIdToUserDtoWithOnlyId(int customerId) {
        UserDto userDto = new UserDto();
        userDto.setId(customerId);
        return userDto;
    }

    /*
    The convertUserDtoToUser method converts a UserDto object to a User object.
     */
    public User convertUserDtoToUser(UserDto userDto) {
        /*
        It first checks if the input userDto is null and returns null in that case.
         */
        if (userDto == null) {
            return null;
        }

        /*
        Otherwise, it creates a new instance of User (specifically, a DefaultUser). It sets
        various properties of the User object based on the corresponding properties of the
        UserDto object, such as email, password, id, firstName, lastName, roleName, money, and
        creditCard.
         */
        User user = new DefaultUser();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        if (userDto.getRoleDto() != null)
            user.setRoleName(userDto.getRoleDto().getRoleName());
        user.setMoney(userDto.getMoney().doubleValue());
        user.setCreditCard(userDto.getCreditCard());

        //It then returns the created User object.
        return user;
    }

    /*
    The convertUserToUserDto method converts a User object to a UserDto object.
     */
    public UserDto convertUserToUserDto(User user) {

        /*
        It creates a new instance of UserDto and sets its properties based on the corresponding
        properties of the input user object. It also converts the roleName property of the User
        object to a RoleDto object using the roleConverter instance.
         */
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setRoleDto(
                roleConverter.convertRoleNameToRoleDtoWithOnlyRoleName(user.getRoleName()));
        userDto.setMoney(BigDecimal.valueOf(user.getMoney()));
        userDto.setCreditCard(user.getCreditCard());

        //Finally, it returns the created UserDto object.
        return userDto;
    }

    /*
    The convertUserDtosToUsers method converts a list of UserDto objects to a list of User
    objects.
     */
    public List<User> convertUserDtosToUsers(List<UserDto> userDtos) {
        List<User> users = new ArrayList<>();

        /*
        It iterates over each UserDto in the input list, converts it to a User object using the
        convertUserDtoToUser method, and adds the converted User object to a new list.
         */
        for (UserDto userDto : userDtos) {
            users.add(convertUserDtoToUser(userDto));
        }

        // The method then returns the list of User objects.
        return users;
    }
}
