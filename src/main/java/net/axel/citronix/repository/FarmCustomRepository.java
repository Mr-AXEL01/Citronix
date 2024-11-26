package net.axel.citronix.repository;

import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.farm.FarmSearchDTO;

import java.util.List;

public interface FarmCustomRepository {

    List<FarmResponseDTO> searchFarms(FarmSearchDTO criteria);
}
