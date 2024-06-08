package com.unicap.capnet.services.comment;

import com.unicap.capnet.domain.comment.Comment;
import com.unicap.capnet.domain.comment.CommentDTO;
import com.unicap.capnet.domain.comment.ListCommentDTO;
import com.unicap.capnet.domain.comment.UpdateCommentDTO;
import com.unicap.capnet.repositories.comment.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment findById(long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Page<ListCommentDTO> findAllComments(Pageable pagination) {
        return commentRepository.findAllByActiveTrue(pagination).map(ListCommentDTO::new);
    }

    @Override
    public void saveComment(CommentDTO data) {
        commentRepository.save(new Comment(data));
    }

    @Override
    public void updateComment(UpdateCommentDTO data) {
        var comment = commentRepository.getReferenceById(data.id());
        comment.updateInfo(data);
    }

    @Override
    public void deleteComment(long commentId) {
        var comment = commentRepository.getReferenceById(commentId);
        comment.delete();
    }
}
