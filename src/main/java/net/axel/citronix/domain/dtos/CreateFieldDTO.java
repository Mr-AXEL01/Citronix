package net.axel.citronix.domain.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.validation.Exists;

public record CreateFieldDTO(

        @DecimalMin(value = "0.1", message = "Area must be at least 0.1")
        @NotNull Double area,

        @Exists(entityClass = Farm.class, fieldName = "id", message = "Farm does not exists.")
        @NotNull Long farmId
) {
}
