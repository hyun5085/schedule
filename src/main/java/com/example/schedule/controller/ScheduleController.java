package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/schedules")
// @Controller + @ResponseBody 기능을 합친 것
// @Controller : View(View, HTML) 페이지를 반환하는 컨트롤러
// @RestController : JSON 응답을 반환하는 컨트롤러
public class ScheduleController {

    // MAP : Key(키)-Value(값) 쌍으로 데이터를 저장
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    // 일정 생성
    @PostMapping // HTTP POST 요청을 처리 (주로 데이터 생성!!에 사용).
    // ResponseEntity<>:  HTTP 응답을 반환
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto){

        // scheduleList.isEmpty() => scheduleList가 비어있는지 확인
        // ? 1 : ... => . 조건이 true 일 경우 1을 사용하고, 조건이 false일 경우 뒤의 ... 부분을 실행
        // scheduleList 의 키 값들 중 가장 큰 값을 찾아서 1을 더한 값
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

        // 요청 받은 데이터로 Schedule 객체 생성
        // 가독성이 떨어져서 get 으로 가져오기보다는 Schedule에서 생성자 생성
        Schedule schedule = new Schedule(scheduleId, scheduleRequestDto);

        // Inmemory DB에 Schedule 메모
        // put(key, value): 키-값을 저장 (키가 있으면 덮어쓰기, 없으면 새로 추가)
        scheduleList.put(scheduleId, schedule);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.CREATED);
    }

    // 일정 단건 조회
    @GetMapping("/{id}") // HTTP GET 요청을 처리 (주로 데이터 조회에 사용).
    // @PathVariable :  MVC에서 URL 경로에 포함된 변수를 메서드 파라미터로 매핑할 때 사용
    // ResponseEntity<>:  HTTP 응답을 반환
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){

        // scheduleList에서 id에 해당하는 Schedule 객체를 찾아서 schedule 변수에 할당
        Schedule schedule = scheduleList.get(id);
        // schedule 에 데이터가 없을 때 HTTP 읃답을 NOT_FOUND 로 반환
        if (schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    // 일정 전체 조회
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule(){

        // init List
        List<ScheduleResponseDto> scheduleResponseList = new ArrayList<>();

        for (Schedule schedule : scheduleList.values()){
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
            scheduleResponseList.add(scheduleResponseDto);
        }

        return scheduleResponseList;
    }

    // 일정 수정
    @PutMapping("/{id}") // HTTP Put 요청을 처리 (주로 데이터 수정에 사용).
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long id, // @PathVariable :  MVC에서 URL 경로에 포함된 변수를 메서드 파라미터로 매핑할 때 사용
            @RequestBody ScheduleRequestDto scheduleRequestDto
    ){
        // scheduleList에서 id에 해당하는 Schedule 객체를 찾아서 schedule 변수에 할당
        Schedule schedule = scheduleList.get(id);

        // schedule 에 데이터가 없을 때 HTTP 읃답을 NOT_FOUND 로 반환
        if (schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // schedule 수정 데이터에 작성자 데이터가 없으면 BAD_REQUEST 로 반환
        if (scheduleRequestDto.getScheduleWriter() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        schedule.update(scheduleRequestDto);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule),HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // HTTP Delete 요청을 처리 (주로 데이터 삭제에 사용).
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){

        if(scheduleList.containsKey(id)){
            scheduleList.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
