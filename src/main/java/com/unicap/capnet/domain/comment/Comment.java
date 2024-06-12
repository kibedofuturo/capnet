package com.unicap.capnet.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicap.capnet.domain.publication.Publication;
import com.unicap.capnet.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

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
    private Long id;

    @Setter
    private String text;
    private LocalDateTime commentDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    @JsonIgnore
    private Publication publication;

    private boolean active;

    public Comment(CommentDTO data, Publication publication, User user) {
        active = true;
        text = data.text();
        commentDate = LocalDateTime.now();
        this.user = user;
        this.publication = publication;
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
