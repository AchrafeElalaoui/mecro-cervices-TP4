package org.enset.keynoteservice.services;


import org.enset.keynoteservice.dto.KeynoteRequestDTO;
import org.enset.keynoteservice.dto.KeynoteResponseDTO;

import java.util.List;

public interface KeynoteService {

    KeynoteResponseDTO createKeynote(KeynoteRequestDTO requestDTO);

    List<KeynoteResponseDTO> getAllKeynotes();

    KeynoteResponseDTO getKeynoteById(Long id);

    KeynoteResponseDTO updateKeynote(Long id, KeynoteRequestDTO requestDTO);

    void deleteKeynote(Long id);
}