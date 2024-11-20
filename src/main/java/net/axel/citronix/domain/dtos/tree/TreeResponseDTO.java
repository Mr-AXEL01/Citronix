package net.axel.citronix.domain.dtos.tree;

import jakarta.validation.constraints.NotNull;
import net.axel.citronix.domain.dtos.field.EmbeddedFieldDTO;
import net.axel.citronix.domain.dtos.harvestDetail.EmbeddedHarvestDetail;

import java.time.LocalDate;
import java.util.List;

public record TreeResponseDTO(
        @NotNull Long id,
        @NotNull LocalDate plantingDate,
        @NotNull Integer age,
        @NotNull Double productivity,
        @NotNull EmbeddedFieldDTO field,
        List<EmbeddedHarvestDetail> harvestDetails
) {
}
