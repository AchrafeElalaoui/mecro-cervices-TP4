package org.enset.conferenceservice.services;

import org.enset.conferenceservice.dtos.ConferenceRequestDTO;
import org.enset.conferenceservice.dtos.ConferenceResponseDTO;
import org.enset.conferenceservice.dtos.ReviewRequestDTO;
import org.enset.conferenceservice.dtos.ReviewResponseDTO;

import java.util.List;

public interface ConferenceService {

    ConferenceResponseDTO createConference(ConferenceRequestDTO requestDTO);

    List<ConferenceResponseDTO> getAllConferences();

    ConferenceResponseDTO getConferenceById(Long id);

    ConferenceResponseDTO updateConference(Long id, ConferenceRequestDTO requestDTO);

    void deleteConference(Long id);

    ReviewResponseDTO addReviewToConference(Long conferenceId, ReviewRequestDTO reviewDTO);

    List<ReviewResponseDTO> getReviewsByConference(Long conferenceId);
}
