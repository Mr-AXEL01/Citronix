package net.axel.citronix.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ErrorResponse(
        int statusCode,
        LocalDateTime timestamp,
        String message,
        String description
) {
}
