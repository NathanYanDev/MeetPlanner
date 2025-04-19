package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.model.MeetingParticipant;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.repository.MeetingParticipantRepository;
import dev.nathanyan.MeetPlanner.types.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class MeetingParticipantService {
    private final MeetingParticipantRepository meetingParticipantRepository;

    public MeetingParticipantService(MeetingParticipantRepository meetingParticipantRepository) {
        this.meetingParticipantRepository = meetingParticipantRepository;
    }

    public String create(Meeting meeting, Set<Participant> participants, Status status) {

        try {
            MeetingParticipant meetingParticipant = new MeetingParticipant();

            meetingParticipant.setMeeting(meeting);
            meetingParticipant.setCreatedAt(LocalDateTime.now());
            meetingParticipant.setStatus(status);

            for(Participant participant: participants) {
                meetingParticipant.setParticipant(participant);
                meetingParticipantRepository.save(meetingParticipant);
            }

            return "Participants successfully added";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
