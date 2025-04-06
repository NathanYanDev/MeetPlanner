package dev.nathanyan.MeetPlanner.domain.participant;

import dev.nathanyan.MeetPlanner.domain.meetingparticipant.MeetingParticipant;

import java.util.Set;

public record ParticipantResponseDTO(
        String id,
        String email,
        String name,
        String password,
        Set<MeetingParticipant> meetings
) {
    public ParticipantResponseDTO(Participant participant) {
        this(participant.getId(), participant.getEmail(), participant.getName(), participant.getPassword(), participant.getMeetings());
    }
}
