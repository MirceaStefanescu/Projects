package org.gym.mvc.model.user;

public enum Gender {
    MALE("Male"), FEMALE("Female");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
