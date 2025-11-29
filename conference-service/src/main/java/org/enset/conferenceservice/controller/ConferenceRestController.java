package org.enset.conferenceservice.controller;



import lombok.RequiredArgsConstructor;

import org.enset.conferenceservice.dtos.ConferenceRequestDTO;
import org.enset.conferenceservice.dtos.ConferenceResponseDTO;
import org.enset.conferenceservice.dtos.ReviewRequestDTO;
import org.enset.conferenceservice.dtos.ReviewResponseDTO;
import org.enset.conferenceservice.services.ConferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conferences")
@RequiredArgsConstructor
public class ConferenceRestController {

    private final ConferenceService conferenceService;

    // CREATE
    @PostMapping
    public ResponseEntity<ConferenceResponseDTO> createConference(
            @RequestBody ConferenceRequestDTO requestDTO
    ) {
        ConferenceResponseDTO response = conferenceService.createConference(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public List<ConferenceResponseDTO> getAllConferences() {
        return conferenceService.getAllConferences();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ConferenceResponseDTO getConferenceById(@PathVariable Long id) {
        return conferenceService.getConferenceById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ConferenceResponseDTO updateConference(
            @PathVariable Long id,
            @RequestBody ConferenceRequestDTO requestDTO
    ) {
        return conferenceService.updateConference(id, requestDTO);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
    }

    // ADD review
    @PostMapping("/{conferenceId}/reviews")
    public ResponseEntity<ReviewResponseDTO> addReview(
            @PathVariable Long conferenceId,
            @RequestBody ReviewRequestDTO reviewDTO
    ) {
        ReviewResponseDTO response = conferenceService.addReviewToConference(conferenceId, reviewDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // LIST reviews
    @GetMapping("/{conferenceId}/reviews")
    public List<ReviewResponseDTO> getReviewsByConference(@PathVariable Long conferenceId) {
        return conferenceService.getReviewsByConference(conferenceId);
    }
}

