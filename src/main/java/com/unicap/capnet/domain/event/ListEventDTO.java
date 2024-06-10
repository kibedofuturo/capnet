package com.unicap.capnet.domain.event;

import java.time.LocalDateTime;

public record ListEventDTO(
        long id,
        String title,
        String description,
        LocalDateTime eventDate
) {
    public ListEventDTO(Event event){
        this(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getEventDate()
        );
    }
}
