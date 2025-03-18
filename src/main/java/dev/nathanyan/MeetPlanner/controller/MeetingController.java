package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.handler.ResponseHandler;
import dev.nathanyan.MeetPlanner.model.CreateMeetingRequest;
import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.service.MeetingService;
import dev.nathanyan.MeetPlanner.service.ParticipantsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<Meeting> meetings = meetingService.getAll();
        if(meetings.isEmpty()) {
            return ResponseHandler.generateResponse("No meeting has yet been created", HttpStatus.OK, Collections.emptyList());
        } else {
            return ResponseHandler.generateResponse("The meetings were successfully found", HttpStatus.OK, meetings);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createMeeting(@RequestBody CreateMeetingRequest request) {
        Set<Participant> participants = participantsService.getParticipantsByEmail(request.getParticipantsEmail());
        Set<String> emailsNotFound = new HashSet<>(request.getParticipantsEmail());

        for(String email: participants.stream().map(Participant::getEmail).toList()) {
            emailsNotFound.remove(email);
        }

        Meeting meeting = new Meeting(null, request.getTitle(), request.getDescription(), request.getDateTime(), request.getLocation(), LocalDateTime.now(), request.getDuration(), participants);

        try {
            Meeting data = meetingService.create(meeting);

            if(!emailsNotFound.isEmpty()) {
                return ResponseHandler.generateResponse(String.format("The meeting was created successfully, but some emails are not found: %s", emailsNotFound), HttpStatus.CREATED, data);
            } else {
                return ResponseHandler.generateResponse("The meeting was created successfully", HttpStatus.CREATED, data);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error, check the fields and try again");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        meetingService.delete(id);
        return ResponseHandler.generateResponse("The meeting was deleted successfully", HttpStatus.OK, Collections.emptyList());
    }
}
