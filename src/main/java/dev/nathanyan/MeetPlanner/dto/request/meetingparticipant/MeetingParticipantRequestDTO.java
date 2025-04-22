package dev.nathanyan.MeetPlanner.dto.request.meetingparticipant;

import dev.nathanyan.MeetPlanner.model.enums.MeetingStatus;

import java.util.Set;

public record MeetingParticipantRequestDTO(
        String meetingId,
        Set<String> participantsEmail,
        MeetingStatus meetingStatus
) {
}
