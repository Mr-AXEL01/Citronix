package net.axel.citronix.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmbeddedFarmDTO(

        @NotNull Long id,

        @NotBlank String name,

        @NotBlank String location,

        @NotNull Double size
) {
}
