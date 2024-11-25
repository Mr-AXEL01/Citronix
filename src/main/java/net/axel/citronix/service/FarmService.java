package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.farm.CreateFarmDTO;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.farm.FarmSearchDTO;
import net.axel.citronix.domain.dtos.farm.UpdateFarmDTO;
import net.axel.citronix.domain.entities.Farm;

import java.util.List;

public interface FarmService {

    FarmResponseDTO findById(Long id);

    List<FarmResponseDTO> findAll(int page, int size);

    FarmResponseDTO create(CreateFarmDTO dto);

    FarmResponseDTO update(Long id, UpdateFarmDTO dto);

    void delete(Long id);

    List<FarmResponseDTO> searchFarms(FarmSearchDTO criteria);
}
