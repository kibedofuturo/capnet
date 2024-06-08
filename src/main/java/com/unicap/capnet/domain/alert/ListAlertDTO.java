package com.unicap.capnet.domain.alert;

import java.time.LocalDateTime;

public record ListAlertDTO(
        String text,
        String description,
        LocalDateTime date,
        LocalDateTime alertDate
) {
    public ListAlertDTO(Alert alert) {
        this(
                alert.getTitle(),
                alert.getDescription(),
                alert.getDate(),
                alert.getAlertDate()
        );
    }
}
