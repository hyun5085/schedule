package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter //  클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성해 줌
// 응답 데이터
public class ScheduleResponseDto {

    private Long scheduleId;
    private String scheduleTodo;
    private String scheduleWriter;
    private String schedulePassword;

    // @JsonFormat은 날짜와 시간의 형식을 지정
    // JsonFormat.Shape.STRING: 날짜를 문자열 형식으로 변환.
    // 날짜나 시간의 형식을 정의하는 문자열,  pattern = "yyyy-MM-dd" 문자 패턴은 yyyy-mm-dd 형식으로 전달하겠다는 말
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd','HH-mm-ss")
    private LocalDateTime scheduleCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd','HH-mm-ss")
    private LocalDateTime scheduleUpdated;

    // Schedule 객체를 받아서 dto 에 복사하는 생성자
    public ScheduleResponseDto(Schedule schedule){

        this.scheduleId = schedule.getScheduleId();
        this.scheduleTodo = schedule.getScheduleTodo();
        this.scheduleWriter = schedule.getScheduleWriter();
        this.schedulePassword = schedule.getSchedulePassword();
        this.scheduleCreated = LocalDateTime.now();
        this.scheduleUpdated = LocalDateTime.now();

    }



}
