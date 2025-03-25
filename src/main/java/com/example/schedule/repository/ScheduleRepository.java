package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    // 생성
    Schedule saveSchedule(Schedule schedule);

    // 전체 조회
    List<ScheduleResponseDto> findAllSchedules();

    // 단건 조회
    Schedule findScheduleById(Long id);

    // 일정 삭제
    void deleteSchedule(Long id);

}
