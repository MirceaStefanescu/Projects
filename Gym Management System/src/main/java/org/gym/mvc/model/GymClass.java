package org.gym.mvc.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GymClass {

    private final String id;
    private final String className;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String instructor;
    private final List<Member> attendees;

    public GymClass(String className,
                    LocalDateTime startTime,
                    LocalDateTime endTime,
                    String instructor) {
        this.id = generateUniqueID();
        this.className = className;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructor = instructor;
        this.attendees = new ArrayList<>();
    }

    private String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    public String getClassName() {
        return className;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getInstructor() {
        return instructor;
    }

    public List<Member> getAttendees() {
        return attendees;
    }

    public void addAttendee(Member member) {
        attendees.add(member);
    }

    public void removeAttendee(Member member) {
        attendees.remove(member);
    }

    public String getId() {
        return id;
    }

}
