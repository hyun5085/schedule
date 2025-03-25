package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
// @Controller + @ResponseBody 기능을 합친 것
// @Controller : View(View, HTML) 페이지를 반환하는 컨트롤러
// @RestController : JSON 응답을 반환하는 컨트롤러
@RequestMapping("/schedules") // Prefix 공통적으로 선언된 URL
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 생성자 주입
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    // 일정 생성
    @PostMapping // HTTP POST 요청을 처리 (주로 데이터 생성!!에 사용).
    // ResponseEntity<>:  HTTP 응답을 반환
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto){

        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto), HttpStatus.CREATED);

    }

    // 일정 전체 조회
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule(){

        return scheduleService.findAllSchedules();
    }

    // 일정 단건 조회
    @GetMapping("/{id}") // HTTP GET 요청을 처리 (주로 데이터 조회에 사용).
    // @PathVariable :  MVC에서 URL 경로에 포함된 변수를 메서드 파라미터로 매핑할 때 사용
    // ResponseEntity<>:  HTTP 응답을 반환
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){

        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }


    // 일정 수정
    @PutMapping("/{id}") // HTTP Put 요청을 처리 (주로 데이터 수정에 사용).
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id, // @PathVariable :  MVC에서 URL 경로에 포함된 변수를 메서드 파라미터로 매핑할 때 사용
            @RequestBody ScheduleRequestDto scheduleRequestDto
    ){
        return new ResponseEntity<>(scheduleService.updateSchedule(id, scheduleRequestDto),HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}") // HTTP Delete 요청을 처리 (주로 데이터 삭제에 사용).
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
