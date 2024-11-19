package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.CreateFarmDTO;
import net.axel.citronix.domain.dtos.FarmResponseDTO;
import net.axel.citronix.domain.dtos.UpdateFarmDTO;

public interface FarmService {

    FarmResponseDTO findById(Long id);

    FarmResponseDTO create(CreateFarmDTO dto);

    FarmResponseDTO update(Long id, UpdateFarmDTO dto);
}
