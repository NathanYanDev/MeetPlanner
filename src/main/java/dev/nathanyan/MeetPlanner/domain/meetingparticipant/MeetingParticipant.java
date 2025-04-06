package dev.nathanyan.MeetPlanner.domain.meetingparticipant;

import dev.nathanyan.MeetPlanner.domain.meeting.Meeting;
import dev.nathanyan.MeetPlanner.domain.participant.Participant;
import dev.nathanyan.MeetPlanner.types.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meeting_participant")
public class MeetingParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
