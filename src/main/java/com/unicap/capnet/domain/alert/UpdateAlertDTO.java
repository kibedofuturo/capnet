package com.unicap.capnet.domain.alert;

import jakarta.validation.constraints.NotNull;

public record UpdateAlertDTO(
        @NotNull
        long id,
        String title,
        String description,
        String date
) {
}
