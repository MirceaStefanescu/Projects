package myapp.dao;

import myapp.dto.RoleDto;

/*
The RoleDao interface defines a contract for accessing and manipulating role data. It declares a
single method getRoleById(int id) that retrieves a RoleDto object based on a given role ID.
 */
public interface RoleDao {
    RoleDto getRoleById(int id);
}
