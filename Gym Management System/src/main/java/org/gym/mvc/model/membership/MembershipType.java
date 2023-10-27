package org.gym.mvc.model.membership;

import lombok.Getter;

@Getter public enum MembershipType {
    BASIC("Basic Membership"), PREMIUM("Premium Membership"), ELITE("Elite Membership");

    private final String description;

    MembershipType(String description) {
        this.description = description;
    }

}
