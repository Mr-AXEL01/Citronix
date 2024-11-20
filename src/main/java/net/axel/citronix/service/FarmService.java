package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.farm.CreateFarmDTO;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.farm.UpdateFarmDTO;

public interface FarmService {

    FarmResponseDTO findById(Long id);

    FarmResponseDTO create(CreateFarmDTO dto);

    FarmResponseDTO update(Long id, UpdateFarmDTO dto);
}
