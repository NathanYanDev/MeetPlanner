package dev.nathanyan.MeetPlanner.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class PasswordEncoderUtil {
    private PasswordEncoderUtil(){}

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static String encrypt(String rawPassword) {
        return ENCODER.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}
