package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

@Repository // 자동으로 Bean으로 등록하고 예외 변환 처리
public class ScheduleRepositoryImpl implements ScheduleRepository {

    // MAP : Key(키)-Value(값) 쌍으로 데이터를 저장
    private final Map<Long, Schedule> scheduleList = new HashMap<>();


    // 생성
    @Override
    public Schedule saveSchedule(Schedule schedule) {

        // scheduleList.isEmpty() => scheduleList가 비어있는지 확인
        // ? 1 : ... => . 조건이 true 일 경우 1을 사용하고, 조건이 false일 경우 뒤의 ... 부분을 실행
        // scheduleList 의 키 값들 중 가장 큰 값을 찾아서 1을 더한 값
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

        schedule.setScheduleId(scheduleId);
        // Inmemory DB에 Schedule 메모
        // put(key, value): 키-값을 저장 (키가 있으면 덮어쓰기, 없으면 새로 추가)
        scheduleList.put(scheduleId, schedule);

        return schedule;
    }

    // 전체 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        List<ScheduleResponseDto> allSchedules = new ArrayList<>();

        for (Schedule schedule : scheduleList.values()){
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
            allSchedules.add(scheduleResponseDto);
        }

        return allSchedules;
    }

    // 단건 조회
    @Override
    public Schedule findScheduleById(Long id) {

        return scheduleList.get(id);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleList.remove(id);
    }
}
