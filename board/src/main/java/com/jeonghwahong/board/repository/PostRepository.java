package com.jeonghwahong.board.repository;

import com.jeonghwahong.board.entity.Post;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.likes join fetch p.comments join fetch p.board join fetch p.member where p.id = :id")
    Optional<Post> findPostById(@Param("id") Long id);
}
