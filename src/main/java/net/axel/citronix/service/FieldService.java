package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.CreateFieldDTO;
import net.axel.citronix.domain.dtos.FieldResponseDTO;

public interface FieldService {

    FieldResponseDTO create(CreateFieldDTO dto);
}
