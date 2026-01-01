package com.controller;

import com.dto.*;
import com.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 - 일정 제목, 일정 내용, 작성자명, 비밀번호, 작성/수정일 저장 (API 응답에는 pw 제외)
    @PostMapping("/schedulers")
    public ResponseEntity<ScheduleCreateResponse> create(
            @RequestBody ScheduleCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 일정 전체 조회 - 작성자명 기준으로 목록 조회 (포함 안될수도), 수정일 기준 내림차순 정렬 (API 응답 pw 제외)
    @GetMapping("/schedulers")
    public ResponseEntity<List<ScheduleGetResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    // 일정 선택 조회 - 단건 정보 조회, 고유 식별자(ID) 사용 (API 응답 pw 제외)
    @GetMapping("/schedulers/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getOne(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

    // 일정 수정 - 선택 일정 한정 조회, 일정 제목/작성자명만 수정 가능, 비밀번호 전달 (API 응답 pw 제외)
    @PutMapping("/schedulers/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> update(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    // 일정 삭제 - 선택 일적 삭제, 비밀번호 전달
    @DeleteMapping("/schedulers/{scheduleId}")
    public void delete(
            @PathVariable Long scheduleId
    ) {
        scheduleService.delete(scheduleId);
    }
}