package com.enfity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Scheduler")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private String username;
    private String password;

    public Schedule(String title, String text, String username) {
        this.title = title;
        this.text = text;
        this.username = username;
        this.password = password;
    }

    public void update(String text, String username) {
        this.text = text;
        this.username = username;
    }
}
