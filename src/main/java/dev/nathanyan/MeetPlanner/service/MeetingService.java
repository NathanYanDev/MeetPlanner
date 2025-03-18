package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public List<Meeting> getAll() {
        return meetingRepository.findAll();
    }

    public Meeting create(Meeting meeting){
        return meetingRepository.save(meeting);
    }

    public void delete(String id) {meetingRepository.deleteById(id);}
}
