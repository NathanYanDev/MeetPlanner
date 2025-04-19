package dev.nathanyan.MeetPlanner.dto.request.meetingparticipant;

import dev.nathanyan.MeetPlanner.types.Status;

import java.util.Set;

public record MeetingParticipantRequestDTO(
        String meetingId,
        Set<String> participantsEmail,
        Status status
) {
}
