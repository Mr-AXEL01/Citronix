package net.axel.citronix.domain.dtos.field;

import jakarta.validation.constraints.NotNull;
import net.axel.citronix.domain.dtos.farm.EmbeddedFarmDTO;

public record FieldResponseDTO(

        @NotNull Long id,

        @NotNull Double area,

        @NotNull EmbeddedFarmDTO farm
) {
}
