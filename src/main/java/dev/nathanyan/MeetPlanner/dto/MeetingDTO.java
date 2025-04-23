package dev.nathanyan.MeetPlanner.dto;

import dev.nathanyan.MeetPlanner.model.Meeting;

import java.time.Instant;
import java.util.List;

public record MeetingDTO(
        String id,
        String title,
        String description,
        Instant dateTime,
        String location,
        Integer duration,
        List<ParticipantInfoDTO> participants
) {
    public MeetingDTO(Meeting meeting) {
        this(
                meeting.getId(),
                meeting.getTitle(),
                meeting.getDescription(),
                meeting.getDateTime(),
                meeting.getLocation(),
                meeting.getDuration(),
                meeting.getParticipants().stream().map(meetingParticipant -> new ParticipantInfoDTO(meetingParticipant.getParticipant())).toList());
    }
}
