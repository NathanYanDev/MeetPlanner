package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.domain.meeting.MeetingResponseDTO;
import dev.nathanyan.MeetPlanner.domain.meetingparticipant.MeetingParticipant;
import dev.nathanyan.MeetPlanner.handler.ResponseHandler;
import dev.nathanyan.MeetPlanner.domain.meeting.MeetingRequestDTO;
import dev.nathanyan.MeetPlanner.domain.meeting.Meeting;
import dev.nathanyan.MeetPlanner.domain.participant.Participant;
import dev.nathanyan.MeetPlanner.service.MeetingService;
import dev.nathanyan.MeetPlanner.service.ParticipantsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;
    private final ParticipantsService participantsService;

    public MeetingController(MeetingService meetingService, ParticipantsService participantsService) {
        this.meetingService = meetingService;
        this.participantsService = participantsService;
    }

    @GetMapping
    public ResponseEntity<Object> getMeetings() {
        List<MeetingResponseDTO> meetingList = meetingService.getAll().stream().map(MeetingResponseDTO::new).toList();

        if(meetingList.isEmpty()) {
            return ResponseHandler.generateResponse("No meeting has yet been created", HttpStatus.OK, Collections.emptyList());
        } else {
            return ResponseHandler.generateResponse("The meetings were successfully found", HttpStatus.OK, meetingList);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createMeeting(@RequestBody @Validated MeetingRequestDTO body) {
        Meeting meeting = new Meeting(body.title(), body.description(), body.dateTime(), body.location(), LocalDateTime.now(), body.duration());

        try {
            return ResponseHandler.generateResponse("The meeting was created successfully", HttpStatus.CREATED, meetingService.create(meeting));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error, check the fields and try again");
        }
    }

    @PostMapping("/add-participant")
    public ResponseEntity<Object> addParticipant(@RequestBody @Validated Set<String> emails) {
        Set<Participant> participants = participantsService.getParticipantsByEmail(emails);
        Set<String> emailsNotFound = new HashSet<>(emails);

        for(String email: participants.stream().map(Participant::getEmail).toList()) {
            emailsNotFound.remove(email);
        }



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        meetingService.delete(id);
        return ResponseHandler.generateResponse("The meeting was deleted successfully", HttpStatus.OK, Collections.emptyList());
    }
}
