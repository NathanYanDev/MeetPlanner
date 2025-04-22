package dev.nathanyan.MeetPlanner.model;

import dev.nathanyan.MeetPlanner.model.enums.MeetingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
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
    private MeetingStatus meetingStatus;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public MeetingParticipant(Meeting meeting, Participant participant, MeetingStatus meetingStatus, LocalDateTime createdAt) {
        this.meeting = meeting;
        this.participant = participant;
        this.meetingStatus = meetingStatus;
        this.createdAt = createdAt;
    }
}
