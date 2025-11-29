package org.enset.conferenceservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ReviewRequestDTO {

    private Date date;
    private String text;
    private int stars;
    private Long conferenceId; // pour savoir à quelle conférence rattacher la review
}

