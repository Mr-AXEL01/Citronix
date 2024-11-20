package net.axel.citronix.domain.dtos.harvest;

import jakarta.validation.constraints.NotNull;
import net.axel.citronix.domain.enums.Season;

import java.time.LocalDate;

public record EmbeddedHarvestDTO(
        @NotNull Long id,
        @NotNull Season season,
        @NotNull LocalDate harvestDate,
        @NotNull Double totalQuantity
) {
}
