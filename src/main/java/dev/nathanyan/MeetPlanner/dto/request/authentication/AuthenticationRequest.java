package dev.nathanyan.MeetPlanner.dto.request.authentication;

public record AuthenticationRequest(
        String email,
        String password
) {
}
