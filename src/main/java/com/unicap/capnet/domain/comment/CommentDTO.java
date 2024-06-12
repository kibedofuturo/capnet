package com.unicap.capnet.domain.comment;

import com.unicap.capnet.domain.user.User;

import java.time.LocalDateTime;

public record CommentDTO(
        String text,
        User user
) {
}
