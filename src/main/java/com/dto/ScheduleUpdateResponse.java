package com.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleUpdateResponse {

    private final String title;
    private final String username;
    private final String password;

    public ScheduleUpdateResponse(
            String title,
            String username,
            String password,
            LocalDateTime updatedAt)
    {
        this.title = title;
        this.username = username;
        this.password = password;
    }

}
