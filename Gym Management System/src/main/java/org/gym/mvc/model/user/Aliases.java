package org.gym.mvc.model.user;

public enum Aliases {
    ONE("None"), VISITOR("Visitor"), GUEST("Guest"), USER("User"), PAID_USER("Paid User"), OPERATOR(
            "Operator"), ADMIN("Admin");

    private final String alias;

    Aliases(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
