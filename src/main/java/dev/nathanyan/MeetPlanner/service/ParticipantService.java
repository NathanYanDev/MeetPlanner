package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.dto.ParticipantDTO;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<ParticipantDTO> listAll() {
        return participantRepository.findAll().stream().map(ParticipantDTO::new).toList();
    }

    public Optional<Participant> getById(String id) {
        return participantRepository.findById(id);
    }

    public Participant getByEmail(String email) {
        return participantRepository.getByEmail(email);
    }
}
