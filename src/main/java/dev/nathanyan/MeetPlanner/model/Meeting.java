package dev.nathanyan.MeetPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
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

    @ManyToMany
    @JoinTable(name = "meeting_participant", joinColumns = @JoinColumn(name = "meeting_id"), inverseJoinColumns = @JoinColumn(name = "participant_id"))
    @JsonIgnoreProperties("meetings")
    private Set<Participant> participants;
}
