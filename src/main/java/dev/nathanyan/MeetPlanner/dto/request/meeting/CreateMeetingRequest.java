package dev.nathanyan.MeetPlanner.dto.request.meeting;

import java.time.LocalDate;
import java.util.Optional;

public record CreateMeetingRequest(
    String title,
    Optional<String> description,
    LocalDate dateTime,
    String location,
    Integer duration
) {
}
