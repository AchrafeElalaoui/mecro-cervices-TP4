package org.enset.conferenceservice.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @Column(length = 2000)
    private String text;

    private int stars; // 1 à 5

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
}

