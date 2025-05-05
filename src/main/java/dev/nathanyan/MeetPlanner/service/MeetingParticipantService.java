package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.model.MeetingParticipant;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.repository.MeetingParticipantRepository;
import dev.nathanyan.MeetPlanner.model.enums.AttendanceStatus;
import javassist.NotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MeetingParticipantService {
    private final MeetingParticipantRepository meetingParticipantRepository;

    public MeetingParticipantService(MeetingParticipantRepository meetingParticipantRepository) {
        this.meetingParticipantRepository = meetingParticipantRepository;
    }

    public MeetingParticipant create(Meeting meeting, Participant participant, AttendanceStatus attendanceStatus) {

        try {
            MeetingParticipant meetingParticipant = new MeetingParticipant();

            meetingParticipant.setMeeting(meeting);
            meetingParticipant.setAttendanceStatus(attendanceStatus);
            meetingParticipant.setParticipant(participant);

            return meetingParticipantRepository.save(meetingParticipant);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void confirmPresence(Meeting meeting, Participant participant) throws NotFoundException {
        int updatedRows = meetingParticipantRepository.updateAttendanceStatus(meeting.getId(), participant.getId(), AttendanceStatus.CONFIRMED);

        if(updatedRows == 0) {
            throw new NotFoundException("Participant or Meeting not found");
        }
    }
}
