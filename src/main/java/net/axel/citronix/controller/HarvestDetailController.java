package net.axel.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.harvestDetail.HarvestDetailRequestDTO;
import net.axel.citronix.domain.dtos.harvestDetail.HarvestDetailResponseDTO;
import net.axel.citronix.service.HarvestDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HarvestDetailController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class HarvestDetailController {

    public final static String CONTROLLER_PATH = "/api/v1/harvest-details";

    private final HarvestDetailService service;

    @PutMapping
    public ResponseEntity<HarvestDetailResponseDTO> update(@RequestBody @Valid HarvestDetailRequestDTO dto) {
        HarvestDetailResponseDTO updatedHarvestDetail = service.update(dto);
        return ResponseEntity.ok(updatedHarvestDetail);
    }
}
