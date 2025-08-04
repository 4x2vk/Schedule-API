package org.example.schedulemanagementapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.dto.CommentRequestDto;
import org.example.schedulemanagementapp.dto.CommentResponseDto;
import org.example.schedulemanagementapp.entity.Comment;
import org.example.schedulemanagementapp.entity.Schedule;
import org.example.schedulemanagementapp.repository.CommentRepository;
import org.example.schedulemanagementapp.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/*
    code is not ready
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Schedule schedule = scheduleRepository.findById(commentRequestDto.getScheduleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule with id %d not found", commentRequestDto.getScheduleId())));

        Comment comment = new Comment(
                commentRequestDto.getComment(),
                commentRequestDto.getName(),
                commentRequestDto.getPassword(),
                schedule
        );

        comment = commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
