package com.unicap.capnet.domain.alert;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateAlertDTO(
        String title,
        String description,
        LocalDateTime date
) {
}
