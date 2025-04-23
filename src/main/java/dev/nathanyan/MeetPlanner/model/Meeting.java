package dev.nathanyan.MeetPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
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
    private Instant dateTime;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @Column(nullable = false)
    private Integer duration;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<MeetingParticipant> participants = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }


    public Meeting(String title, Instant dateTime, String location, Integer duration) {
        this.title = title;
        this.dateTime = dateTime;
        this.location = location;
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
