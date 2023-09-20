package org.gym.mvc.model;

import java.time.LocalDate;

public class Attendance {

    private final LocalDate date;

    public Attendance() {
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }

    @Override public String toString() {
        return date.toString();
    }
}
