package org.gym.mvc.model.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.gym.mvc.model.equipment.Equipment;
import org.gym.mvc.model.facility.Facility;
import org.gym.mvc.model.membership.Membership;
import org.gym.mvc.model.membership.MembershipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode public abstract class User {
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    private final Aliases[] aliases;
    private final ActorType actorType;
    private final ContactPerson contactPerson;
    private final String contactDetails;
    protected Profile profile;
    private Membership membership;

    public abstract void performAction();

    protected String getAliasesString() {
        return Arrays.stream(aliases).map(Aliases::name).collect(Collectors.joining(", "));
    }

    public boolean canBook() {
        return false;
    }

    public void bookFacility(Facility facility) {
        if (canBook()) {
            facility.book(this);
        } else {
            LOGGER.info("This user cannot book facilities.");
        }
    }

    public void cancelFacilityBooking(Facility facility) {
        facility.cancelBooking(this);
    }

    public void bookEquipment(Equipment equipment) {
        if (canBook()) {
            equipment.book();
        } else {
            LOGGER.info("This user cannot book equipment.");
        }
    }

    public void cancelEquipmentBooking(Equipment equipment) {
        equipment.cancelBooking();
    }

    @Override public String toString() {
        return "User{" + "aliases=" + getAliasesString() + ", actorType=" + actorType +
               ", contactPerson=" + contactPerson + ", contactDetails='" + contactDetails + '\'' +
               ", profile=" + profile + '}';
    }

    public void upgradeMembership(MembershipType newType) {
        this.membership.setType(newType);
    }
}
