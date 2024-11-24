package net.axel.citronix.domain.dtos.sale;

import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record UpdateSaleDTO(

        LocalDate date,

        @Positive(message = "Can't sold for free.")
        Double unitePrice,

        String client,

        Long harvestId
) {
}
