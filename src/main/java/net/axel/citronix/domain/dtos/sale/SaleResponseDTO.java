package net.axel.citronix.domain.dtos.sale;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.axel.citronix.domain.dtos.harvest.EmbeddedHarvestDTO;

import java.time.LocalDate;

public record SaleResponseDTO(

        @NotNull Long id,

        @NotNull LocalDate date,

        @NotNull Double unitPrice,

        @NotBlank String client,

        EmbeddedHarvestDTO harvest
) {
}
