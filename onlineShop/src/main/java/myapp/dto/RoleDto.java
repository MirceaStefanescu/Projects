package myapp.dto;

/*
    class represents a Data Transfer Object (DTO) for a user in an application. DTOs are used to
    transfer data between different layers of an application or between applications.
    it encapsulates role-related information and provides methods for accessing and modifying the
    user's attributes.
 */
public class RoleDto {

    private Integer id;
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
