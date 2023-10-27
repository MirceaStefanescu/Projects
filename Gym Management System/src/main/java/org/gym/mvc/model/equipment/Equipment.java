package org.gym.mvc.model.equipment;

import lombok.Getter;

public class Equipment {
    @Getter private final String name;
    private boolean isBooked;

    public Equipment(String name) {
        this.name = name;
        this.isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    private void setBooked() {
        isBooked = true;
    }

    private void setUnbooked() {
        isBooked = false;
    }

    public void book() {
        if (isBooked) {
            throw new EquipmentAlreadyBookedException("The equipment is already booked.");
        }
        setBooked();
    }

    public void cancelBooking() {
        if (!isBooked) {
            throw new EquipmentNotBookedException("The equipment is not booked.");
        }
        setUnbooked();
    }
}

class EquipmentAlreadyBookedException extends RuntimeException {
    public EquipmentAlreadyBookedException(String message) {
        super(message);
    }
}

class EquipmentNotBookedException extends RuntimeException {
    public EquipmentNotBookedException(String message) {
        super(message);
    }
}
