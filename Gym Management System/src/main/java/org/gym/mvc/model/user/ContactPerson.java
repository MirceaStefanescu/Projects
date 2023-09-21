package org.gym.mvc.model.user;

public enum ContactPerson {
    FRANK("Frank");

    private final String name;

    ContactPerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
