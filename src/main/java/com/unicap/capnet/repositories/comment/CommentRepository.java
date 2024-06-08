package com.unicap.capnet.repositories.comment;

import com.unicap.capnet.domain.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByActiveTrue(Pageable pagination);
}
