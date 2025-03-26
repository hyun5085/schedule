package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    // 일정 생성
    Schedule saveSchedule(Schedule schedule);
    // 전체 조회
    List<ScheduleResponseDto> findAllSchedules(String scheduleWriter, LocalDate scheduleUpdated);
    // 단건 조회
    Schedule findScheduleByIdOrElseThrow(Long id);
    // 일정 수정
    void updateSchedule(Schedule schedule);
    // 일정 삭제
    void deleteSchedule(Long id);

}
