package org.gym.mvc.model.membership;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter public class Membership {
    private MembershipType type;
    private LocalDate startDate;
    private LocalDate endDate;
}