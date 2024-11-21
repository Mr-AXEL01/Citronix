package net.axel.citronix.mapper;

import net.axel.citronix.domain.dtos.harvest.CreateHarvestDTO;
import net.axel.citronix.domain.dtos.harvest.HarvestResponseDTO;
import net.axel.citronix.domain.entities.Harvest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestMapper extends BaseMapper<Harvest, CreateHarvestDTO, HarvestResponseDTO> {
}
