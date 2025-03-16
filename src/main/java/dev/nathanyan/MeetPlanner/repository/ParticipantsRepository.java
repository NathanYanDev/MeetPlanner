package dev.nathanyan.MeetPlanner.repository;

import dev.nathanyan.MeetPlanner.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ParticipantsRepository extends JpaRepository<Participant, String> {
    @Query("SELECT p FROM Participant p WHERE p.email IN :emails")
    Set<Participant> findAllByEmail(@Param("emails") Iterable<String> emails);
}
