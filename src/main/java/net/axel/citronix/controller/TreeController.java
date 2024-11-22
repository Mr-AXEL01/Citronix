package net.axel.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.tree.CreateTreeDTO;
import net.axel.citronix.domain.dtos.tree.TreeResponseDTO;
import net.axel.citronix.service.TreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TreeController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class TreeController {

    public final static String CONTROLLER_PATH = "/api/v1/trees";

    private final TreeService service;

    @GetMapping("/{id}")
    public ResponseEntity<TreeResponseDTO> findById(@PathVariable("id") Long id) {
        TreeResponseDTO tree = service.findById(id);
        return new ResponseEntity<>(tree, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TreeResponseDTO> create(@RequestBody @Valid CreateTreeDTO dto) {
        TreeResponseDTO tree = service.create(dto);
        return new ResponseEntity<>(tree, HttpStatus.CREATED);
    }
}
