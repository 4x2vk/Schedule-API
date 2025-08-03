package org.example.schedulemanagementapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.repository.CommentRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;


}
