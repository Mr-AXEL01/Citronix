package net.axel.citronix.domain.dtos.field;

import jakarta.validation.constraints.DecimalMin;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.validation.Exists;

public record UpdateFieldDTO(

        @DecimalMin(value = "0.1", message = "Area must be at least 0.1 hectares.")
        Double area,

        @Exists(entityClass = Farm.class, fieldName = "id", message = "Farm does not exists.")
        Long farmId
) {
}
