package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.repository.ParticipantsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class ParticipantsService {
    Logger logger = Logger.getLogger(getClass().getName());

    private final ParticipantsRepository participantsRepository;

    public ParticipantsService(ParticipantsRepository participantsRepository) {
        this.participantsRepository = participantsRepository;
    }

    public List<Participant> listAll() {
        return participantsRepository.findAll();
    }

    public Set<Participant> getParticipantsByEmail(Iterable<String> emails) {
            return participantsRepository.findAllByEmail(emails);
    }

    public String create(Participant participant) {
        try {
            participantsRepository.save(participant);
            return "Participant successfully added";
        } catch (Exception err) {
            logger.info(err.getMessage());
            return "The participant cannot be created, check the fields and try again";
        }
    }
}
