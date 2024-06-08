package com.unicap.capnet.domain.comment;

import jakarta.validation.constraints.NotNull;

public record UpdateCommentDTO(
        @NotNull
        long id,
        @NotNull
        String text
) {
}
