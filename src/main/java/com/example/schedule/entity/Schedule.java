package com.example.schedule.entity;

import com.example.schedule.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter //  클래스의 모든 필드에 대한 Getter 메서드를 자동으로 생성해 줌
@AllArgsConstructor // 모든 필드를 포함하는 생성자를 자동으로 생성해 줌
public class Schedule {

    @Setter
    private Long scheduleId;            // 고유 식별자(ID)
    private String scheduleTodo;        // 할 일
    private String scheduleWriter;      // 작성자명
    private String schedulePassword;    // 비밀번호
    private LocalDateTime scheduleCreated;  // 작성일
    private LocalDateTime scheduleUpdated;  // 수정일

    public Schedule(ScheduleRequestDto scheduleRequestDto) {

        this.scheduleTodo = scheduleRequestDto.getScheduleTodo();
        this.scheduleWriter = scheduleRequestDto.getScheduleWriter();
        this.schedulePassword = scheduleRequestDto.getSchedulePassword();

    }

    public void update(ScheduleRequestDto scheduleResponseDto){

        this.scheduleTodo = scheduleResponseDto.getScheduleTodo();
        this.scheduleWriter = scheduleResponseDto.getScheduleWriter();
        this.schedulePassword = scheduleResponseDto.getSchedulePassword();
        this.scheduleUpdated = scheduleResponseDto.getScheduleUpdated();

    }

}
