package dev.nathanyan.MeetPlanner.dto.request.meeting;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Optional;

public record CreateMeetingRequest(
    String title,
    Optional<String> description,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    String dateTime,
    String location,
    Integer duration
) {
}
