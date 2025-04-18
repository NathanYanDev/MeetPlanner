package dev.nathanyan.MeetPlanner.domain.meeting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.nathanyan.MeetPlanner.domain.meetingparticipant.MeetingParticipant;
import dev.nathanyan.MeetPlanner.domain.participant.Participant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDate dateTime;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer duration;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MeetingParticipant> participants = new HashSet<>();

    public Meeting(String title, String description, LocalDate dateTime, String location, LocalDateTime createdAt, Integer duration) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.createdAt = createdAt;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id) && Objects.equals(title, meeting.title) && Objects.equals(description, meeting.description) && Objects.equals(dateTime, meeting.dateTime) && Objects.equals(location, meeting.location) && Objects.equals(createdAt, meeting.createdAt) && Objects.equals(duration, meeting.duration) && Objects.equals(participants, meeting.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createdAt);
    }
}
