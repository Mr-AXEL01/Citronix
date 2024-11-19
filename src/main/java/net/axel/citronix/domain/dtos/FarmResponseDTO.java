package net.axel.citronix.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FarmResponseDTO(

        @NotNull Long id,

        @NotBlank String name,

        @NotBlank String location,

        @NotNull Double size,

        List<EmbeddedFieldDTO> fields
) {
}
