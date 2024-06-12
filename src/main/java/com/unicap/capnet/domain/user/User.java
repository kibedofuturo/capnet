package com.unicap.capnet.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicap.capnet.domain.comment.Comment;
import com.unicap.capnet.domain.course.Course;
import com.unicap.capnet.domain.event.Event;
import com.unicap.capnet.domain.publication.Publication;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private String ra;

    @Setter
    private boolean isGraduated;
    @Setter
    private String email;
    @Setter
    private String password;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "course")
    private Course course;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Publication> publicationList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Event> eventList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> commentList;

    @Column(name = "active", columnDefinition = "boolean USING active:boolean")
    private boolean active;

    public User(UserDTO data) {
        active = true;
        name = data.name();
        cpf = data.cpf();
        ra = data.ra();
        isGraduated = data.isGraduated();
        email = data.email();
        password = data.password();
        course = data.course();
    }

    public void setGraduated(boolean graduated) {
        isGraduated = graduated;
    }

    public void updateInfo(UpdateUserDTO data) {
        if (this.email != null) {
            this.email = data.email();
        }
        if (this.password != null) {
            this.password = data.password();
        }
        if (this.course != null) {
            this.course = data.course();
        }
        this.isGraduated = data.isGraduated();
    }

    public void delete() {
        this.active = false;
    }
}
