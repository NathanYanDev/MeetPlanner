package dev.nathanyan.MeetPlanner.dto.request.participant;

import dev.nathanyan.MeetPlanner.model.enums.UserRole;

public record CreateParticipant(
        String email,
        String name,
        String password,
        UserRole role
) {
}
