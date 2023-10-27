package org.gym.mvc.model.user;

import org.gym.mvc.model.membership.Membership;

public class RegisteredUser extends User {
    public RegisteredUser(Aliases[] aliases, ActorType actorType, ContactPerson contactPerson,
                          String contactDetails, Profile profile, Membership membership) {
        super(aliases, actorType, contactPerson, contactDetails, profile, membership);
    }

    @Override public void performAction() {
        System.out.println("Performing action for registered user...");
    }

    @Override public boolean canBook() {
        return true;
    }
}
