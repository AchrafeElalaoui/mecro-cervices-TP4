package org.enset.keynoteservice.controllers;



import lombok.RequiredArgsConstructor;

import org.enset.keynoteservice.dto.KeynoteRequestDTO;
import org.enset.keynoteservice.dto.KeynoteResponseDTO;
import org.enset.keynoteservice.services.KeynoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
@RequiredArgsConstructor
public class KeynoteRestController {

    private final KeynoteService keynoteService;

    @PostMapping
    public ResponseEntity<KeynoteResponseDTO> createKeynote(
            @RequestBody KeynoteRequestDTO requestDTO
    ) {
        KeynoteResponseDTO responseDTO = keynoteService.createKeynote(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public List<KeynoteResponseDTO> getAllKeynotes() {
        return keynoteService.getAllKeynotes();
    }

    @GetMapping("/{id}")
    public KeynoteResponseDTO getKeynoteById(@PathVariable Long id) {
        return keynoteService.getKeynoteById(id);
    }

    @PutMapping("/{id}")
    public KeynoteResponseDTO updateKeynote(
            @PathVariable Long id,
            @RequestBody KeynoteRequestDTO requestDTO
    ) {
        return keynoteService.updateKeynote(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKeynote(@PathVariable Long id) {
        keynoteService.deleteKeynote(id);
    }
}

