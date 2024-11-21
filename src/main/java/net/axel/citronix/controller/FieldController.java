package net.axel.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.field.CreateFieldDTO;
import net.axel.citronix.domain.dtos.field.FieldResponseDTO;
import net.axel.citronix.domain.dtos.field.UpdateFieldDTO;
import net.axel.citronix.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(FieldController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class FieldController {

    public final static String CONTROLLER_PATH = "/api/v1/fields";

    private final FieldService service;

    @PostMapping
    public ResponseEntity<FieldResponseDTO> create(@RequestBody @Valid CreateFieldDTO dto) {
        FieldResponseDTO field = service.create(dto);
        return new ResponseEntity<>(field, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> findById(@PathVariable("id") Long id) {
        FieldResponseDTO farm = service.findById(id);
        return ResponseEntity.ok(farm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> update(@PathVariable("id") Long id,
                                                   @RequestBody @Valid UpdateFieldDTO dto) {
        FieldResponseDTO updatedField = service.update(id, dto);
        return ResponseEntity.ok(updatedField);
    }
}
