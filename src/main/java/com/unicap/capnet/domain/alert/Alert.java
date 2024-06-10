package com.unicap.capnet.domain.alert;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "alerts")
@Entity(name = "Alert")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private  String description;
    private  LocalDateTime date;
    private LocalDateTime alertDate;

    @Column(name = "active", columnDefinition = "boolean USING active:boolean")
    private boolean active;

    public Alert(AlertDTO data) {
        active = true;
        title = data.title();
        description = data.description();
        date = data.date();
        alertDate = LocalDateTime.now();
    }

    public void updateInfo(UpdateAlertDTO data) {
        if (this.title != null) {
            this.title = data.title();
        }
        if (this.description != null) {
            this.description= data.description();
        }
        if (this.description != null ) {
            this.date = data.date();
        }
    }

    public void delete() {
        this.active = false;
    }
}
