package dev.nathanyan.MeetPlanner.service;

import dev.nathanyan.MeetPlanner.dto.ParticipantDTO;
import dev.nathanyan.MeetPlanner.dto.request.participant.CreateParticipant;
import dev.nathanyan.MeetPlanner.model.Participant;
import dev.nathanyan.MeetPlanner.repository.ParticipantRepository;
import dev.nathanyan.MeetPlanner.configuration.security.PasswordEncoderUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final ParticipantRepository participantRepository;

    AuthenticationService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return participantRepository.findByEmail(username);
    }

    public ParticipantDTO register(CreateParticipant participant){
        if(participantRepository.findByEmail(participant.email()) != null) throw new IllegalArgumentException("Email already registered");

        String encryptedPassword = PasswordEncoderUtil.encrypt(participant.password());

        Participant newParticipant = new Participant();
        newParticipant.setName(participant.name());
        newParticipant.setEmail(participant.email());
        newParticipant.setPassword(encryptedPassword);
        newParticipant.setRole(participant.role());

        participantRepository.save(newParticipant);

        return new ParticipantDTO(newParticipant);
    }
}
