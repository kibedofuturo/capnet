package com.unicap.capnet.domain.comment;

import jakarta.validation.constraints.NotNull;

public record UpdateCommentDTO(
        @NotNull
        String text
) {
}
