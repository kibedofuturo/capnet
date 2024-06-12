package com.unicap.capnet.domain.comment;

import com.unicap.capnet.domain.user.User;

import java.time.LocalDateTime;

public record ListCommentDTO(
        String text,
        LocalDateTime commentDate,
        User user
) {
    public ListCommentDTO(Comment comment) {
        this(
                comment.getText(),
                comment.getCommentDate(),
                comment.getUser()
        );
    }
}
