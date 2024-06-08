package com.unicap.capnet.domain.publication;

import java.time.LocalDateTime;

public record ListPublicationDTO(
        String text,
        LocalDateTime publicationDate
) {
    public ListPublicationDTO(Publication publication) {
        this(
                publication.getText(),
                publication.getPublicationDate()
        );
    }
}
