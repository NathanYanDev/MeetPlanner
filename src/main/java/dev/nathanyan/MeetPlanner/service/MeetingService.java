package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
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
    public String create(Meeting meeting) {
        LocalDate createdAt = LocalDate.now();

        meeting.setCreatedAt(createdAt);

        meetingRepository.save(meeting);

        return "Meeting created successfully";
    }

    // Delete
    public void delete(Long id) {meetingRepository.deleteById(id);}
}
