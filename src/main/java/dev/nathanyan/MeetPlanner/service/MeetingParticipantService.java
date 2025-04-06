package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.repository.MeetingParticipantRepository;
import org.springframework.stereotype.Service;

@Service
public class MeetingParticipantService {
    private final MeetingParticipantRepository meetingParticipantRepository;

    public MeetingParticipantService(MeetingParticipantRepository meetingParticipantRepository) {
        this.meetingParticipantRepository = meetingParticipantRepository;
    }


}
