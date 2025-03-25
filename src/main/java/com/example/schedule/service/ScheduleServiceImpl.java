package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
// @Service : 자동으로 객체를 생성해 관리하므로 개발자가 직접 new로 생성할 필요 없음
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 생성
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {

        // 요청 받은 데이터로 Schedule 객체 생성
        // 가독성이 떨어져서 get 으로 가져오기보다는 Schedule에서 생성자 생성
        Schedule schedule = new Schedule(scheduleRequestDto);

        // DB 저장
        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    // 전체 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();

        return allSchedules;
    }

    // 단건 조회
    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new ScheduleResponseDto(schedule);
    }

    // 일정 수정
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        schedule.update(scheduleRequestDto);

        return new ScheduleResponseDto(schedule);

    }

    // 일정 삭제
    @Override
    public void deleteSchedule(Long id) {

        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        scheduleRepository.deleteSchedule(id);

    }


}
