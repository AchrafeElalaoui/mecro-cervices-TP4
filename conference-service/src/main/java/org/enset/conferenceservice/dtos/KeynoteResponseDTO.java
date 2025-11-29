package org.enset.conferenceservice.dtos;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeynoteResponseDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String functionTitle;
}

