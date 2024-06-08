package com.unicap.capnet.domain.comment;

import com.unicap.capnet.domain.publication.Publication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "comments")
@Entity(name = "Comment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;
    private LocalDateTime commentDate;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    private boolean active;

    public Comment(CommentDTO data) {
        active = true;
        text = data.text();
        commentDate = data.commentDate();
    }

    public void updateInfo(UpdateCommentDTO data) {
        if (this.text != null) {
            this.text = data.text();
        }
    }

    public void delete() {
        this.active = false;
    }
}
