package org.enset.keynoteservice.mappers;


import org.enset.keynoteservice.dto.KeynoteRequestDTO;
import org.enset.keynoteservice.dto.KeynoteResponseDTO;
import org.enset.keynoteservice.entities.Keynote;
import org.springframework.stereotype.Component;

@Component
public class KeynoteMapper {

    public static Keynote toEntity(KeynoteRequestDTO dto) {
        if (dto == null) return null;

        Keynote keynote = new Keynote();
        keynote.setLastName(dto.getLastName());
        keynote.setFirstName(dto.getFirstName());
        keynote.setEmail(dto.getEmail());
        keynote.setFunctionTitle(dto.getFunctionTitle());
        return keynote;
    }


    public static KeynoteResponseDTO toResponseDTO(Keynote entity) {
        if (entity == null) return null;

        KeynoteResponseDTO dto = new KeynoteResponseDTO();
        dto.setId(entity.getId());
        dto.setLastName(entity.getLastName());
        dto.setFirstName(entity.getFirstName());
        dto.setEmail(entity.getEmail());
        dto.setFunctionTitle(entity.getFunctionTitle());
        return dto;
    }
}
