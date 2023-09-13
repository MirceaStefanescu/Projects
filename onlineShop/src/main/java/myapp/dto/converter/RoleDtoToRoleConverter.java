package myapp.dto.converter;

import myapp.dto.RoleDto;

/*
The RoleDtoToRoleConverter class is a converter class responsible for converting a role name
(String) to a RoleDto object.
 */
public class RoleDtoToRoleConverter {

    /*
    the convertRoleNameToRoleDtoWithOnlyRoleName method takes a roleName as input and creates a
    new instance of RoleDto. It sets the roleName parameter in the RoleDto object using the
    setRoleName method. Finally, it returns the created RoleDto object.
     */
    public RoleDto convertRoleNameToRoleDtoWithOnlyRoleName(String roleName) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleName);
        return roleDto;
    }
}
