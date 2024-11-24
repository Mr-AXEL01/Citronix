package net.axel.citronix.domain.dtos.harvest;

import java.time.LocalDate;

public record UpdateHarvestDTO(
        LocalDate harvestDate
) {
}
