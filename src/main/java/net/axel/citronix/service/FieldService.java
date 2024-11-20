package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.field.CreateFieldDTO;
import net.axel.citronix.domain.dtos.field.FieldResponseDTO;

public interface FieldService {

    FieldResponseDTO create(CreateFieldDTO dto);
}
