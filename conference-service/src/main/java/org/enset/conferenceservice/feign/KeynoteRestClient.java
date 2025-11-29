package org.enset.conferenceservice.feign;

import org.enset.conferenceservice.dtos.KeynoteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "keynote-service")
public interface KeynoteRestClient {

    @GetMapping("/api/keynotes/{id}")
    KeynoteResponseDTO getKeynoteById(@PathVariable Long id);

    @GetMapping("/api/keynotes")
    List<KeynoteResponseDTO> getAllKeynotes();
}

