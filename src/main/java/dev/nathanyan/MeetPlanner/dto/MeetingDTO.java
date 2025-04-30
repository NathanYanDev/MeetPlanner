package dev.nathanyan.MeetPlanner.dto;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.model.Participant;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record MeetingDTO(
        String id,
        String title,
        String description,
        Instant dateTime,
        String location,
        Integer duration,
        Participant organizer,
        Set<ParticipantInfoDTO> participants
) {
    public MeetingDTO(Meeting meeting) {
        this(
                meeting.getId(),
                meeting.getTitle(),
                meeting.getDescription(),
                meeting.getDateTime(),
                meeting.getLocation(),
                meeting.getDuration(),
                meeting.getOrganizer(),
                meeting.getParticipants().stream().map(meetingParticipant -> new ParticipantInfoDTO(meetingParticipant.getParticipant())).collect(Collectors.toSet()));
    }
}
