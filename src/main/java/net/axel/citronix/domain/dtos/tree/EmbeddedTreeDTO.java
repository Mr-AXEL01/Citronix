package net.axel.citronix.domain.dtos.tree;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmbeddedTreeDTO(
        @NotNull Long id,
        @NotNull LocalDate plantingDate,
        @NotNull Integer age,
        @NotNull Double productivity
) {
}
