package dev.nathanyan.MeetPlanner.dto;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.model.MeetingParticipant;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.model.enums.UserRole;

import java.util.List;

public record ParticipantDTO(
        String id,
        String name,
        String email,
        UserRole role,
        List<Meeting> meetings
) {
    public ParticipantDTO(Participant participant) {
        this(participant.getId(), participant.getName(), participant.getEmail(), participant.getRole(), participant.getMeetings().stream().map(MeetingParticipant::getMeeting).toList());
    }
}
