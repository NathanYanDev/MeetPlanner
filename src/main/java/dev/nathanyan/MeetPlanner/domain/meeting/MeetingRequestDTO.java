package dev.nathanyan.MeetPlanner.domain.meeting;

import java.time.LocalDate;
import java.util.Set;

public record MeetingRequestDTO(
        String title,
        String description,
        LocalDate dateTime,
        String location,
        Integer duration,
        Set<String> participantsEmail
) {
}
