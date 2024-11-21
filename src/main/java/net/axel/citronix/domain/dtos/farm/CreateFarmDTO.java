package net.axel.citronix.domain.dtos.farm;

import jakarta.validation.constraints.*;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.validation.IsUnique;

import java.time.LocalDate;

public record CreateFarmDTO(

        @IsUnique(entityClass = Farm.class, fieldName = "name", message = "This name is already in use")
        @NotBlank String name,

        @NotBlank String location,

        @DecimalMin(value = "0.2", inclusive = true, message = "Farm size must be at least 0.2 hectares")
        @NotNull Double size,

        @PastOrPresent(message = "Creation date must be in the past or today")
        LocalDate creationDate
) {
}
