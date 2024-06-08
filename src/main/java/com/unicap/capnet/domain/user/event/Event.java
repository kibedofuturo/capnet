package com.unicap.capnet.domain.event;

import com.unicap.capnet.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "events")
@Entity(name = "Event")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "active", columnDefinition = "boolean USING active:boolean")
    private boolean active;

    public Event(EventDTO data) {
        active = true;
        title = data.title();
        description = data.description();
        eventDate = data.eventDate();
    }

    public void updateInfo(UpdateEventDTO data) {
        if (this.description != null) {
            this.description = data.description();
        }
        if (this.eventDate != null) {
            this.eventDate = data.eventDate();
        }
    }

    public void delete() {
        this.active = false;
    }
}
