package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public @ResponseBody String create(@RequestBody String title, @RequestBody String description, @RequestBody LocalDate dateTime, @RequestBody String location, @RequestBody Integer duration) {
        return meetingService.create(title, description, dateTime, location, duration);
    }
}
