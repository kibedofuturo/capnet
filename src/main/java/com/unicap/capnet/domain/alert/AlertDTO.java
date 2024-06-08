package com.unicap.capnet.domain.alert;

import java.time.LocalDateTime;

public record AlertDTO(
        String title,
        String description,
        LocalDateTime date
) {

}
