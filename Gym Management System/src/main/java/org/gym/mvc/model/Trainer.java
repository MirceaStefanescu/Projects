package org.gym.mvc.model;

import java.util.UUID;

public class Trainer implements User {
    private final String id;
    private final String username;
    private final String password;
    private final String specialization;
    private final int yearsOfExperience;

    public Trainer(String username, String password, String specialization, int yearsOfExperience) {
        this.id = generateUniqueID();
        this.username = username;
        this.password = password;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    private String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
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
