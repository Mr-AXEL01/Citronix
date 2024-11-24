package net.axel.citronix.domain.dtos.sale;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateSaleDTO(

        @NotNull LocalDate date,

        @Positive(message = "Can't sold for free.")
        @NotNull Double unitePrice,

        @NotBlank String client,

        @NotNull Long harvestId
) {
}
