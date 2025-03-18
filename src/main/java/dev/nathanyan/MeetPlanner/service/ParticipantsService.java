package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.repository.ParticipantsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ParticipantsService {
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

    public Participant create(Participant participant) {
        return participantsRepository.save(participant);
    }
}
