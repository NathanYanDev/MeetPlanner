package dev.nathanyan.MeetPlanner.model;

import dev.nathanyan.MeetPlanner.model.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

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
    private AttendanceStatus attendanceStatus;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }


    public MeetingParticipant(Meeting meeting, Participant participant, AttendanceStatus attendanceStatus) {
        this.meeting = meeting;
        this.participant = participant;
        this.attendanceStatus = attendanceStatus;
    }
}
