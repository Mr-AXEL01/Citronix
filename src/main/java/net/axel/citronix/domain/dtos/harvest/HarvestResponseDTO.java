package net.axel.citronix.domain.dtos.harvest;

import jakarta.validation.constraints.NotNull;
import net.axel.citronix.domain.dtos.harvestDetail.EmbeddedHarvestDetailDTO;
import net.axel.citronix.domain.dtos.sale.EmbeddedSaleDTO;
import net.axel.citronix.domain.enums.Season;

import java.time.LocalDate;
import java.util.List;

public record HarvestResponseDTO(
        @NotNull Long id,
        @NotNull Season season,
        @NotNull LocalDate harvestDate,
        @NotNull Double totalQuantity,
        List<EmbeddedHarvestDetailDTO> harvestDetails,
        List<EmbeddedSaleDTO> sales
) {
}
