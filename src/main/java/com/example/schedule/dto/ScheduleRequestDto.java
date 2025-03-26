package com.example.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter //  클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성해 줌
// 요청 데이터
public class ScheduleRequestDto {

    private String scheduleTodo;
    private String scheduleWriter;
    private String schedulePassword;
    private LocalDateTime scheduleCreated;
    private LocalDateTime scheduleUpdated;

}
