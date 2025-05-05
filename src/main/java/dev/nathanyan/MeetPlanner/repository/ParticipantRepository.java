package dev.nathanyan.MeetPlanner.repository;

import dev.nathanyan.MeetPlanner.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public interface ParticipantRepository extends JpaRepository<Participant, String> {
    @Query("SELECT p FROM Participant p WHERE p.email IN :emails")
    Set<Participant> getAllByEmails(@Param("emails") Iterable<String> emails);

    @Query("SELECT p FROM Participant p WHERE p.email = :email")
    Participant getByEmail(@Param("email") String email);

    UserDetails findByEmail(String email);
}
