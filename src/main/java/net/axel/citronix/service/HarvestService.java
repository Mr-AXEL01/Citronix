package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.harvest.CreateHarvestDTO;
import net.axel.citronix.domain.dtos.harvest.HarvestResponseDTO;

public interface HarvestService {

    HarvestResponseDTO create(CreateHarvestDTO dto);
}
