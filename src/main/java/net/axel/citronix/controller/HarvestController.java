package net.axel.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.harvest.CreateHarvestDTO;
import net.axel.citronix.domain.dtos.harvest.HarvestResponseDTO;
import net.axel.citronix.service.HarvestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(HarvestController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class HarvestController {

    public final static String CONTROLLER_PATH = "/api/v1/harvests";

    private final HarvestService service;

    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> findById(@PathVariable("id") Long id) {
        HarvestResponseDTO harvest = service.findById(id);
        return ResponseEntity.ok(harvest);
    }

    @GetMapping
    public ResponseEntity<List<HarvestResponseDTO>> findAll() {
        List<HarvestResponseDTO> harvests = service.findAll();
        return ResponseEntity.ok(harvests);
    }
    
    @PostMapping
    public ResponseEntity<HarvestResponseDTO> create(@RequestBody @Valid CreateHarvestDTO dto) {
        HarvestResponseDTO harvest = service.create(dto);
        return new ResponseEntity<>(harvest, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
