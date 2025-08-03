package org.example.schedulemanagementapp.repository;

import org.example.schedulemanagementapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
