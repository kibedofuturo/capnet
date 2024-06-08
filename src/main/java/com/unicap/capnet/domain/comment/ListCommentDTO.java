package com.unicap.capnet.domain.comment;

import java.time.LocalDateTime;

public record ListCommentDTO(
        String text,
        LocalDateTime commentDate
) {
    public ListCommentDTO(Comment comment) {
        this(
                comment.getText(),
                comment.getCommentDate()
        );
    }
}
