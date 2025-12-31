package com.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetResponse {

    private final Long id;
    private final String title;
    private final String text;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleGetResponse (
            Long id,
            String title,
            String text,
            String username,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
