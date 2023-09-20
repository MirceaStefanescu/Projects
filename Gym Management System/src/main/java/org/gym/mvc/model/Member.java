package org.gym.mvc.model;

import java.util.*;

public class Member implements User {
    private static final Random random = new Random();
    private final String username;
    private final String password;
    private final List<Attendance> attendanceHistory;
    private final Set<Integer> generatedIDs = new HashSet<>();
    private int id;
    private int subscriptionValidity;
    private String contactDetails;
    private String preferences;

    public Member(String username, String password, String contactDetails, String preferences) {
        this.id = generateUniqueID();
        this.username = username;
        this.password = password;
        this.contactDetails = contactDetails;
        this.preferences = preferences;
        this.attendanceHistory = new ArrayList<>();
    }

    public void addAttendanceRecord(Attendance attendance) {
        attendanceHistory.add(attendance);
    }

    public List<Attendance> getAttendanceHistory() {
        return attendanceHistory;
    }

    public int getSubscriptionValidity() {
        return subscriptionValidity;
    }

    private int generateUniqueID() {
        do {
            this.id = random.nextInt(10000) + 1;
        } while (generatedIDs.contains(id));

        generatedIDs.add(this.id);
        return this.id;
    }


    public void updateContactDetails(String contactDetails) {
        // Logic to validate and update member's contact details
        if (isValidContactDetails(contactDetails)) {
            this.contactDetails = contactDetails;
            System.out.println("Contact details updated successfully.");
        } else {
            System.out.println("Invalid contact details. Please provide valid contact details.");
        }
    }

    private boolean isValidContactDetails(String contactDetails) {
        // Contact details should contain at least one digit and one letter
        boolean containsDigit = false;
        boolean containsLetter = false;

        for (char c : contactDetails.toCharArray()) {
            if (Character.isDigit(c)) {
                containsDigit = true;
            } else if (Character.isLetter(c)) {
                containsLetter = true;
            }
        }
        return containsDigit && containsLetter;
    }


    public void updatePreferences(String preferences) {
        // Logic to validate and update member's preferences
        if (isValidPreferences(preferences)) {
            this.preferences = preferences;
            System.out.println("Preferences updated successfully.");
        } else {
            String permittedValues = "low, medium, high";
            System.out.println(
                    "Invalid preferences. Please provide valid preferences (" + permittedValues +
                    ").");
        }
    }

    private boolean isValidPreferences(String preferences) {
        // Validate preferences against the permitted values
        String[] permittedValues = {"low", "medium", "high"};
        return Arrays.asList(permittedValues).contains(preferences.toLowerCase());
    }

    @Override public String getUsername() {
        return this.username;
    }

    @Override public String getPassword() {
        return this.password;
    }

    public int getId() {
        return id;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    @Override public String toString() {
        return "Member ID: " + id + "\n" + "Username: " + username + "\n" + "Password: " +
               password + "\n" + "Attendance History: " + attendanceHistoryToString() + "\n" +
               "Subscription Validity: " + subscriptionValidity + "\n" + "Contact Details: " +
               contactDetails + "\n" + "Preferences: " + preferences;
    }

    private String attendanceHistoryToString() {
        StringBuilder sb = new StringBuilder();
        for (Attendance attendance : attendanceHistory) {
            sb.append(attendance.toString()).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        }
        return sb.toString();
    }
}
