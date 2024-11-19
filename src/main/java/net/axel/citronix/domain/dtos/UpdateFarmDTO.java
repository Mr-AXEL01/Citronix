package net.axel.citronix.domain.dtos;

import jakarta.validation.constraints.*;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.validation.IsUnique;

import java.time.LocalDate;

public record UpdateFarmDTO(
        @IsUnique(entityClass = Farm.class, fieldName = "name", message = "Try using a new name")
        @Pattern(regexp = "\\S.*", message = "Name cannot be blank or contain only whitespace")
        String name,

        @Pattern(regexp = "\\S.*", message = "Location cannot be blank or contain only whitespace")
        String location,

        @Positive Double size,

        @PastOrPresent(message = "Creation date must be in the past or today")
        LocalDate creationDate
) {
}
