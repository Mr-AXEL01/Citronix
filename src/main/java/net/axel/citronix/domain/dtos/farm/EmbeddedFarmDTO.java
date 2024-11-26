package net.axel.citronix.domain.dtos.farm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmbeddedFarmDTO(

        @NotNull Long id,

        @NotBlank String name,

        @NotBlank String location,

        @NotNull Double size,

        @NotNull LocalDate creationDate
) {
}
