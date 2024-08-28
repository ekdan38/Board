package com.jeonghwahong.board.repository;

import com.jeonghwahong.board.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
