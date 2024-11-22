package net.axel.citronix.domain.dtos.tree;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import net.axel.citronix.domain.entities.Field;
import net.axel.citronix.validation.Exists;

import java.time.LocalDate;

public record UpdateTreeDTO(

        @PastOrPresent(message = "Planting date must be in the past or today.")
        LocalDate plantingDate,

        @Exists(entityClass = Field.class, fieldName = "id", message = "Field does not exists.")
        Long fieldId
) {
}
