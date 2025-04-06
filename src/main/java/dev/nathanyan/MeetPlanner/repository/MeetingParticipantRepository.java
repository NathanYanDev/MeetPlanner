package dev.nathanyan.MeetPlanner.repository;

import dev.nathanyan.MeetPlanner.domain.meetingparticipant.MeetingParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipant, String> {
}
