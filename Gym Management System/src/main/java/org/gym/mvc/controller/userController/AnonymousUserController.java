package org.gym.mvc.controller.userController;

import org.gym.mvc.model.user.AnonymousUser;
import org.gym.persistance.dao.AnonymousUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController public class AnonymousUserController {

    private final AnonymousUserDAO anonymousUserDAO;

    @Autowired public AnonymousUserController(AnonymousUserDAO anonymousUserDAO) {
        this.anonymousUserDAO = anonymousUserDAO;
    }

    @PostMapping("/submitFormAnonymousUser")
    public void submitFormAnonymousUser(@RequestBody AnonymousUser anonymousUser) {
        // Validate data
        validateData(anonymousUser);

        // Perform the action for AnonymousUser
        anonymousUser.performAction();

        // Save the anonymous user using the DAO
        AnonymousUserDAO.saveAnonymousUser(anonymousUser);
    }

    private void validateData(AnonymousUser anonymousUser) {
        // Validate aliases
        if (anonymousUser.getAliases() == null || anonymousUser.getAliases().length == 0) {
            throw new IllegalArgumentException("Aliases cannot be null or empty.");
        }

        // Validate actorType
        if (anonymousUser.getActorType() == null) {
            throw new IllegalArgumentException("ActorType cannot be null.");
        }

        // Validate contactPerson
        if (anonymousUser.getContactPerson() == null) {
            throw new IllegalArgumentException("ContactPerson cannot be null.");
        }

        // Validate contactDetails
        if (anonymousUser.getContactDetails() == null ||
            anonymousUser.getContactDetails().trim().isEmpty()) {
            throw new IllegalArgumentException("ContactDetails cannot be null or empty.");
        }

        // Validate profile
        if (anonymousUser.getProfile() == null) {
            throw new IllegalArgumentException("Profile cannot be null.");
        }
    }
}
