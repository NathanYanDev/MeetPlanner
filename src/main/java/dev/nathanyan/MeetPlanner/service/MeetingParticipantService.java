package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.model.MeetingParticipant;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.repository.MeetingParticipantRepository;
import dev.nathanyan.MeetPlanner.model.enums.MeetingStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MeetingParticipantService {
    private final MeetingParticipantRepository meetingParticipantRepository;

    public MeetingParticipantService(MeetingParticipantRepository meetingParticipantRepository) {
        this.meetingParticipantRepository = meetingParticipantRepository;
    }

    public String create(Meeting meeting, Set<Participant> participants, MeetingStatus meetingStatus) {

        try {
            MeetingParticipant meetingParticipant = new MeetingParticipant();

            meetingParticipant.setMeeting(meeting);
            meetingParticipant.setMeetingStatus(meetingStatus);

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
