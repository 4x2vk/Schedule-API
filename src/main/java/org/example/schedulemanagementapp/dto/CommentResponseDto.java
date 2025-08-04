package org.example.schedulemanagementapp.dto;

import lombok.Getter;
import org.example.schedulemanagementapp.entity.Comment;
/*
    code is not ready
 */
@Getter
public class CommentResponseDto {
    private final Long id;
    private final String comment;
    private final String name;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.name = comment.getName();
    }
}
