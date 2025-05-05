package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.dto.ParticipantDTO;
import dev.nathanyan.MeetPlanner.model.Meeting;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class MailSenderService {
    private final JavaMailSender mailSender;
    private final TemplateEngine htmlTemplateEngine;

    public MailSenderService(JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
        this.mailSender = mailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    public void sendTemplateMail(String to, Meeting meetingData, String templateName, Locale locale)
            throws MessagingException {

        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name()
        );

        List<ParticipantDTO> participants = meetingData.getParticipants().stream().map(meetingParticipant -> new ParticipantDTO(meetingParticipant.getParticipant())).toList();

        String meetingStart = meetingData.getDateTime().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"));
        String meetingEnd = meetingData.getDateTime().atZone(ZoneId.systemDefault()).plusMinutes(meetingData.getDuration()).format(DateTimeFormatter.ofPattern("HH:mm"));

        Context ctx = new Context(locale);
        ctx.setVariable("meetingTitle", meetingData.getTitle());
        ctx.setVariable("meetingDescription",
                meetingData.getDescription() != null ? meetingData.getDescription() : "");
        ctx.setVariable("meetingStart", meetingStart);
        ctx.setVariable("meetingEnd", meetingEnd);
        ctx.setVariable("meetingLocation", meetingData.getLocation());
        ctx.setVariable("meetingOrganizer", meetingData.getOrganizer());
        ctx.setVariable("participants", participants);

        final String htmlContent = htmlTemplateEngine.process(templateName, ctx);

        message.setTo(to);
        message.setSubject(meetingData.getTitle());
        message.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }
}
