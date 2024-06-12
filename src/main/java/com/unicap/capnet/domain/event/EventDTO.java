package com.unicap.capnet.domain.event;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventDTO(
        String title,
        String description,
        LocalDateTime eventDate
) {
}
