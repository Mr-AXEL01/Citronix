package net.axel.citronix.domain.dtos.farm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.axel.citronix.domain.dtos.field.EmbeddedFieldDTO;

import java.util.List;

public record FarmResponseDTO(

        @NotNull Long id,

        @NotBlank String name,

        @NotBlank String location,

        @NotNull Double size,

        List<EmbeddedFieldDTO> fields
) {
}
