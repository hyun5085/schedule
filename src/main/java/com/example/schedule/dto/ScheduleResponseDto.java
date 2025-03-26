package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long scheduleId;
    private String scheduleTodo;
    private String scheduleWriter;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime scheduleCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime scheduleUpdated;

    public ScheduleResponseDto(Long scheduleId, String scheduleTodo, String scheduleWriter,LocalDateTime scheduleCreated, LocalDateTime scheduleUpdated) {
        this.scheduleId = scheduleId;
        this.scheduleTodo = scheduleTodo;
        this.scheduleWriter = scheduleWriter;
        this.scheduleCreated = scheduleCreated;
        this.scheduleUpdated = scheduleUpdated;
    }
    public ScheduleResponseDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.scheduleTodo = schedule.getScheduleTodo();
        this.scheduleWriter = schedule.getScheduleWriter();
        this.scheduleCreated = schedule.getScheduleCreated();
        this.scheduleUpdated = schedule.getScheduleUpdated();
    }
}
