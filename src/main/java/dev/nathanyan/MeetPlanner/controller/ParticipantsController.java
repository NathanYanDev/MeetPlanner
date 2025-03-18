package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.handler.ResponseHandler;
import dev.nathanyan.MeetPlanner.model.CreateParticipantRequest;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.service.ParticipantsService;
import dev.nathanyan.MeetPlanner.types.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantsController {

    private final ParticipantsService participantsService;

    public ParticipantsController(ParticipantsService participantsService) {
        this.participantsService = participantsService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Participant> participants = participantsService.listAll();
        if(participants.isEmpty()) {
            return ResponseHandler.generateResponse("No participant has yet been created", HttpStatus.OK, Collections.emptyList());
        } else {
            return ResponseHandler.generateResponse("The participants were successfully found", HttpStatus.OK, participants);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CreateParticipantRequest request) {
        Participant participant = new Participant(null, request.getEmail(), request.getName(), Status.PENDING, LocalDateTime.now(), Collections.emptySet());

        try {
            return ResponseHandler.generateResponse("The participant was created successfully", HttpStatus.CREATED, participant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error, check the fields and try again");
        }
    }
}
