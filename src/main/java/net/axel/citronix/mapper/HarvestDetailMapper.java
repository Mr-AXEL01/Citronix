package net.axel.citronix.mapper;

import net.axel.citronix.domain.dtos.harvestDetail.HarvestDetailRequestDTO;
import net.axel.citronix.domain.dtos.harvestDetail.HarvestDetailResponseDTO;
import net.axel.citronix.domain.entities.HarvestDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper extends BaseMapper<HarvestDetail, HarvestDetailRequestDTO, HarvestDetailResponseDTO> {
}
