package dev.nathanyan.MeetPlanner.controller;

import dev.nathanyan.MeetPlanner.model.Meeting;
import dev.nathanyan.MeetPlanner.service.MailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;

import java.util.Locale;

@Controller
public class MailSenderController {

    private final MailSenderService mailSenderService;

    MailSenderController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    public void sendMail(String to, Meeting meeting) {
        try {
            mailSenderService.sendTemplateMail(to, meeting, "invited-meeting", Locale.ENGLISH);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
