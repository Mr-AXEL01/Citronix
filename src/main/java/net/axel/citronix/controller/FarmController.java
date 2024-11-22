package net.axel.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.farm.CreateFarmDTO;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.farm.UpdateFarmDTO;
import net.axel.citronix.service.FarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FarmController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class FarmController {
    public final static String CONTROLLER_PATH = "/api/v1/farms";

    private final FarmService service;
    
    @GetMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> findById(@PathVariable("id") Long id) {
        FarmResponseDTO farm = service.findById(id);
        return new ResponseEntity<>(farm, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FarmResponseDTO>> findAll() {
        List<FarmResponseDTO> farms = service.findAll();
        return ResponseEntity.ok(farms);
    }

    @PostMapping
    public ResponseEntity<FarmResponseDTO> create(@RequestBody @Valid CreateFarmDTO dto) {
        FarmResponseDTO farm = service.create(dto);
        return new ResponseEntity<>(farm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateFarmDTO dto) {
        FarmResponseDTO updatedFarm = service.update(id, dto);
        return ResponseEntity.ok(updatedFarm);
    }
}
