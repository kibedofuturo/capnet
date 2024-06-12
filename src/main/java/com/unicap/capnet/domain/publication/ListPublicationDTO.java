package com.unicap.capnet.domain.publication;

import com.unicap.capnet.domain.comment.Comment;
import com.unicap.capnet.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record ListPublicationDTO(
        long id,
        String text,
        LocalDateTime publicationDate,
        User user,
        List<Comment> commentList
) {
    public ListPublicationDTO(Publication publication) {
        this(
                publication.getId(),
                publication.getText(),
                publication.getPublicationDate(),
                publication.getUser(),
                publication.getCommentList()
        );
    }
}
