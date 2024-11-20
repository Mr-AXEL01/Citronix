package net.axel.citronix.mapper;

import net.axel.citronix.domain.dtos.field.CreateFieldDTO;
import net.axel.citronix.domain.dtos.field.FieldResponseDTO;
import net.axel.citronix.domain.entities.Field;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldMapper extends BaseMapper<Field, CreateFieldDTO, FieldResponseDTO> {
}
