package net.axel.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.FarmRequestDTO;
import net.axel.citronix.domain.dtos.FarmResponseDTO;
import net.axel.citronix.service.FarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FarmController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class FarmController {
    public final static String CONTROLLER_PATH = "/api/v1/farms";

    private final FarmService service;

    @PostMapping
    public ResponseEntity<FarmResponseDTO> create(@RequestBody @Valid FarmRequestDTO dto) {
        FarmResponseDTO farm = service.create(dto);
        return new ResponseEntity<>(farm, HttpStatus.CREATED);
    }
}