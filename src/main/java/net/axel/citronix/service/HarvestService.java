package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.harvest.CreateHarvestDTO;
import net.axel.citronix.domain.dtos.harvest.HarvestResponseDTO;
import net.axel.citronix.domain.dtos.harvest.UpdateHarvestDTO;

import java.util.List;

public interface HarvestService {

    HarvestResponseDTO findById(Long id);

    List<HarvestResponseDTO> findAll(int page, int size);

    HarvestResponseDTO create(CreateHarvestDTO dto);

    HarvestResponseDTO update(Long id, UpdateHarvestDTO dto);

    void delete(Long id);
}
