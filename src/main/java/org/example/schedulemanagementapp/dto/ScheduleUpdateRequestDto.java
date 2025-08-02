package org.example.schedulemanagementapp.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    private Long id;
    private String title;
    private String password;
    private String name;
}
