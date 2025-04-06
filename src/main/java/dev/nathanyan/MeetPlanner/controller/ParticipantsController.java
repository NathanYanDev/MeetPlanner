package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.domain.participant.ParticipantRequestDTO;
import dev.nathanyan.MeetPlanner.domain.participant.ParticipantResponseDTO;
import dev.nathanyan.MeetPlanner.handler.ResponseHandler;
import dev.nathanyan.MeetPlanner.domain.participant.Participant;
import dev.nathanyan.MeetPlanner.service.ParticipantsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
        List<ParticipantResponseDTO> participantList = participantsService.listAll().stream().map(ParticipantResponseDTO::new).toList();

        if(participantList.isEmpty()) {
            return ResponseHandler.generateResponse("No participant has yet been created", HttpStatus.OK, Collections.emptyList());
        } else {
            return ResponseHandler.generateResponse("The participants were successfully found", HttpStatus.OK, participantList);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Validated ParticipantRequestDTO body) {
        Participant participant = new Participant(body.email(), body.name(), body.password(), LocalDateTime.now());

        try {
            return ResponseHandler.generateResponse("The participant was created successfully", HttpStatus.CREATED, participantsService.create(participant));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error, check the fields and try again");
        }
    }
}
