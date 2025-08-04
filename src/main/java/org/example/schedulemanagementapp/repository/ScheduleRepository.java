package org.example.schedulemanagementapp.repository;

import org.example.schedulemanagementapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    // 사용자 이름으로 스케줄 검색
    List<Schedule> findByName(String name);
}

