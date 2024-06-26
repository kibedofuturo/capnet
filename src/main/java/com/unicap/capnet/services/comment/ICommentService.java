package com.unicap.capnet.services.comment;

import com.unicap.capnet.domain.comment.ListCommentDTO;
import com.unicap.capnet.domain.comment.Comment;
import com.unicap.capnet.domain.comment.CommentDTO;
import com.unicap.capnet.domain.comment.UpdateCommentDTO;
import com.unicap.capnet.domain.publication.Publication;
import com.unicap.capnet.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICommentService {
    Comment findById(long commentId);

    Page<ListCommentDTO> findAllComments(Pageable pagination);

    void saveComment(CommentDTO data, Publication publication, User user);

    Optional<Comment> updateComment(UpdateCommentDTO data, Long commentId);

    void deleteComment(long commentId);
}
