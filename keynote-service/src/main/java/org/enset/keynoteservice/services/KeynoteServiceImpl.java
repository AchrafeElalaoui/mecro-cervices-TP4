package org.enset.keynoteservice.services;


import lombok.RequiredArgsConstructor;

import org.enset.keynoteservice.dto.KeynoteRequestDTO;
import org.enset.keynoteservice.dto.KeynoteResponseDTO;
import org.enset.keynoteservice.entities.Keynote;
import org.enset.keynoteservice.mappers.KeynoteMapper;
import org.enset.keynoteservice.repository.KeynoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeynoteServiceImpl implements KeynoteService {

    private final KeynoteRepository keynoteRepository;

    @Override
    public KeynoteResponseDTO createKeynote(KeynoteRequestDTO requestDTO) {
        Keynote keynote = KeynoteMapper.toEntity(requestDTO);
        Keynote saved = keynoteRepository.save(keynote);
        return KeynoteMapper.toResponseDTO(saved);
    }

    @Override
    public List<KeynoteResponseDTO> getAllKeynotes() {
        return keynoteRepository.findAll()
                .stream()
                .map(KeynoteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KeynoteResponseDTO getKeynoteById(Long id) {
        Keynote keynote = keynoteRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Keynote not found")
                );
        return KeynoteMapper.toResponseDTO(keynote);
    }

    @Override
    public KeynoteResponseDTO updateKeynote(Long id, KeynoteRequestDTO requestDTO) {
        Keynote keynote = keynoteRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Keynote not found")
                );

        // Mise à jour des champs
        keynote.setLastName(requestDTO.getLastName());
        keynote.setFirstName(requestDTO.getFirstName());
        keynote.setEmail(requestDTO.getEmail());
        keynote.setFunctionTitle(requestDTO.getFunctionTitle());

        Keynote updated = keynoteRepository.save(keynote);
        return KeynoteMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteKeynote(Long id) {
        if (!keynoteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Keynote not found");
        }
        keynoteRepository.deleteById(id);
    }
}
