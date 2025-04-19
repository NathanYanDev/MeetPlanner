package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.dto.ParticipantDTO;
import dev.nathanyan.MeetPlanner.dto.request.participant.CreateParticipant;
import dev.nathanyan.MeetPlanner.handler.ResponseHandler;
import dev.nathanyan.MeetPlanner.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<ParticipantDTO> participantList = participantService.listAll();

        if(participantList.isEmpty()) {
            return ResponseHandler.generateResponse("No participant has yet been created", HttpStatus.OK, Collections.emptyList());
        } else {
            return ResponseHandler.generateResponse("The participants were successfully found", HttpStatus.OK, participantList);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Validated CreateParticipant body) {
        ParticipantDTO newParticipant = participantService.create(body);

        try {
            return ResponseHandler.generateResponse("The participant was created successfully", HttpStatus.CREATED, newParticipant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error, check the fields and try again");
        }
    }
}
