package com.unicap.capnet.domain.alert;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateAlertDTO(
        @NotNull
        long id,
        String title,
        String description,
        LocalDateTime date
) {
}
