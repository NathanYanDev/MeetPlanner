package dev.nathanyan.MeetPlanner.dto.request.meetingparticipant;

import dev.nathanyan.MeetPlanner.model.enums.AttendanceStatus;

import java.util.Set;

public record MeetingParticipantRequestDTO(
        String participantEmail,
        AttendanceStatus attendanceStatus
) {
}
