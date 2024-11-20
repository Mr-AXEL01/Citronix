package net.axel.citronix.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record EmbeddedFieldDTO(

        @NotNull Long id,

        @NotNull Double area
) {
}
