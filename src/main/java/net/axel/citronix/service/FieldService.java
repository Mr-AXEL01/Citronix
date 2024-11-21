package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.field.CreateFieldDTO;
import net.axel.citronix.domain.dtos.field.FieldResponseDTO;
import net.axel.citronix.domain.dtos.field.UpdateFieldDTO;

public interface FieldService {

    FieldResponseDTO create(CreateFieldDTO dto);

    FieldResponseDTO update(Long id, UpdateFieldDTO dto);

    FieldResponseDTO findById(Long id);
}
