package com.unicap.capnet.controllers;

import com.unicap.capnet.domain.comment.CommentDTO;
import com.unicap.capnet.domain.comment.ListCommentDTO;
import com.unicap.capnet.domain.comment.Comment;
import com.unicap.capnet.domain.comment.UpdateCommentDTO;
import com.unicap.capnet.services.comment.CommentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid CommentDTO data) {
        commentService.saveComment(data);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ListCommentDTO> getCommentById(@PathVariable long commentId){
        Comment comment = commentService.findById(commentId);

        if (comment != null) {
            ListCommentDTO commentDTO = new ListCommentDTO(comment);

            return ResponseEntity.ok(commentDTO);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ListCommentDTO>> getAllComments(Pageable pagination) {
        Page<ListCommentDTO> commentPage = commentService.findAllComments(pagination);

        return ResponseEntity.ok(commentPage);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateCommentDTO data) {
        commentService.updateComment(data);
    }

    @DeleteMapping("/{commentId}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable long commentId) {
        Comment comment = commentService.findById(commentId);

        if (comment != null) {
            commentService.deleteComment(commentId);
            return ResponseEntity.ok("Comentário excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado.");
        }
    }
}
