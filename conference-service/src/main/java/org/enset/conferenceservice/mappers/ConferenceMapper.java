package org.enset.conferenceservice.mappers;



import org.enset.conferenceservice.dtos.ConferenceRequestDTO;
import org.enset.conferenceservice.dtos.ConferenceResponseDTO;
import org.enset.conferenceservice.dtos.ReviewResponseDTO;
import org.enset.conferenceservice.entities.Conference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConferenceMapper {

    public static Conference toEntity(ConferenceRequestDTO dto) {
        if (dto == null) return null;

        Conference c = new Conference();
        c.setTitle(dto.getTitle());
        c.setType(dto.getType());
        c.setDate(dto.getDate());
        c.setDuration(dto.getDuration());
        c.setNumberOfRegistrations(dto.getNumberOfRegistrations());
        c.setScore(dto.getScore());
        c.setKeynoteId(dto.getKeynoteId());
        return c;
    }

    public static ConferenceResponseDTO toResponseDTO(Conference entity) {
        if (entity == null) return null;

        ConferenceResponseDTO dto = new ConferenceResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setType(entity.getType());
        dto.setDate(entity.getDate());
        dto.setDuration(entity.getDuration());
        dto.setNumberOfRegistrations(entity.getNumberOfRegistrations());
        dto.setScore(entity.getScore());
        dto.setKeynoteId(entity.getKeynoteId());

        if (entity.getReviews() != null) {
            List<ReviewResponseDTO> reviewDTOs = entity.getReviews()
                    .stream()
                    .map(ReviewMapper::toResponseDTO)
                    .collect(Collectors.toList());
            dto.setReviews(reviewDTOs);
        }

        return dto;
    }
}

