package org.enset.keynoteservice.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "keynotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Keynote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;

    private String firstName;

    private String email;

    private String functionTitle;
}

