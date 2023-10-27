package org.gym.mvc.model.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gym.mvc.model.membership.Membership;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter @Setter @ToString(callSuper = true) @EqualsAndHashCode(callSuper = true)
public class AnonymousUser extends User {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnonymousUser.class);

    public AnonymousUser(Aliases[] aliases, ActorType actorType, ContactPerson contactPerson,
                         String contactDetails, Profile profile, Membership membership) {
        super(aliases, actorType, contactPerson, contactDetails, profile, membership);
    }

    @Override public void performAction() {
        LOGGER.info("Performing some action in AnonymousUser class");
    }
}
