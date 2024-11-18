package net.axel.citronix.mapper;

import net.axel.citronix.domain.dtos.FarmRequestDTO;
import net.axel.citronix.domain.dtos.FarmResponseDTO;
import net.axel.citronix.domain.entities.Farm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper extends BaseMapper<Farm, FarmRequestDTO, FarmResponseDTO>{
}