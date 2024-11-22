package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.harvest.CreateHarvestDTO;
import net.axel.citronix.domain.dtos.harvest.HarvestResponseDTO;

import java.util.List;

public interface HarvestService {

    HarvestResponseDTO findById(Long id);

    List<HarvestResponseDTO> findAll();

    HarvestResponseDTO create(CreateHarvestDTO dto);

    HarvestResponseDTO update(Long id);

    void delete(Long id);
}
