package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.dto.MeetingDTO;
import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public List<MeetingDTO> getAll() {
        return meetingRepository.findAll().stream().map(MeetingDTO::new).toList();
    }

    public Optional<Meeting> getById(String meetingId) {
        return meetingRepository.findById(meetingId);
    }

    public MeetingDTO create(Meeting meeting){
        Meeting meetingCreated = meetingRepository.save(meeting);

        return new MeetingDTO(meetingCreated);

    }

    public void delete(String id) {meetingRepository.deleteById(id);}
}
