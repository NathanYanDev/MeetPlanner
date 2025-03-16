package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.model.CreateMeetingRequest;
import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.service.MeetingService;
import dev.nathanyan.MeetPlanner.service.ParticipantsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
    public List<Meeting> getMeetings() {
        return meetingService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createMeeting(@RequestBody CreateMeetingRequest request) {
        Set<Participant> participants = participantsService.getParticipantsByEmail(request.getParticipantsEmail());

        Meeting meeting = new Meeting(null, request.getTitle(), request.getDescription(), request.getDateTime(), request.getLocation(), LocalDateTime.now(), request.getDuration(), participants);

        return ResponseEntity.ok(meetingService.create(meeting));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        meetingService.delete(id);
    }
}
