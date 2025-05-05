package dev.nathanyan.MeetPlanner.repository;

import dev.nathanyan.MeetPlanner.model.MeetingParticipant;
import dev.nathanyan.MeetPlanner.model.enums.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipant, String> {
    @Transactional
    @Modifying
    @Query("UPDATE MeetingParticipant mp SET mp.attendanceStatus = :status " +  "WHERE mp.meeting.id = :meetingId AND mp.participant.id = :participantId")
    int updateAttendanceStatus(@Param("meetingId") String meetingId, @Param("participantId") String participantId, @Param("status")AttendanceStatus status);
}
