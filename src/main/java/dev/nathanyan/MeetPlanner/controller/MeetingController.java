package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.service.MeetingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public List<Meeting> getAll() {
        return meetingService.getAll();
    }

    @PostMapping("/create")
    public @ResponseBody String create(@RequestBody Meeting meeting) {
        return meetingService.create(meeting);
    }
}
