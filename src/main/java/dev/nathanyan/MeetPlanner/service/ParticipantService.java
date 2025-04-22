package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.dto.ParticipantDTO;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<ParticipantDTO> listAll() {
        return participantRepository.findAll().stream().map(ParticipantDTO::new).toList();
    }

    public Set<Participant> getParticipantsByEmail(Iterable<String> emails) {
            return participantRepository.findAllByEmail(emails);
    }
}
