package com.jeonghwahong.board.repository;

import com.jeonghwahong.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
