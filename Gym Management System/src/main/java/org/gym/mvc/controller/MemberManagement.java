package org.gym.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.gym.mvc.controller.persistance.GymDatabaseConnection;
import org.gym.mvc.model.Attendance;
import org.gym.mvc.model.Member;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/*
The system should allow the management of member information, including registration, membership
plans, personal details, and contact information.
 */
public class MemberManagement {
    private static List<Member> members = new ArrayList<>();

    static {
        members = new ArrayList<>();
    }

    private int subscriptionValidity;

    public void viewProfile(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                System.out.println("Member ID: " + member.getId());
                System.out.println("Username: " + member.getUsername());
                System.out.println("Contact Details: " + member.getContactDetails());
                System.out.println("Preferences: " + member.getPreferences());
                viewAttendanceHistory(id);
                return;
            }
        }
        System.out.println("Member with ID " + id + " not found.");
    }

    public void viewAttendanceHistory(int memberId) {
        Member member = findMemberById(memberId);
        if (member != null) {
            System.out.println("Attendance History:");
            List<Attendance> attendanceHistory = member.getAttendanceHistory();
            if (attendanceHistory.isEmpty()) {
                System.out.println("No attendance history available.");
            } else {
                for (Attendance attendance : attendanceHistory) {
                    System.out.println("Date: " + attendance.getDate());
                }
            }
            System.out.println();
        } else {
            System.out.println("Member not found.");
        }
    }


    public void registerMember(String username,
                               String password,
                               String contactDetails,
                               String preferences,
                               Attendance[] attendanceHistory) {
        Member newMember = new Member(username, password, contactDetails, preferences);
        setSubscriptionValidity(1); // Set the validity to 1 month
        members.add(newMember);

        // Save the member to the database
        try (Connection connection = GymDatabaseConnection.connect()) {
            String sql =
                    "INSERT INTO member (id, username, password, contactDetails, preferences, " +
                    "subscriptionValidity, attendanceHistory) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newMember.getId());
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, contactDetails);
            statement.setString(5, preferences);
            statement.setInt(6, newMember.getSubscriptionValidity());
            String attendanceJson = convertAttendanceArrayToJson(attendanceHistory);
            statement.setString(7, attendanceJson);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String convertAttendanceArrayToJson(Attendance[] attendanceHistory) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register the JavaTimeModule
        try {
            return objectMapper.writeValueAsString(attendanceHistory);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]"; // Return an empty array as a fallback
        }
    }

    public void removeMember(int memberId) {
        Member foundMember = null;
        for (Member member : members) {
            if (member.getId() == memberId) {
                foundMember = member;
                break;
            }
        }
        if (foundMember != null) {
            setSubscriptionValidity(0);
            members.remove(foundMember);
        }
    }


    public void getAllMembers() {
        List<String> memberDetails = new ArrayList<>();

        // Assuming you have a connection to the database
        try (Connection connection = GymDatabaseConnection.connect()) {
            String query = "SELECT * FROM member";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int memberId = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String contactDetails = resultSet.getString("contactDetails");
                    String subscriptionValidity = resultSet.getString("subscriptionValidity");
                    String preferences = resultSet.getString("preferences");
                    List<Attendance> attendanceHistory = getAttendanceHistoryForMember(memberId);

                    String memberInfo =
                            "Member ID: " + memberId + "\n" + "Username: " + username + "\n" +
                            "Password: " + password + "\n" + "Contact Details: " + contactDetails +
                            "\n" + "Subscription Validity: " + subscriptionValidity + "\n" +
                            "Preferences: " + preferences + "\n" + "Attendance History: " +
                            attendanceHistoryToString(attendanceHistory) + "\n";

                    memberDetails.add(memberInfo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String memberDetail : memberDetails) {
            System.out.println(memberDetail);
        }
    }

    private List<Attendance> getAttendanceHistoryForMember(int memberId) {
        List<Attendance> attendanceHistory = new ArrayList<>();

        // Assuming you have a connection to the database
        try (Connection connection = GymDatabaseConnection.connect()) {
            String query = "SELECT * FROM member WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, memberId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Assuming you have an Attendance constructor that accepts the relevant
                        // parameters
                        Attendance attendance = new Attendance();
                        attendanceHistory.add(attendance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceHistory;
    }

    private String attendanceHistoryToString(List<Attendance> attendanceHistory) {
        StringBuilder sb = new StringBuilder();
        for (Attendance attendance : attendanceHistory) {
            sb.append(attendance.toString()).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        }
        return sb.toString();
    }

    public void setSubscriptionValidity(int validityMonths) {
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusMonths(validityMonths);
        long daysUntilExpiration = ChronoUnit.DAYS.between(currentDate, expirationDate);
        long monthsUntilExpiration = ChronoUnit.MONTHS.between(currentDate, expirationDate);

        if (daysUntilExpiration >= 30) {
            subscriptionValidity = (int) monthsUntilExpiration;
        } else {
            subscriptionValidity = (int) Math.ceil(daysUntilExpiration / 30.0);
        }
    }

    public int getSubscriptionValidity(int memberId) {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member.getSubscriptionValidity();
            }
        }
        return -1;
    }

    private Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        return null;
    }
}
