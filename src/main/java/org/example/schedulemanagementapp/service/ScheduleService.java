package org.example.schedulemanagementapp.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.dto.*;
import org.example.schedulemanagementapp.entity.Schedule;
import org.example.schedulemanagementapp.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(
                scheduleRequestDto.getTitle(),
                scheduleRequestDto.getContents(),
                scheduleRequestDto.getName(),
                scheduleRequestDto.getPassword()
        );
        Schedule savedMember = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedMember.getId(), savedMember.getTitle(), savedMember.getContents(), savedMember.getName(), savedMember.getCreatedAt(), savedMember.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll(String name) {
        List<Schedule> scheduleList;

        if (name != null && !name.isEmpty()) {
            scheduleList = scheduleRepository.findByName(name);
        } else {
            scheduleList = scheduleRepository.findAll();
        }

        return scheduleList.stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getName(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()))
                .sorted(Comparator.comparing(ScheduleResponseDto::getModifiedAt).reversed())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule with id %d not found", id)));

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public ScheduleUpdateResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule with id %d not found", id)));

        String recentPassword = schedule.getPassword();

        schedule.changeTitle(scheduleUpdateRequestDto.getTitle());
        schedule.changeName(scheduleUpdateRequestDto.getName());
        checkPassword(scheduleUpdateRequestDto.getPassword(), recentPassword);
        scheduleRepository.save(schedule);

        return new ScheduleUpdateResponseDto(schedule.getTitle(),schedule.getContents(),schedule.getName(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Transactional
    public void deleteById(Long id, ScheduleDeleteRequestDto scheduleDeleteRequestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule with id %d not found", id)));

        String recentPassword = schedule.getPassword();
        
        checkPassword(scheduleDeleteRequestDto.getPassword(), recentPassword);
        scheduleRepository.deleteById(id);
    }

    private void checkPassword(String inputPassword, String recentPassword) {
        if (!inputPassword.equals(recentPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
