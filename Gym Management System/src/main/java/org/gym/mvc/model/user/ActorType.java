package org.gym.mvc.model.user;

public enum ActorType {
    ACTIVE_PERSON("Active Person"), PASSIVE_EXTERNAL("Passive External");

    private final String value;

    ActorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
