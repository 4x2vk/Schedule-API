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

    @PostMapping("/schedule")
    public ScheduleResponseDto createMember(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.save(scheduleRequestDto);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getMembers(@RequestParam(required = false) String name) {
        return scheduleService.findAll(name);
    }

    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getMember(@PathVariable Long id) {
        return scheduleService.findById(id);
    }

    @PutMapping("/schedule/{id}")
    public ScheduleUpdateResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        return scheduleService.updateSchedule(id, scheduleUpdateRequestDto);
    }

    @DeleteMapping("/schedule/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestBody ScheduleDeleteRequestDto scheduleDeleteRequestDto) {
        scheduleService.deleteById(id, scheduleDeleteRequestDto);
    }
}
