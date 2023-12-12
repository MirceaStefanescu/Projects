package com.mycompany.documentmanagement.model;

import lombok.Getter;

@Getter
public enum Permission {
    READ("Read", "Allows read access"),
    WRITE("Write", "Allows write access"),
    DELETE("Delete", "Allows delete access"),
    UPDATE("Update", "Allows update access"),
    CREATE("Create", "Allows create access"),
    ADMIN("Admin", "Allows administrative access"),
    EXPORT("Export", "Allows export access");
    private final String name;
    private final String description;

    Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }
}