package org.enset.conferenceservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ReviewResponseDTO {

    private Long id;
    private Date date;
    private String text;
    private int stars;
}

