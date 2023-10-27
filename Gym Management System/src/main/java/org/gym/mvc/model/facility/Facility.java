package org.gym.mvc.model.facility;

import lombok.Getter;
import org.gym.mvc.model.user.AnonymousUser;
import org.gym.mvc.model.user.User;

import java.util.HashSet;
import java.util.Set;

public class Facility {
    @Getter private final String name;
    private final Set<User> bookedByUsers;
    private boolean isBooked;

    public Facility(String name) {
        this.name = name;
        this.isBooked = false;
        this.bookedByUsers = new HashSet<>();
    }

    public boolean isBooked() {
        return isBooked;
    }

    private void setBooked(User user) {
        isBooked = true;
        bookedByUsers.add(user);
    }

    private void setUnbooked(User user) {
        bookedByUsers.remove(user);
        if (bookedByUsers.isEmpty()) {
            isBooked = false;
        }
    }

    public void book(User user) {
        if (user instanceof AnonymousUser) {
            throw new AnonymousUserBookingException("Anonymous users cannot book facilities.");
        }
        setBooked(user);
    }

    public void cancelBooking(User user) {
        if (!isBooked) {
            throw new IllegalStateException("The facility is not booked.");
        }
        if (!bookedByUsers.contains(user)) {
            throw new IllegalStateException(
                    "Only the user who booked the facility can cancel the booking.");
        }
        setUnbooked(user);
    }
}

class AnonymousUserBookingException extends RuntimeException {
    public AnonymousUserBookingException(String message) {
        super(message);
    }
}
