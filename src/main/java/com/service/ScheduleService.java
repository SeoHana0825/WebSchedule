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
    public List<ScheduleGetResponse> findAll(String criteria) {

     // criteria에 따라 정렬된 schedule 목록 먼저 가져오기
     List<Schedule> schedules;
     if("title".equals(criteria)) {
         schedules = scheduleRepository.findAllByOrderByTitleDesc();
     } else if("username".equals(criteria)) {
         schedules = scheduleRepository.findAllByOrderByUsernameDesc();
     } else {
         schedules = scheduleRepository.findAllByOrderByUpdatedAtDesc();
     }
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
        // 1. 일정 조회 없으면 예외
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        //2. 비밀번호 검증
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
        }
        // 3. 수정 가능한 필드만 변경
        schedule.update(request.getTitle(), request.getUsername(), request.getPassword());
        return new ScheduleUpdateResponse (
                schedule.getTitle(),
                schedule.getUsername(),
                schedule.getPassword(),
                schedule.getUpdatedAt()
        );
    }

    // 4 일정 삭제
    @Transactional
    public void delete(Long scheduleId, String password) {
        // 1. 일정이 존재하지 않을 때
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        // 2. 비밀번호 검증
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
        }
        // 일정이 존재하고, 비밀번호 검증이 통과 했을 때 삭제
        scheduleRepository.deleteById(scheduleId);

    }

}

