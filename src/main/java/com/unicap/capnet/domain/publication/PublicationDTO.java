package com.unicap.capnet.domain.publication;

import com.unicap.capnet.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PublicationDTO(
        @NotBlank
        String text,
        @NotNull
        LocalDateTime publicationDate
) {
}
