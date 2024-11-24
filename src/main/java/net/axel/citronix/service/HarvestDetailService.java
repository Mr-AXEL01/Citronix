package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.harvestDetail.CreateHarvestDetailDTO;
import net.axel.citronix.domain.dtos.harvestDetail.HarvestDetailResponseDTO;

public interface HarvestDetailService {

    HarvestDetailResponseDTO update(CreateHarvestDetailDTO dto);
}
