package dev.nathanyan.MeetPlanner.repository;

import dev.nathanyan.MeetPlanner.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, String> {


}
