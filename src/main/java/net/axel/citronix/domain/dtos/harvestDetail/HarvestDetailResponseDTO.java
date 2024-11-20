package net.axel.citronix.domain.dtos.harvestDetail;

import jakarta.validation.constraints.NotNull;
import net.axel.citronix.domain.dtos.harvest.EmbeddedHarvestDTO;
import net.axel.citronix.domain.dtos.tree.EmbeddedTreeDTO;
import net.axel.citronix.domain.embeddeds.HarvestDetailKey;

public record HarvestDetailResponseDTO(
        @NotNull HarvestDetailKey id,
        @NotNull Double quantity,
        @NotNull EmbeddedTreeDTO tree,
        @NotNull EmbeddedHarvestDTO harvest
) {
}
