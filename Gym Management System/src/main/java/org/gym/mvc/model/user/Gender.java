package org.gym.mvc.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor public enum Gender {
    MALE("Male"), FEMALE("Female");

    private final String label;
}
