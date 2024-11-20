package net.axel.citronix.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record FieldResponseDTO(

        @NotNull Long id,

        @NotNull Double area,

        @NotNull EmbeddedFarmDTO farm
) {
}
