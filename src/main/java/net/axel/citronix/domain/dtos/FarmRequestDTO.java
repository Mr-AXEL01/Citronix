package net.axel.citronix.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.validation.IsUnique;

public record FarmRequestDTO(

        @IsUnique(entityClass = Farm.class, fieldName = "name", message = "This name is already in use")
        @NotBlank String name,

        @NotBlank String location,

        @NotNull @Positive Double size
) {
}
