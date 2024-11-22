package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.farm.CreateFarmDTO;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.farm.UpdateFarmDTO;

import java.util.List;

public interface FarmService {

    FarmResponseDTO findById(Long id);

    List<FarmResponseDTO> findAll();

    FarmResponseDTO create(CreateFarmDTO dto);

    FarmResponseDTO update(Long id, UpdateFarmDTO dto);

    void delete(Long id);
}
