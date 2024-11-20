package net.axel.citronix.domain.dtos.sale;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateSaleDTO(

        @NotNull LocalDate date,

        @NotNull Double unitePrice,

        @NotBlank String client
) {
}
