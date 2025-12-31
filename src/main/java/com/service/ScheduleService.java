package com.service;

import com.dto.*;
import com.enfity.Schedule;
import com.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 1 일정 생성
    @Transactional
    public ScheduleCreateResponse save (ScheduleCreateRequest request) {
        Schedule schedule = new Schedule (
                request.getTitle(),
                request.getText(),
                request.getUsername()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleCreateResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getText(),
                savedSchedule.getUsername(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    // 2-1 전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> findAll() {
     List<Schedule> schedules = scheduleRepository.findAll();
     List<ScheduleGetResponse> dtos = new ArrayList<>();
     for (Schedule schedule : schedules) {
         ScheduleGetResponse dto = new ScheduleGetResponse(
                 schedule.getId(),
                 schedule.getTitle(),
                 schedule.getText(),
                 schedule.getUsername(),
                 schedule.getCreatedAt(),
                 schedule.getUpdatedAt()
         );
         dtos.add(dto);
     }
     return dtos;
    }

    // 2-2 선택 일정 조회
    @Transactional(readOnly = true)
    public ScheduleGetResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getUsername(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );

    }

    // 3 일정 수정
    @Transactional
    public ScheduleUpdateResponse update (Long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        schedule.update(request.getTitle(), request.getText());
        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getUsername(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    // 4 일정 삭제
    @Transactional
    public void delete(Long scheduleId) {

        boolean existence = scheduleRepository.existsById(scheduleId);
        // 일정이 존재하지 않을 때
        if (!existence) {
            throw new IllegalStateException("존재하지 않는 일정입니다.");
        }
            // 일정이 존재했을 때
            scheduleRepository.deleteById(scheduleId);

        }

    }

