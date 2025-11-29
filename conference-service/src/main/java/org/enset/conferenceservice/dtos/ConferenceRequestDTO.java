package org.enset.conferenceservice.dtos;


import lombok.Getter;
import lombok.Setter;
import org.enset.conferenceservice.entities.ConferenceType;


import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ConferenceRequestDTO {

    private String title;
    private ConferenceType type;
    private Long keynoteId;
    private Date date;
    private int duration;
    private int numberOfRegistrations;
    private double score;
}

