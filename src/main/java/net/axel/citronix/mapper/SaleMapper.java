package net.axel.citronix.mapper;

import net.axel.citronix.domain.dtos.sale.CreateSaleDTO;
import net.axel.citronix.domain.dtos.sale.SaleResponseDTO;
import net.axel.citronix.domain.entities.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper extends BaseMapper<Sale, CreateSaleDTO, SaleResponseDTO> {

    @Mapping(target = "income", expression = "java(sale.getIncome())")
    SaleResponseDTO toResponseDto(Sale sale);
}
