package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.dto.ParticipantDTO;
import dev.nathanyan.MeetPlanner.handler.ResponseHandler;
import dev.nathanyan.MeetPlanner.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
