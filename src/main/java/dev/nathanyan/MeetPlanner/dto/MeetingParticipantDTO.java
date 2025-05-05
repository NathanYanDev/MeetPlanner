package dev.nathanyan.MeetPlanner.dto;

import dev.nathanyan.MeetPlanner.model.MeetingParticipant;
import dev.nathanyan.MeetPlanner.model.enums.AttendanceStatus;

public record MeetingParticipantDTO(
        String id,
        AttendanceStatus attendanceStatus,
        MeetingDTO meetingDTO,
        ParticipantInfoDTO participantInfoDTO
) {
    public MeetingParticipantDTO(MeetingParticipant meetingParticipant) {
        this(
                meetingParticipant.getId(), meetingParticipant.getAttendanceStatus(), new MeetingDTO(meetingParticipant.getMeeting()), new ParticipantInfoDTO(meetingParticipant.getParticipant())
        );
    }
}
