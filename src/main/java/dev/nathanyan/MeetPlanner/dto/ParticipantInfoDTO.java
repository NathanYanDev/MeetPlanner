package dev.nathanyan.MeetPlanner.dto;

import dev.nathanyan.MeetPlanner.model.Participant;

public record ParticipantInfoDTO(
        String id,
        String name,
        String email
) {
    public ParticipantInfoDTO(Participant participant) {
        this(participant.getId(), participant.getName(), participant.getEmail());
    }
}
