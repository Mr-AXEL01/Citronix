package net.axel.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.harvest.CreateHarvestDTO;
import net.axel.citronix.domain.dtos.harvest.HarvestResponseDTO;
import net.axel.citronix.service.HarvestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HarvestController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class HarvestController {

    public final static String CONTROLLER_PATH = "/api/v1/harvests";

    private final HarvestService service;
    
    @PostMapping
    public ResponseEntity<HarvestResponseDTO> create(@RequestBody @Valid CreateHarvestDTO dto) {
        HarvestResponseDTO harvest = service.create(dto);
        return new ResponseEntity<>(harvest, HttpStatus.CREATED);
    }
}
