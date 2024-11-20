package net.axel.citronix.domain.dtos.harvestDetail;

import jakarta.validation.constraints.NotNull;
import net.axel.citronix.domain.embeddeds.HarvestDetailKey;

public record EmbeddedHarvestDetailDTO(
        @NotNull HarvestDetailKey id,
        @NotNull Double quantity
) {
}
