package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    // 일정 저장
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);
    // 전체 조회
    List<ScheduleResponseDto> findAllSchedules(String scheduleWriter,LocalDate scheduleUpdated);
    // 단건 조회
    ScheduleResponseDto findScheduleById(Long id);
    // 일정 수정
    ScheduleResponseDto updateSchedule(Long id, String scheduleWriter, String scheduleTodo, String password);
    // 일정 삭제
    void deleteSchedule(Long id, String schedulePassword);



}
