package net.axel.citronix.mapper;

import net.axel.citronix.domain.dtos.farm.CreateFarmDTO;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.entities.Farm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper extends BaseMapper<Farm, CreateFarmDTO, FarmResponseDTO>{
}
