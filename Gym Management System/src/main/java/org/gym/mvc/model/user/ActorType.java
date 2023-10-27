package org.gym.mvc.model.user;

import lombok.Getter;

@Getter public enum ActorType {
    ACTIVE_PERSON("Active Person"), PASSIVE_EXTERNAL("Passive External");

    private final String value;

    ActorType(String value) {
        this.value = value;
    }

    public static ActorType fromValue(String value) {
        for (ActorType actorType : ActorType.values()) {
            if (actorType.getValue().equals(value)) {
                return actorType;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

}
