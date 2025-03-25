package com.example.schedule.entity;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter //  클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성해 줌
@AllArgsConstructor // 모든 필드를 포함하는 생성자를 자동으로 생성해 줌
public class Schedule {

    private Long scheduleId;            // 고유 식별자(ID)
    private String scheduleTodo;        // 할 일
    private String scheduleWriter;      // 작성자명
    private String schedulePassword;    // 비밀번호
    private Timestamp scheduleCreated;  // 작성일
    private Timestamp scheduleUpdated;  // 수정일

    public Schedule(Long scheduleId, ScheduleRequestDto scheduleRequestDto) {
        this.scheduleId = scheduleId;
        this.scheduleTodo = scheduleRequestDto.getScheduleTodo();
        this.scheduleWriter = scheduleRequestDto.getScheduleWriter();
        this.schedulePassword = scheduleRequestDto.getSchedulePassword();
        this.scheduleCreated = scheduleRequestDto.getScheduleCreated();
        this.scheduleUpdated = scheduleRequestDto.getScheduleUpdated();
    }

    public void update(ScheduleRequestDto scheduleResponseDto){

        this.scheduleTodo = scheduleResponseDto.getScheduleTodo();
        this.scheduleWriter = scheduleResponseDto.getScheduleWriter();
        this.scheduleUpdated = scheduleResponseDto.getScheduleUpdated();

    }

}
