package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto);
        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        return new ScheduleResponseDto(savedSchedule.getScheduleId(),
                savedSchedule.getScheduleTodo(),
                savedSchedule.getScheduleWriter(),
                savedSchedule.getScheduleCreated(),
                savedSchedule.getScheduleUpdated()
        );
    }

    // 전체 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules(String scheduleWriter, LocalDate scheduleUpdated) {
        return scheduleRepository.findAllSchedules(scheduleWriter,scheduleUpdated);
    }

    // 단건 조회
    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }


    // 일정 수정
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String scheduleWriter, String scheduleTodo, String password) {
        // 필수값 검증
        if (scheduleWriter == null || scheduleTodo == null || password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title, content, and password are required values.");
        }

        // 기존 일정 조회
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        // 비밀번호 확인
        if (!schedule.getSchedulePassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password.");
        }

        // 일정 수정
        schedule.update(scheduleWriter, scheduleTodo);

        // 수정된 일정 저장
        scheduleRepository.updateSchedule(schedule);

        return new ScheduleResponseDto(schedule);
    }

    // 일정 삭제
    @Override
    public void deleteSchedule(Long id, String schedulePassword) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        // 비밀번호 확인
        if (!schedule.getSchedulePassword().equals(schedulePassword)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Password does not match.");
        }

        scheduleRepository.deleteSchedule(id);
    }



}
