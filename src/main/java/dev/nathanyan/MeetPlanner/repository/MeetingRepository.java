package dev.nathanyan.MeetPlanner.repository;

import dev.nathanyan.MeetPlanner.domain.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, String> {
}
