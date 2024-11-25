package net.axel.citronix.domain.dtos.farm;

import java.time.LocalDate;

public record FarmSearchDTO(
        String name,
        String location,
        Double minSize,
        Double maxSize,
        LocalDate createdAfter,
        LocalDate createdBefore
) {
}
