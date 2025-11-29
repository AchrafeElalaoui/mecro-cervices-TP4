package org.enset.conferenceservice.dtos;


import lombok.Getter;
import lombok.Setter;
import org.enset.conferenceservice.entities.ConferenceType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ConferenceResponseDTO {

    private Long id;
    private String title;
    private ConferenceType type;
    private Long keynoteId;
    private Date date;
    private int duration;
    private int numberOfRegistrations;
    private double score;
    private KeynoteResponseDTO keynote;

    // Optionnel : liste des reviews associées
    private List<ReviewResponseDTO> reviews;
}

