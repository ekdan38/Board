package com.jeonghwahong.board.repository;

import com.jeonghwahong.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
