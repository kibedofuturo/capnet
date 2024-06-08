package com.unicap.capnet.domain.comment;

import java.time.LocalDateTime;

public record CommentDTO(
        String text,
        LocalDateTime commentDate
) {
}
