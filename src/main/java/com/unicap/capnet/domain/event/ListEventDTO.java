package com.unicap.capnet.domain.event;

import java.time.LocalDateTime;

public record ListEventDTO(
        String title,
        String description,
        LocalDateTime eventDate
) {
    public ListEventDTO(Event event){
        this(
                event.getTitle(),
                event.getDescription(),
                event.getEventDate()
        );
    }
}
