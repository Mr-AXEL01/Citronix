package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.field.CreateFieldDTO;
import net.axel.citronix.domain.dtos.field.FieldResponseDTO;
import net.axel.citronix.domain.dtos.field.UpdateFieldDTO;
import net.axel.citronix.domain.entities.Field;

import java.util.List;

public interface FieldService {

    FieldResponseDTO create(CreateFieldDTO dto);

    FieldResponseDTO update(Long id, UpdateFieldDTO dto);

    FieldResponseDTO findById(Long id);

    Field findEntityById(Long id);

    List<FieldResponseDTO> findAll(int page, int size);

    void delete(Long id);
}
