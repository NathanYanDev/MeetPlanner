package dev.nathanyan.MeetPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.nathanyan.MeetPlanner.types.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "participants")
    @JsonIgnoreProperties("participants")
    private Set<Meeting> meetings;
}
