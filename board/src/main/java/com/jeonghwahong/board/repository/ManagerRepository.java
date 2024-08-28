package com.jeonghwahong.board.repository;

import com.jeonghwahong.board.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
