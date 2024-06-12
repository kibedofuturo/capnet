package com.unicap.capnet.domain.event;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateEventDTO(
        String description,
        LocalDateTime eventDate
) {
}
