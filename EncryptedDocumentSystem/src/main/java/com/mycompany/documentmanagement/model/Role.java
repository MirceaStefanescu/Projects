package com.mycompany.documentmanagement.model;

import lombok.Getter;

@Getter public enum Role {
    ADMIN("Admin", "Administrator role"),
    USER("User", "User role"),
    GUEST("Guest", "Guest role"),
    MANAGER("Manager", "Manager role"),
    SUPERVISOR("Supervisor", "Supervisor role"),
    ANALYST("Analyst", "Analyst role");
    private final String name;
    private final String description;

    Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}