package com.unicap.capnet.domain.publication;

import java.time.LocalDateTime;

public record PublicationDTO(
        long id,
        String text,
        LocalDateTime publicationDate
) {
}
