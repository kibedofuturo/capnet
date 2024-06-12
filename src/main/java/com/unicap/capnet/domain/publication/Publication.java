package com.unicap.capnet.domain.publication;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unicap.capnet.domain.comment.Comment;
import com.unicap.capnet.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "publications")
@Entity(name = "Publication")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;
    private LocalDateTime publicationDate;

    //@OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<Reaction> reactionList;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "active", columnDefinition = "boolean USING active:boolean")
    private boolean active;

    public Publication(PublicationDTO data, User user) {
        active = true;
        text = data.text();
        publicationDate = data.publicationDate();
        this.user = user;
    }

    public void updateInfo(UpdatePublicationDTO data) {
        if( this.text != null) {
            this.text = data.text();
        }
    }

    public void delete() {
        this.active = false;
    }
}
