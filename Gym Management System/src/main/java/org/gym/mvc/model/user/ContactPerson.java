package org.gym.mvc.model.user;

import lombok.Getter;

@Getter public enum ContactPerson {
    FRANK("Frank");

    private final String name;

    ContactPerson(String name) {
        this.name = name;
    }

    public static ContactPerson fromName(String name) {
        for (ContactPerson contactPerson : ContactPerson.values()) {
            if (contactPerson.getName().equals(name)) {
                return contactPerson;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + name);
    }
}
