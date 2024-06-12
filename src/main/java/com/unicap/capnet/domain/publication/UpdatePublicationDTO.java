package com.unicap.capnet.domain.publication;

import jakarta.validation.constraints.NotNull;

public record UpdatePublicationDTO(
        @NotNull
        String text
) {
}
