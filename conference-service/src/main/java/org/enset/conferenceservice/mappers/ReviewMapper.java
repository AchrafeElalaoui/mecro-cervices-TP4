package org.enset.conferenceservice.mappers;


import org.enset.conferenceservice.dtos.ReviewRequestDTO;
import org.enset.conferenceservice.dtos.ReviewResponseDTO;
import org.enset.conferenceservice.entities.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public static Review toEntity(ReviewRequestDTO dto) {
        if (dto == null) return null;

        Review review = new Review();
        review.setDate(dto.getDate());
        review.setText(dto.getText());
        review.setStars(dto.getStars());
        // conference sera set dans le service (avec Conference trouvée par id)
        return review;
    }

    public static ReviewResponseDTO toResponseDTO(Review entity) {
        if (entity == null) return null;

        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setText(entity.getText());
        dto.setStars(entity.getStars());
        return dto;
    }
}
