package org.example.schedulemanagementapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.dto.*;
import org.example.schedulemanagementapp.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    // 새 스케줄 생성
    @PostMapping("/schedule")
    public ScheduleResponseDto createMember(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.save(scheduleRequestDto);
    }
    // 전체 또는 특정 사용자 스케줄 목록 조회
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getMembers(@RequestParam(required = false) String name) {
        return scheduleService.findAll(name);
    }
    // 특정 ID의 스케줄 조회
    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getMember(@PathVariable Long id) {
        return scheduleService.findById(id);
    }
    // 스케줄 수정
    @PutMapping("/schedule/{id}")
    public ScheduleUpdateResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        return scheduleService.updateSchedule(id, scheduleUpdateRequestDto);
    }
    // 스케줄 삭제
    @DeleteMapping("/schedule/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestBody ScheduleDeleteRequestDto scheduleDeleteRequestDto) {
        scheduleService.deleteById(id, scheduleDeleteRequestDto);
    }
}
