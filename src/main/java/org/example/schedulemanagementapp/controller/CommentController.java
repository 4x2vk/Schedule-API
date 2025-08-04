package org.example.schedulemanagementapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.dto.CommentRequestDto;
import org.example.schedulemanagementapp.dto.CommentResponseDto;
import org.example.schedulemanagementapp.repository.CommentRepository;
import org.example.schedulemanagementapp.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/*
    code is not ready
 */
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }
}
