package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.repository.MeetingRepository;

import java.time.LocalDate;
import java.util.List;

public class MeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    // Read
    public List<Meeting> getAll() {
        return meetingRepository.findAll();
    }

    // Create
    public String create(String title, String description, LocalDate dateTime, String location, Integer duration) {
        LocalDate createdAt = LocalDate.now();

        Meeting meeting = new Meeting();

        meeting.setTitle(title);
        meeting.setDescription(description);
        meeting.setDateTime(dateTime);
        meeting.setLocation(location);
        meeting.setCreatedAt(createdAt);
        meeting.setDuration(duration);

        meetingRepository.save(meeting);

        return "Meeting created successfully";
    }

    // Delete
    public void delete(Long id) {meetingRepository.deleteById(id);}
}
