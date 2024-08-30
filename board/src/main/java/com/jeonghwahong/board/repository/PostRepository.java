package com.jeonghwahong.board.repository;

import com.jeonghwahong.board.entity.Post;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.member join fetch p.board left join fetch p.comments where p.id = :id")
    Optional<Post> findPostById(@Param("id") Long id);

    @Query("select p from Post p join fetch p.member join fetch p.board left join fetch p.comments where p.title LIKE %:query%")
    Page<Post> findBySearch(@Param("query") String query, Pageable pageable);
}
