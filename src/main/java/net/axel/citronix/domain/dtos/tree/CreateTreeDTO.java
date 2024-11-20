package net.axel.citronix.domain.dtos.tree;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateTreeDTO(
        LocalDate plantingDate,
        @NotNull Integer age,
        @NotNull Long fieldId
) {
}
