package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.dto.MeetingDTO;
import dev.nathanyan.MeetPlanner.dto.request.meeting.CreateMeetingRequest;
import dev.nathanyan.MeetPlanner.dto.request.meetingparticipant.MeetingParticipantRequestDTO;
import dev.nathanyan.MeetPlanner.handler.ResponseHandler;
import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.service.MeetingParticipantService;
import dev.nathanyan.MeetPlanner.service.MeetingService;
import dev.nathanyan.MeetPlanner.service.ParticipantService;
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
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;
    private final ParticipantService participantService;
    private final MeetingParticipantService meetingParticipantService;

    public MeetingController(MeetingService meetingService, ParticipantService participantService, MeetingParticipantService meetingParticipantService) {
        this.meetingService = meetingService;
        this.participantService = participantService;
        this.meetingParticipantService = meetingParticipantService;
    }

    @GetMapping
    public ResponseEntity<Object> getMeetings() {
        List<MeetingDTO> meetingList = meetingService.getAll().stream().map(MeetingDTO::new).toList();

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

        Meeting meeting = new Meeting(body.title(), meetingDateTime, body.location(), body.duration());

        if(body.description().isPresent()) meeting.setDescription(body.description().get());

        try {
            return ResponseHandler.generateResponse("The meeting was created successfully", HttpStatus.CREATED, meetingService.create(meeting));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error, check the fields and try again");
        }
    }

    @PostMapping("/add-participant")
    public ResponseEntity<Object> addParticipant(@RequestBody @Validated MeetingParticipantRequestDTO body) {
        Meeting meetingData = meetingService.getById(body.meetingId()).orElseThrow();
        Set<Participant> participants = participantService.getParticipantsByEmail(body.participantsEmail());
        List<String> emailsNotFound = new ArrayList<>(body.participantsEmail());

        for(String email: participants.stream().map(Participant::getEmail).toList()) {
            emailsNotFound.remove(email);
        }

        String flashMessage = meetingParticipantService.create(meetingData, participants, body.meetingStatus());

        return ResponseHandler.generateResponse(flashMessage, HttpStatus.OK, emailsNotFound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        meetingService.delete(id);
        return ResponseHandler.generateResponse("The meeting was deleted successfully", HttpStatus.OK, Collections.emptyList());
    }
}
