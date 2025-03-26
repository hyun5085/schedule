package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;


    public JdbcTemplateScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    // 생성
    @Override
    public Schedule saveSchedule(Schedule schedule) {

        // SimpleJdbcInsert를 사용하여 새로운 레코드를 삽입
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schedules") // 테이블 이름 설정
                .usingGeneratedKeyColumns("schedule_id"); // 생성된 key(기본키)를 반환하도록 설정

        // 삽입할 데이터 매핑
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("schedule_todo", schedule.getScheduleTodo());  // 컬럼 이름 확인
        parameters.put("schedule_writer", schedule.getScheduleWriter());  // 컬럼 이름 확인
        parameters.put("schedule_password", schedule.getSchedulePassword());  // 컬럼 이름 확인
        parameters.put("schedule_created", schedule.getScheduleCreated());  // 컬럼 이름 확인
        parameters.put("schedule_updated", schedule.getScheduleUpdated());  // 컬럼 이름 확인

        // 새로운 일정의 ID를 반환
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        // 삽입된 일정 객체 반환 (ID를 포함)
        return new Schedule(
                key.longValue(),
                schedule.getScheduleTodo(),
                schedule.getScheduleWriter(),
                schedule.getSchedulePassword(),
                schedule.getScheduleCreated(),
                schedule.getScheduleUpdated()
        );
    }


    // 전체 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules(String scheduleWriter, LocalDate scheduleUpdated) {
        StringBuilder sql = new StringBuilder("SELECT schedule_id, schedule_todo, schedule_writer, schedule_created, schedule_updated FROM schedules WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (scheduleUpdated != null) {
            sql.append("AND schedule_updated BETWEEN ? AND ? ");
            params.add(scheduleUpdated.atStartOfDay());  // 2024-03-26 00:00:00
            params.add(scheduleUpdated.atTime(23, 59, 59));  // 2024-03-26 23:59:59
        }
        if (scheduleWriter != null && !scheduleWriter.isEmpty()) {
            sql.append("AND schedule_writer = ? ");
            params.add(scheduleWriter);
        }
        sql.append("ORDER BY schedule_updated DESC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), scheduleRowMapper());
    }

    // 단건 조회
    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedules where schedule_id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    // 일정 수정
    @Override
    public void updateSchedule(Schedule schedule) {
        String sql = "UPDATE schedules SET schedule_todo = ?, schedule_writer = ?, schedule_updated = ? WHERE schedule_id = ?";
        jdbcTemplate.update(sql, schedule.getScheduleTodo(), schedule.getScheduleWriter(), LocalDateTime.now(), schedule.getScheduleId());
    }

    // 일정 삭제
    @Override
    public void deleteSchedule(Long id) {
        String sql = "DELETE FROM schedules WHERE schedule_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
    }






    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long scheduleId = rs.getLong("schedule_id");
                String scheduleTodo = rs.getString("schedule_todo");
                String scheduleWriter = rs.getString("schedule_writer");
                LocalDateTime scheduleCreated = rs.getTimestamp("schedule_created").toLocalDateTime();
                LocalDateTime scheduleUpdated = rs.getTimestamp("schedule_updated").toLocalDateTime();

                // ScheduleResponseDto 객체를 생성해서 반환
                return new ScheduleResponseDto(scheduleId, scheduleTodo, scheduleWriter, scheduleCreated, scheduleUpdated);
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long scheduleId = rs.getLong("schedule_id");
                String scheduleTodo = rs.getString("schedule_todo");
                String scheduleWriter = rs.getString("schedule_writer");
                String schedulePassword = rs.getString("schedule_password"); // password 추가
                LocalDateTime scheduleCreated = rs.getTimestamp("schedule_created") != null ? rs.getTimestamp("schedule_created").toLocalDateTime() : null;
                LocalDateTime scheduleUpdated = rs.getTimestamp("schedule_updated") != null ? rs.getTimestamp("schedule_updated").toLocalDateTime() : null;

                return new Schedule(scheduleId, scheduleTodo, scheduleWriter, schedulePassword, scheduleCreated, scheduleUpdated);
            }
        };
    }


}
