package net.axel.citronix.domain.dtos.harvestDetail;

import jakarta.validation.constraints.NotNull;

public record HarvestDetailRequestDTO(
        @NotNull Long treeId,
        @NotNull Long harvestId,
        @NotNull Double quantity
) {
}
