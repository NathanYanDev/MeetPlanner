package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.dto.MeetingDTO;
import dev.nathanyan.MeetPlanner.dto.MeetingParticipantDTO;
import dev.nathanyan.MeetPlanner.dto.request.meeting.CreateMeetingRequest;
import dev.nathanyan.MeetPlanner.dto.request.meetingparticipant.MeetingParticipantRequestDTO;
import dev.nathanyan.MeetPlanner.handler.ResponseHandler;
import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.model.enums.AttendanceStatus;
import dev.nathanyan.MeetPlanner.service.MeetingParticipantService;
import dev.nathanyan.MeetPlanner.service.MeetingService;
import dev.nathanyan.MeetPlanner.service.ParticipantService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingService meetingService;
    private final ParticipantService participantService;
    private final MeetingParticipantService meetingParticipantService;
    private final MailSenderController mailSenderController;

    public MeetingController(MeetingService meetingService, ParticipantService participantService, MeetingParticipantService meetingParticipantService, MailSenderController mailSenderController) {
        this.meetingService = meetingService;
        this.participantService = participantService;
        this.meetingParticipantService = meetingParticipantService;
        this.mailSenderController = mailSenderController;
    }

    @GetMapping
    public ResponseEntity<Object> getMeetings() {
        List<MeetingDTO> meetingList = meetingService.getAll();

        if(meetingList.isEmpty()) {
            return ResponseHandler.generateResponse("No meeting has yet been created", HttpStatus.OK, Collections.emptyList());
        } else {
            return ResponseHandler.generateResponse("The meetings were successfully found", HttpStatus.OK, meetingList);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createMeeting(@RequestBody @Validated CreateMeetingRequest body) {
        LocalDateTime localDateTime = LocalDateTime.parse(body.dateTime());
        Instant meetingDateTime = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

        Participant organizer = participantService.getByEmail(body.organizerEmail());

        if(organizer == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organizer data not found, verify the email typed");

        Meeting meeting = new Meeting(body.title(), meetingDateTime, body.location(), body.duration(), organizer);

        if(body.description().isPresent()) meeting.setDescription(body.description().get());

        try {
            return ResponseHandler.generateResponse("The meeting was created successfully", HttpStatus.CREATED, meetingService.create(meeting));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error, check the fields and try again");
        }
    }

    @PostMapping("/{id}/participants/add")
    public ResponseEntity<Object> addParticipant(@RequestBody @Validated MeetingParticipantRequestDTO body, @PathVariable String id) {
        Meeting meetingData = meetingService.getById(id).orElseThrow();
        Participant participant = participantService.getByEmail(body.participantEmail());

        if(participant == null) {
            return ResponseHandler.generateResponse("Participant not found, make sure you type a correct email", HttpStatus.BAD_REQUEST);
        }

        MeetingParticipantDTO meetingParticipantDTO = new MeetingParticipantDTO(meetingParticipantService.create(meetingData, participant, AttendanceStatus.PENDING));

        mailSenderController.sendMail(participant.getEmail(), meetingData);

        return ResponseHandler.generateResponse("Participant successfully added to the meeting", HttpStatus.OK, meetingParticipantDTO);
    }

    @PatchMapping("/{id}/participants/{participantId}/confirm")
    public ResponseEntity<Object> confirmPresence(@PathVariable String id, @PathVariable String participantId) throws NotFoundException {
        Meeting meeting = meetingService.getById(id).orElseThrow();
        Participant participant = participantService.getById(participantId).orElseThrow();

        meetingParticipantService.confirmPresence(meeting, participant);

        return ResponseHandler.generateResponse("Attendance at the meeting was confirmed", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        meetingService.delete(id);
        return ResponseHandler.generateResponse("The meeting was deleted successfully", HttpStatus.OK);
    }
}
