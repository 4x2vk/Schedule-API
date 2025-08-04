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
    // 스케줄 데이터 접근 객체
    private final ScheduleRepository scheduleRepository;

    // 새 스케줄을 생성하고 저장
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
    // 이름에 따라 전체 또는 특정 사용자 스케줄 목록 조회
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
    // ID로 스케줄 단건 조회
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
    // 스케줄 정보 수정. 비밀번호 일치 확인 포함
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
    // 스케줄 삭제. 비밀번호 일치 확인 포함
    @Transactional
    public void deleteById(Long id, ScheduleDeleteRequestDto scheduleDeleteRequestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule with id %d not found", id)));

        String recentPassword = schedule.getPassword();
        
        checkPassword(scheduleDeleteRequestDto.getPassword(), recentPassword);
        scheduleRepository.deleteById(id);
    }
    // 입력된 비밀번호와 기존 비밀번호 비교
    private void checkPassword(String inputPassword, String recentPassword) {
        if (!inputPassword.equals(recentPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
