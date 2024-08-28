package com.jeonghwahong.board.repository;

import com.jeonghwahong.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
