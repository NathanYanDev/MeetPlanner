package dev.nathanyan.MeetPlanner.domain.meeting;

import dev.nathanyan.MeetPlanner.domain.meetingparticipant.MeetingParticipant;

import java.time.LocalDate;
import java.util.Set;

public record MeetingResponseDTO(
        String id,
        String title,
        String description,
        LocalDate dateTime,
        String location,
        Integer duration,
        Set<MeetingParticipant> participants
) {
    public MeetingResponseDTO(Meeting meeting) {
        this(meeting.getId(), meeting.getTitle(), meeting.getDescription(), meeting.getDateTime(), meeting.getLocation(), meeting.getDuration(), meeting.getParticipants());
    }
}
