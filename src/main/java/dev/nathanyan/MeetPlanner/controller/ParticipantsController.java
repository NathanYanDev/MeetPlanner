package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.model.CreateParticipantRequest;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.service.ParticipantsService;
import dev.nathanyan.MeetPlanner.types.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Participant> getAll() {
        return participantsService.listAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CreateParticipantRequest request) {
        Participant participant = new Participant(null, request.getEmail(), request.getName(), Status.PENDING, LocalDateTime.now(), Collections.emptySet());

        return ResponseEntity.ok(participantsService.create(participant));
    }
}
