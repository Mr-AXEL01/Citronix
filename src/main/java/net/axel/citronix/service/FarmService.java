package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.FarmRequestDTO;
import net.axel.citronix.domain.dtos.FarmResponseDTO;

public interface FarmService {

    FarmResponseDTO create(FarmRequestDTO dto);

    FarmResponseDTO update(Long id, FarmRequestDTO dto);
}
