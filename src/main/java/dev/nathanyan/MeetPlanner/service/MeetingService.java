package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MeetingService {
    Logger logger = Logger.getLogger(getClass().getName());

    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public List<Meeting> getAll() {
        return meetingRepository.findAll();
    }

    public String create(Meeting meeting){
        try{
            meetingRepository.save(meeting);

            return "Meeting created successfully";
        }catch(Exception err) {
            logger.info(err.getMessage());

            return "The meeting cannot be created, check the fields and try again";
        }
    }

    public void delete(String id) {meetingRepository.deleteById(id);}
}
