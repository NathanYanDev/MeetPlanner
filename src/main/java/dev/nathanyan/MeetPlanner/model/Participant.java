package dev.nathanyan.MeetPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<MeetingParticipant> meetings = new HashSet<>();

    public Participant(String email, String name, String password, LocalDateTime createdAt) {
        this.email = email.toLowerCase();
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(createdAt, that.createdAt) && Objects.equals(meetings, that.meetings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
