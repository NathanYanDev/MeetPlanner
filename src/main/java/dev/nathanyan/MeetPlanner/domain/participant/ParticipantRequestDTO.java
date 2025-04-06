package dev.nathanyan.MeetPlanner.domain.participant;

public record ParticipantRequestDTO(
        String email,
        String name,
        String password
) {
}
