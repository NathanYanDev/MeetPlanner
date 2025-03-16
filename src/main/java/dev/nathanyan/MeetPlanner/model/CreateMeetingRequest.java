package dev.nathanyan.MeetPlanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMeetingRequest {
    private String title;
    private String description;
    private LocalDate dateTime;
    private String location;
    private Integer duration;
    private Set<String> participantsEmail;
}
