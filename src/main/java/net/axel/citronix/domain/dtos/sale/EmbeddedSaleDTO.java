package net.axel.citronix.domain.dtos.sale;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmbeddedSaleDTO(

        @NotNull Long id,

        @NotNull LocalDate date,

        @NotNull Double unitPrice,

        @NotBlank String client
) {
}
