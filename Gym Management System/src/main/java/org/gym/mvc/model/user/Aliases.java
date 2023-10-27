package org.gym.mvc.model.user;

import lombok.Getter;

@Getter public enum Aliases {
    ONE("None"), VISITOR("Visitor"), GUEST("Guest"), USER("User"), PAID_USER("Paid User"), OPERATOR(
            "Operator"), ADMIN("Admin");

    private final String alias;

    Aliases(String alias) {
        this.alias = alias;
    }

    public static Aliases fromAlias(String alias) {
        for (Aliases aliases : Aliases.values()) {
            if (aliases.getAlias().equals(alias)) {
                return aliases;
            }
        }
        throw new IllegalArgumentException("Invalid alias: " + alias);
    }
}
