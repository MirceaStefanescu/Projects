package org.gym.mvc.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter @Setter @NoArgsConstructor public class Profile {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private int age = 0;
    private Gender sex;

    @Builder
    public Profile(String username, String password, String firstName, String lastName,
                   String address, String city, String province, String postalCode, int age,
                   Gender sex) {
        this.username = username;
        this.password = PasswordService.hashPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.age = age;
        this.sex = sex;
    }

    @Override public String toString() {
        return "Profile{" + "username='" + username + '\'' + ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' + ", address='" + address + '\'' + ", city='" +
               city + '\'' + ", province='" + province + '\'' + ", postalCode='" + postalCode +
               '\'' + ", age=" + age + ", sex=" + sex + '}';
    }
}

class PasswordService {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
