package org.gym.mvc.model;

import java.util.UUID;

public class Administrator implements User {
    private final String id;
    private final String username;
    private final String password;
    private final String department;
    private final String role;

    public Administrator(String username, String password, String department, String role) {
        this.id = generateUniqueID();
        this.username = username;
        this.password = password;
        this.department = department;
        this.role = role;
    }

    private String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }

    @Override public String getUsername() {
        return username;
    }

    @Override public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}
