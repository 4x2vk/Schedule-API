package org.example.schedulemanagementapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private String name;
    private String password;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(String comment, String name, String password, Schedule schedule) {
        this.comment = comment;
        this.name = name;
        this.password = password;
        this.schedule = schedule;
    }
}
