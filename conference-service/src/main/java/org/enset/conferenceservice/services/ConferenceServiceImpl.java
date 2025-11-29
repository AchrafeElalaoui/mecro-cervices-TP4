package org.enset.conferenceservice.services;


import lombok.RequiredArgsConstructor;

import org.enset.conferenceservice.dtos.*;
import org.enset.conferenceservice.entities.Conference;
import org.enset.conferenceservice.entities.Review;
import org.enset.conferenceservice.feign.KeynoteRestClient;
import org.enset.conferenceservice.mappers.ConferenceMapper;
import org.enset.conferenceservice.mappers.ReviewMapper;
import org.enset.conferenceservice.repository.ConferenceRepository;
import org.enset.conferenceservice.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final ReviewRepository reviewRepository;
    private final KeynoteRestClient keynoteRestClient;

    @Override
    public ConferenceResponseDTO createConference(ConferenceRequestDTO requestDTO) {
        Conference conference = ConferenceMapper.toEntity(requestDTO);
        Conference saved = conferenceRepository.save(conference);
        return enrichWithKeynote(ConferenceMapper.toResponseDTO(saved));
    }

    @Override
    public List<ConferenceResponseDTO> getAllConferences() {
        return conferenceRepository.findAll()
                .stream()
                .map(ConferenceMapper::toResponseDTO)
                .map(this::enrichWithKeynote)
                .collect(Collectors.toList());
    }

    @Override
    public ConferenceResponseDTO getConferenceById(Long id) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found"));

        ConferenceResponseDTO dto = ConferenceMapper.toResponseDTO(conference);
        return enrichWithKeynote(dto);
    }

    @Override
    public ConferenceResponseDTO updateConference(Long id, ConferenceRequestDTO requestDTO) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found"));

        conference.setTitle(requestDTO.getTitle());
        conference.setType(requestDTO.getType());
        conference.setKeynoteId(requestDTO.getKeynoteId());
        conference.setDate(requestDTO.getDate());
        conference.setDuration(requestDTO.getDuration());
        conference.setNumberOfRegistrations(requestDTO.getNumberOfRegistrations());
        conference.setScore(requestDTO.getScore());

        Conference updated = conferenceRepository.save(conference);
        return enrichWithKeynote(ConferenceMapper.toResponseDTO(updated));
    }

    @Override
    public void deleteConference(Long id) {
        if (!conferenceRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found");
        }
        conferenceRepository.deleteById(id);
    }

    @Override
    public ReviewResponseDTO addReviewToConference(Long conferenceId, ReviewRequestDTO reviewDTO) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found"));

        Review review = ReviewMapper.toEntity(reviewDTO);
        review.setConference(conference);
        Review saved = reviewRepository.save(review);

        return ReviewMapper.toResponseDTO(saved);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByConference(Long conferenceId) {
        if (!conferenceRepository.existsById(conferenceId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found");
        }

        return reviewRepository.findByConferenceId(conferenceId)
                .stream()
                .map(ReviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 🔗 enrichir la DTO avec les données du keynote (via OpenFeign)
    private ConferenceResponseDTO enrichWithKeynote(ConferenceResponseDTO dto) {
        if (dto.getKeynoteId() != null) {
            try {
                KeynoteResponseDTO keynote = keynoteRestClient.getKeynoteById(dto.getKeynoteId());
                dto.setKeynote(keynote);
            } catch (Exception e) {
                // keynote-service down ou keynote introuvable : on ignore
                dto.setKeynote(null);
            }
        }
        return dto;
    }
}



