package com.example.schedule.service;


import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    // 생성
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    // 전체 조회
    List<ScheduleResponseDto> findAllSchedules();

    // 단건 조회
    ScheduleResponseDto findScheduleById(Long id);

    // 일정 수정
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto);

    // 일정 삭제
    void deleteSchedule(Long id);
}
