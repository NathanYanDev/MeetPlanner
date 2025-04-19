package dev.nathanyan.MeetPlanner.dto.request.participant;

public record CreateParticipant(
        String email,
        String name,
        String password
) {
}
