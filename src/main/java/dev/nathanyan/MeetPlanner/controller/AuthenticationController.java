package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.dto.ParticipantDTO;
import dev.nathanyan.MeetPlanner.dto.request.authentication.AuthenticationRequest;
import dev.nathanyan.MeetPlanner.dto.request.participant.CreateParticipant;
import dev.nathanyan.MeetPlanner.handler.ResponseHandler;
import dev.nathanyan.MeetPlanner.service.JWTService;
import dev.nathanyan.MeetPlanner.configuration.security.PasswordEncoderUtil;
import dev.nathanyan.MeetPlanner.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JWTService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JWTService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Validated AuthenticationRequest body) {
        UserDetails participant = authenticationService.loadUserByUsername(body.email());

        if(participant == null) return ResponseHandler.generateResponse("Invalid username or password", HttpStatus.UNAUTHORIZED, Collections.emptyList());

        if(PasswordEncoderUtil.matches(body.password(), participant.getPassword())) {
            String token = jwtService.generateToken(body.email());
            return ResponseHandler.generateResponse("Authentication successful", HttpStatus.OK, token);
        }

        return ResponseHandler.generateResponse("Invalid username or password", HttpStatus.UNAUTHORIZED, Collections.emptyList());
    }

    @PostMapping("/register")
    public ResponseEntity<Object> create(@RequestBody @Validated CreateParticipant body) {
        ParticipantDTO newParticipant = authenticationService.register(body);

        try {
            return ResponseHandler.generateResponse("The participant was created successfully", HttpStatus.CREATED, newParticipant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error, check the fields and try again");
        }
    }
}
