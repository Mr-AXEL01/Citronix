package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.harvestDetail.HarvestDetailRequestDTO;
import net.axel.citronix.domain.dtos.harvestDetail.HarvestDetailResponseDTO;

public interface HarvestDetailService {

    HarvestDetailResponseDTO update(HarvestDetailRequestDTO dto);
}
